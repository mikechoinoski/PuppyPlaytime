-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: puppy_playtime_test
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pack_member_review`
--

DROP TABLE IF EXISTS `pack_member_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pack_member_review` (
  `pack_member_review_nr` int(11) NOT NULL AUTO_INCREMENT,
  `pack_member_nr` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `date` date NOT NULL,
  `comments` text,
  `create_date` datetime NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pack_member_review_nr`),
  UNIQUE KEY `pack_member_review_pack_member_review_nr_uindex` (`pack_member_review_nr`),
  KEY `pack_member_review_pack_member_pack_member_nr_fk` (`pack_member_nr`),
  CONSTRAINT `pack_member_review_pack_member_pack_member_nr_fk` FOREIGN KEY (`pack_member_nr`) REFERENCES `pack_member` (`pack_member_nr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pack_member_review`
--

LOCK TABLES `pack_member_review` WRITE;
/*!40000 ALTER TABLE `pack_member_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `pack_member_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-04 19:53:12
