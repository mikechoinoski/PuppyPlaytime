package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.List;


public class PackDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get Pack by id
     */
    public Pack getById(int packTokenNr) {
        Session session = sessionFactory.openSession();
        Pack pack = session.get( Pack.class, packTokenNr );
        session.close();
        return pack;
    }

    /**
     * update Pack
     * @param Pack  Pack to be inserted or updated
     */
    public void saveOrUpdate(Pack Pack) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(Pack);
        session.close();
    }

    /**
     * update Pack
     * @param pack  Pack to be inserted or updated
     */
    public int insert(Pack pack) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(pack);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a Pack
     * @param pack Pack to be deleted
     */
    public void delete(Pack pack) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(pack);
        transaction.commit();
        session.close();
    }


}
