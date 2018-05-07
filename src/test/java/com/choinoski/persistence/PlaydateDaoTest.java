package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import com.choinoski.entity.Playdate;
import com.choinoski.entity.Role;
import com.choinoski.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlaydateDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Playdate.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify the success of getting by id
     */
    @Test
    void testGetById() {

        Playdate retrievedPlaydate = (Playdate) dao.getById(1);
        assertNotNull(retrievedPlaydate);
        assertEquals("Barky Park", retrievedPlaydate.getPlaydateLocation());

    }

    /**
     * Verify the success of an update
     */
    @Test
    void testSaveOrUpdate() {
        String newLocation = "Over the Hill";
        Playdate playdateToUpdate = (Playdate) dao.getById(2);
        playdateToUpdate.setPlaydateLocation(newLocation);
        dao.saveOrUpdate(playdateToUpdate);

        Playdate retrievedPlaydate = (Playdate) dao.getById(2);
        assertEquals(newLocation, retrievedPlaydate.getPlaydateLocation());
    }

    /**
     * Verify the success of an insert
     */
    @Test
    void testInsert() {

        LocalDate newDate = LocalDate.of(2018,05,25);
        LocalTime newTime = LocalTime.parse("12:40");

        Playdate newPlaydate = new Playdate(2,
                "Tiny Park", newDate, newTime, "Pending", true);

        int id = dao.insert(newPlaydate);

        assertNotEquals(0,id);
        Playdate insertedPlaydate = (Playdate) dao.getById(id);
        assertTrue(insertedPlaydate.equals(newPlaydate));

    }

    /**
     * Verify the success of a delete
     */
    @Test
    void testDelete() {
        dao.delete(dao.getById(24));
        assertNull(dao.getById(24));
    }

    /**
     * Verify the success of getting all rows
     */
    @Test
    void testGetAll() {
        List<Playdate> playdates = dao.getAll();
        assertEquals(6, playdates.size());
    }

    /**
     * Verify the success of getting by specific data in a column
     */
    @Test
    void testGetByPropertyEqual() {
        List<Playdate> packs = dao.getByPropertyEqual("playdateLocation", "Lagoon Ave");
        assertEquals(1, packs.size());
    }

    /**
     * Verify the success of getting by a string that is in a column
     */
    @Test
    void testGetByPropertyLike() {
        List<Playdate> packs = dao.getByPropertyLike("playdateLocation", "Lagoo");
        assertEquals(1, packs.size());
    }

}
