package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import com.choinoski.entity.Role;
import com.choinoski.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * A class to test the generic dao for roles
 *
 * @author mchoinoski
 */
public class RoleDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Role.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify the success of getting by id
     */
    @Test
    void testGetById() {

        Role retrievedRole = (Role) dao.getById(1);
        assertNotNull(retrievedRole);
        assertEquals("admin", retrievedRole.getRoleName());

    }

    /**
     * Verify the success of an update
     */
    @Test
    void testSaveOrUpdate() {
        String newRoleName = "Down the Street";
        Role packToUpdate = (Role) dao.getById(4);
        packToUpdate.setRoleName(newRoleName);
        dao.saveOrUpdate(packToUpdate);

        Role retrievedRole = (Role) dao.getById(4);
        assertEquals(newRoleName, retrievedRole.getRoleName());
    }

    /**
     * Verify the success of an insert
     */
    @Test
    void testInsert() {

        GenericDao packDao = new GenericDao(Pack.class);

        Pack retrievedPack = (Pack) packDao.getById(6);

        Role newRole = new Role("admin",retrievedPack);

        int id = dao.insert(newRole);

        assertNotEquals(0,id);
        Role insertedRole = (Role) dao.getById(id);
        assertTrue(insertedRole.equals(newRole));

    }

    /**
     * Verify the success of a delete
     */
    @Test
    void testDelete() {
        dao.delete(dao.getById(4));
        assertNull(dao.getById(4));
    }

    /**
     * Verify the success of getting all rows
     */
    @Test
    void testGetAll() {
        List<Role> roles = dao.getAll();
        assertEquals(6, roles.size());
    }

    /**
     * Verify the success of getting by specific data in a column
     */
    @Test
    void testGetByPropertyEqual() {
        List<Pack> packs = dao.getByPropertyEqual("login", "random_pack");
        assertEquals(1, packs.size());
    }

    /**
     * Verify the success of getting by a string that is in a column
     */
    @Test
    void testGetByPropertyLike() {
        List<Pack> packs = dao.getByPropertyLike("login", "rando");
        assertEquals(1, packs.size());
    }
}
