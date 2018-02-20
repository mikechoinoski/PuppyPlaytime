package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import com.choinoski.test.util.Database;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type PackMember packMemberDao test.
 */
class PackMemberDaoTest {

    GenericDao packMemberDao;

    /**
     * Creating the packMemberDao.
     */
    @BeforeEach
    void setUp() {
        packMemberDao = new GenericDao(PackMember.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all members successfully.
     */
    @Test
    void getAllSuccess() {
        List<PackMember> members = packMemberDao.getAll();
        assertEquals(6, members.size());
    }


    /**
     * Verifies a packMember is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        PackMember retrievedMember = (PackMember) packMemberDao.getById(2);
        assertNotNull(retrievedMember);
        assertTrue(retrievedMember.getName().equals("Boomer"));
    }

    /**
     * Verify successful insert of a packMember
     */
    @Test
    void insertSuccess() {

        int        packId    = 2;

        GenericDao packDao   = new GenericDao(Pack.class);
        Pack       pack      = (Pack) packDao.getById(packId);

        PackMember newMember = new PackMember("Scout", "M", "Golden Retriever", 'F',
                               LocalDate.of(2011, Month.MAY, 9),
                               LocalDateTime.now(), LocalDateTime.now(), pack);

        pack.addMember(newMember);

        int id = packDao.insert(newMember);

        assertNotEquals(0,id);
        PackMember insertedMember = (PackMember) packMemberDao.getById(id);
        assertTrue(newMember.equals(insertedMember));
        assertNotNull(insertedMember.getPack());
        assertTrue(pack.equals(insertedMember.getPack()));
    }


    /**
     * Verify successful delete of packMember
     */
    @Test
    void deleteSuccess() {
        packMemberDao.delete(packMemberDao.getById(3));
        assertNull(packMemberDao.getById(3));
    }

    /**
     * Verify successful update of pack_member
     */
    @Test
    void updateSuccess() {
        String description = "Bulldog";
        PackMember memberToUpdate = (PackMember) packMemberDao.getById(3);
        memberToUpdate.setBreed(description);
        packMemberDao.saveOrUpdate(memberToUpdate);
        PackMember retrievedMember = (PackMember) packMemberDao.getById(3);
        assertEquals(description, retrievedMember.getBreed());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<PackMember> members = packMemberDao.getByPropertyEqual("breed", "Boxer");
        assertEquals(1, members.size());
        assertEquals(6, members.get(0).getPackMemberNumber());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<PackMember> members = packMemberDao.getByPropertyLike("breed", "B");
        assertEquals(4, members.size());
    }
}