package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * A class to access pack data from a database
 *
 * @author mchoinoski
 */
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
     * @param pack  Pack to be inserted or updated
     */
    public void saveOrUpdate(Pack pack) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(pack);
        transaction.commit();
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

    /** Return a list of all users
     *
     * @return All users
     */
    public List<Pack> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Pack> query = builder.createQuery( Pack.class );
        Root<Pack> root = query.from( Pack.class );
        List<Pack> users = session.createQuery( query ).getResultList();

        logger.debug("The list of users " + users);
        session.close();

        return users;
    }

    /**
     * Get user by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Pack> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Pack> query = builder.createQuery( Pack.class );
        Root<Pack> root = query.from( Pack.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Pack> packs = session.createQuery( query ).getResultList();

        session.close();
        return packs;

    }

    /**
     * Get user by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<Pack> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for user with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Pack> query = builder.createQuery( Pack.class );
        Root<Pack> root = query.from( Pack.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Pack> packs = session.createQuery( query ).getResultList();
        session.close();
        return packs;
    }    


}
