package com.choinoski.persistence;

import com.choinoski.entity.PackMember;
import com.choinoski.entity.Playdate;
import com.choinoski.entity.PlaydateMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.choinoski.test.util.Database;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the playdate dao.
 *
 * @author mchoinoski
 */
class PlaydateMemberDaoTest {

    GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(PlaydateMember.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify the success of getting by id
     */
    @Test
    void testGetById() {

        PlaydateMember retrievedPlaydateMember = (PlaydateMember) dao.getById(101);
        assertNotNull(retrievedPlaydateMember);
        assertEquals("Special", retrievedPlaydateMember.getStatus());

    }

    /**
     * Verify the success of an update
     */
    @Test
    void testSaveOrUpdate() {
        String newPlaydateMemberName = "Accept";
        PlaydateMember PlaydateMemberToUpdate = (PlaydateMember) dao.getById(1);
        PlaydateMemberToUpdate.setStatus(newPlaydateMemberName);
        dao.saveOrUpdate(PlaydateMemberToUpdate);

        PlaydateMember retrievedPlaydateMember = (PlaydateMember) dao.getById(1);
        assertEquals(newPlaydateMemberName, retrievedPlaydateMember.getStatus());
    }

    /**
     * Verify the success of an insert
     */
    @Test
    void testInsert() {

        GenericDao playdateDao = new GenericDao(Playdate.class);
        GenericDao packMemberDao = new GenericDao(PackMember.class);

        Playdate   playdate = (Playdate) playdateDao.getById(25);
        PackMember member   = (PackMember) packMemberDao.getById(4);

        PlaydateMember newPlaydateMember = new PlaydateMember("Accept",member, playdate);

        int id = dao.insert(newPlaydateMember);

        assertNotEquals(0,id);
        PlaydateMember insertedPlaydateMember = (PlaydateMember) dao.getById(id);
        assertTrue(insertedPlaydateMember.equals(newPlaydateMember));

    }

    /**
     * Verify the success of a delete
     */
    @Test
    void testDelete() {
        dao.delete(dao.getById(94));
        assertNull(dao.getById(94));
    }

    /**
     * Verify the success of getting all rows
     */
    @Test
    void testGetAll() {
        List<PlaydateMember> PlaydateMembers = dao.getAll();
        assertEquals(20, PlaydateMembers.size());
    }

    /**
     * Verify the success of getting by specific data in a column
     */
    @Test
    void testGetByPropertyEqual() {
        List<PlaydateMember> PlaydateMembers = dao.getByPropertyEqual("status", "Special");
        assertEquals(1, PlaydateMembers.size());
    }

    /**
     * Verify the success of getting by a string that is in a column
     */
    @Test
    void testGetByPropertyLike() {
        List<PlaydateMember> PlaydateMembers = dao.getByPropertyLike("status", "Spec");
        assertEquals(1, PlaydateMembers.size());
    }

}