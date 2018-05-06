package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.choinoski.test.util.Database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the pack dao.
 *
 * @author mchoinoski
 */
class PackDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Pack.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify the success of getting by id
     */
    @Test
    void testGetById() {

        Pack retrievedPack = (Pack) dao.getById(3);
        assertNotNull(retrievedPack);
        assertEquals("Awesome Pack!", retrievedPack.getPackName());

    }

    /**
     * Verify the success of an update
     */
    @Test
    void testSaveOrUpdate() {
        String newPackName = "The Best Pack Ever";
        Pack packToUpdate = (Pack) dao.getById(1);
        packToUpdate.setPackName(newPackName);
        dao.saveOrUpdate(packToUpdate);

        Pack retrievedPack = (Pack) dao.getById(1);
        assertEquals(newPackName, retrievedPack.getPackName());
    }
    
    /**
     * Verify the success of an insert
     */
    @Test
    void testInsert() {

        Pack newPack = new Pack("Newest Pack","Ramon","Williams",
                "5122 Spike Way","ramon53324@yahoo.com",
                "16084254867","R42sf63");

        int id = dao.insert(newPack);

        assertNotEquals(0,id);
        Pack insertedPack = (Pack) dao.getById(id);
        assertTrue(insertedPack.equals(newPack));

    }

    /**
     * Verify the success of a delete
     */
    @Test
    void testDelete() {
        dao.delete(dao.getById(5));
        assertNull(dao.getById(5));
    }

    /**
     * Verify the success of getting all rows
     */
    @Test
    void testGetAll() {
        List<Pack> packs = dao.getAll();
        assertEquals(6, packs.size());
    }

    /**
     * Verify the success of getting by specific data in a column
     */
    @Test
    void testGetByPropertyEqual() {
        List<Pack> packs = dao.getByPropertyEqual("packName", "Number 4");
        assertEquals(1, packs.size());
    }

    /**
     * Verify the success of getting by a string that is in a column
     */
    @Test
    void testGetByPropertyLike() {
        List<Pack> packs = dao.getByPropertyLike("packName", "Dawg");
        assertEquals(1, packs.size());
    }

}