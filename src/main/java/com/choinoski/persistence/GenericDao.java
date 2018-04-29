package com.choinoski.persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.persistence.criteria.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * A generic DAO somewhat inspired by http://rodrigouchoa.wordpress.com
 *
 */
public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    private Session session;

    private CriteriaBuilder builder;
    private CriteriaQuery<T> query;
    private Root<T> root;

    public GenericDao() {

    }

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the entity type, for example, User.
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets an entity by id
     * @param id entity id to search by
     * @return entity
     */
    public <T> T getById(int id) {

        logger.debug("Selecting rows by {}", id);

        Session session = sessionFactory.openSession();
        T entity = (T) session.get(type, id);
        session.close();
        return entity;

    }

    /**
     * update entity
     * @param entity the entity to be inserted or updated
     */
    public void saveOrUpdate(T entity) {

        logger.debug("Updating " + entity.getClass().getName());

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();

    }

    /**
     * insert entity
     * @param entity the entity to be inserted
     */
    public int insert(T entity) {

        logger.debug("Inserting into " + entity.getClass().getName());

        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;

    }

    /**
     * Deletes the entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {

        logger.debug("Deleting from " + entity.getClass().getName());

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();

    }

    /**
     * Gets all entities
     *
     * @return the all entities
     */
    public List<T> getAll() {

        logger.debug("Selecting all rows from a table");

        querySetup();

        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;

    }

    /**
     * Get user by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<T> getByPropertyEqual(String propertyName, String value) {

        logger.debug("Searching for user with {} = {}",  propertyName, value);

        querySetup();

        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;

    }

    public List<T> getByPropertyEqual(String propertyName, boolean value) {

        logger.debug("Searching for user with {} = {}",  propertyName, value);

        querySetup();

        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;

    }

    /**
     *
     *
     */
    public List<T> getByPropertyBetween(String propertyName, LocalDate beginDate, LocalDate endDate) {

        logger.debug("Searching for user with {} between {} and {}",  propertyName, beginDate, endDate);

        querySetup();

        query.select(root).where(builder.between(root.get(propertyName), beginDate, endDate));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;

    }

    /**
     *
     *
     */
    public List<T> getByPropertyBetween(String propertyName, int beginNumber, int endNumber) {

        logger.debug("Searching for user with {} between {} and {}",  propertyName, beginNumber, endNumber);

        querySetup();

        query.select(root).where(builder.between(root.get(propertyName), beginNumber, endNumber));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;

    }

    /**
     * Get user by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<T> getByPropertyLike(String propertyName, String value) {

        logger.debug("Searching for user with {} = {}",  propertyName, value);

        querySetup();

        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<T> entities = session.createQuery( query ).getResultList();
        session.close();
        return entities;

    }

    /**
     *
     *
     */
    public List<T> getByMultipleProperty(String propertyOneName, int propertyOneBegin, int propertyOneEnd,
                                         String propertyTwoName, LocalDate beginDate, LocalDate endDate,
                                         String propertyThreeName, char propertyThree,
                                         String propertyFourName, Boolean propertyFour) {

        //logger.debug("Searching for user with {} between {} and {}",  propertyName, beginNumber, endNumber);
        List<Predicate> predicates = new ArrayList();

        querySetup();

        predicates.add(builder.between(root.get(propertyOneName), propertyOneBegin, propertyOneEnd));
        predicates.add(builder.between(root.get(propertyTwoName), beginDate, endDate));
        if (propertyThree != ' ') {
            predicates.add(builder.equal(root.get(propertyThreeName), propertyThree));
        }
        if (propertyFour != null) {
            predicates.add(builder.equal(root.get(propertyFourName), propertyFour));
        }

        //Predicate[] allPredicates =  predicates.toArray(new Predicate[predicates.size()]);

        query.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;

    }

    private void querySetup() {

        session = sessionFactory.openSession();
        builder = session.getCriteriaBuilder();
        query   = builder.createQuery(type);
        root    = query.from(type);

    }


//https://stackoverflow.com/questions/11138118/really-dynamic-jpa-criteriabuilder

}

