package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Properties;

import com.choinoski.test.util.Database;

import static org.h2.util.SortedProperties.loadProperties;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type PackMember dao test.
 */
class PackMemberDaoTest {

    GenericDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(PackMember.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all members successfully.
     */
    @Test
    void getAllSuccess() {
        List<PackMember> members = dao.getAll();
        assertEquals(10, members.size());
    }


    /**
     * Verifies a packMember is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        PackMember retrievedMember = (PackMember) dao.getById(2);
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

        PackMember newMember = new PackMember("Scout", "80", "Golden Retriever", 'F',
                               LocalDate.of(2011, Month.MAY, 9),true,null);

        int id = pack.addMember(newMember);

        //int id = packDao.insert(newMember);

        assertNotEquals(0,id);
        PackMember insertedMember = (PackMember) dao.getById(id);
        assertTrue(newMember.equals(insertedMember));
        assertNotNull(insertedMember.getPack());
        assertTrue(pack.equals(insertedMember.getPack()));
    }


    /**
     * Verify successful delete of packMember
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verify successful update of pack_member
     */
    @Test
    void updateSuccess() {
        String description = "Bulldog";
        PackMember memberToUpdate = (PackMember) dao.getById(3);
        memberToUpdate.setBreed(description);
        dao.saveOrUpdate(memberToUpdate);
        PackMember retrievedMember = (PackMember) dao.getById(3);
        assertEquals(description, retrievedMember.getBreed());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<PackMember> members = dao.getByPropertyEqual("breed", "Chihuahua");
        assertEquals(1, members.size());
        assertEquals(7, members.get(0).getPackMemberNumber());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<PackMember> members = dao.getByPropertyLike("breed", "New");
        assertEquals(2, members.size());
    }

    /**
     * Verify successful get by property when using multiple properties
     */
    @Test
    void getByMultipleProperty() {

        List<PackMember> members = null;

        try {
            Properties properties = loadProperties("/home/student/IdeaProjects/PuppyPlaytime/src/test/resources/puppyPlaytime.properties");
            DataConverter converter = new DataConverter(properties);
            members = dao.getByMultipleProperty(
                    "weight", converter.getMinimumWeightForSize("XS"), converter.getMaximumWeightForSize("M"),
                    "dateOfBirth", LocalDate.now().minusYears(5), LocalDate.now().minusYears(0),
                    "sex", ' ', "intact",null);
        } catch (IOException e) {
            Assert.fail();
        }
        assertEquals(2, members.size());
    }

    /**
     * Verify successful get by property when using multiple properties
     */
    @Test
    void getByMultiplePropertyTwo() {

        List<PackMember> members = null;

        try {
            Properties properties = loadProperties("/home/student/IdeaProjects/PuppyPlaytime/src/test/resources/puppyPlaytime.properties");
            DataConverter converter = new DataConverter(properties);
            members = dao.getByMultipleProperty(
                    "weight", converter.getMinimumWeightForSize("S"), converter.getMaximumWeightForSize("XL"),
                    "dateOfBirth", LocalDate.now().minusYears(10), LocalDate.now().minusYears(0),
                    "sex", 'M', "intact",null);
        } catch (IOException e) {
            Assert.fail();
        }
        assertEquals(4, members.size());
    }

}