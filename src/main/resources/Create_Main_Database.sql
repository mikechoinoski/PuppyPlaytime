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
-- Table structure for table `pack`
--

DROP TABLE IF EXISTS `pack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pack` (
  `pack_nr` int(11) NOT NULL AUTO_INCREMENT,
  `pack_name` varchar(30) DEFAULT NULL,
  `first_name` text,
  `last_name` text,
  `address` text,
  `phone_number` text,
  `email_address` text,
  `password` text,
  PRIMARY KEY (`pack_nr`),
  UNIQUE KEY `pack_nr_primary_key` (`pack_nr`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pack`
--

LOCK TABLES `pack` WRITE;
/*!40000 ALTER TABLE `pack` DISABLE KEYS */;
INSERT INTO `pack` VALUES (1,'admin','admin','admin','admin street','00000000000','admin@admin.com','admin'),(2,'user','Michael','Choinoski','1234 Willow Street','14142912312','michon@gmail','user'),(3,'Awesome Pack!','Andrew','Cutler','12 Ganderson Court','16081234567','random@yahoo.com','RedBike21'),(4,'Number 4',' ',' ',' ',' ',' ','password'),(5,'random_pack',' ',' ',' ',' ',' ','randompassword'),(6,'dog','Dawg','Boarder','30 Dog Territory Ave.','12622429123','Ilovedogs100@yahoo.com','dog');
/*!40000 ALTER TABLE `pack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pack_member`
--

DROP TABLE IF EXISTS `pack_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pack_member` (
  `pack_member_nr` int(11) NOT NULL AUTO_INCREMENT,
  `pack_nr` int(11) NOT NULL,
  `name` text NOT NULL,
  `weight` int(11) DEFAULT NULL,
  `breed` text,
  `sex` char(1) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `intact` tinyint(1) DEFAULT NULL,
  `picture_filename` text,
  `last_modified_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pack_member_nr`),
  UNIQUE KEY `pack_member_pack_member_nr_uindex` (`pack_member_nr`),
  KEY `pack_foreign_key` (`pack_nr`),
  CONSTRAINT `pack_foreign_key` FOREIGN KEY (`pack_nr`) REFERENCES `pack` (`pack_nr`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pack_member`
--

LOCK TABLES `pack_member` WRITE;
/*!40000 ALTER TABLE `pack_member` DISABLE KEYS */;
INSERT INTO `pack_member` VALUES (1,2,'Odo',15,'Mixed','M','2016-07-14',1,'test_odo.jpg','2018-04-29 16:05:57','2018-04-29 16:05:57'),(2,1,'Boomer',68,'Black Lab','M','2012-08-19',0,'test_black_lab.jpg','2018-04-29 16:05:57','2018-04-29 16:05:57'),(3,1,'Pow',175,'Great Dane','M','2014-03-25',0,'test_great_dane.jpg','2018-04-29 16:05:57','2018-04-29 16:05:57'),(4,3,'Happy',9,'Yorkie','M','2009-12-18',0,'test_yorkie.jpg','2018-04-29 16:05:57','2018-04-29 16:05:57'),(5,4,'Lucy',7,'Alaskan Eskimo Dog','F','2018-01-15',1,'test_alaskan_eskimo.jpg','2018-04-29 16:05:57','2018-04-29 16:05:57'),(6,5,'Missy',11,'Shih Tzu','F','2010-05-08',0,NULL,'2018-04-29 16:05:57','2018-04-29 16:05:57'),(7,6,'Tiny',5,'Chihuahua','F','2005-02-25',1,NULL,'2018-04-29 16:05:57','2018-04-29 16:05:57'),(8,6,'Grumpy',120,'Rottweiler','M','2011-07-17',0,NULL,'2018-04-29 16:05:57','2018-04-29 16:05:57'),(9,6,'Seymour',25,'New Guinea Singing Dog ','M','1988-07-17',0,NULL,'2018-04-29 16:05:57','2018-04-29 16:05:57'),(10,2,'Billy Bob',38,'Unknown!','M','2018-05-08',0,'user_Billy Bob.jpeg','2018-05-02 01:59:47','2018-05-02 01:59:47'),(11,6,'coolbean',14,'ffff','F','2018-05-01',1,'dog_coolbean.jpg','2018-05-02 03:33:53','2018-05-02 03:33:53');
/*!40000 ALTER TABLE `pack_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playdate`
--

DROP TABLE IF EXISTS `playdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playdate` (
  `playdate_nr` int(11) NOT NULL AUTO_INCREMENT,
  `organizing_pack_nr` int(11) NOT NULL,
  `playdate_location` text,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `status` text NOT NULL,
  `private_fl` tinyint(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`playdate_nr`),
  UNIQUE KEY `playdate_playdate_nr_uindex` (`playdate_nr`),
  KEY `playdate_pack_pack_nr_fk` (`organizing_pack_nr`),
  CONSTRAINT `playdate_pack_pack_nr_fk` FOREIGN KEY (`organizing_pack_nr`) REFERENCES `pack` (`pack_nr`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playdate`
--

LOCK TABLES `playdate` WRITE;
/*!40000 ALTER TABLE `playdate` DISABLE KEYS */;
INSERT INTO `playdate` VALUES (1,2,'fsfs','2015-07-03','11:02:00','pending',1,'2018-05-02 21:00:21','2018-05-02 21:00:21'),(2,2,'Barkkkkyy Parky','2018-05-14','11:40:00','pending',1,'2018-05-02 21:39:06','2018-05-02 21:39:06'),(3,2,'Cool Park','2018-09-04','11:55:00','pending',1,'2018-05-02 21:54:07','2018-05-02 21:54:07'),(4,2,'fdss','2018-05-09','14:18:00','pending',1,'2018-05-02 22:15:05','2018-05-02 22:15:05'),(5,2,'New Date','2020-07-15','01:52:00','pending',1,'2018-05-04 00:49:26','2018-05-04 00:49:26');
/*!40000 ALTER TABLE `playdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playdate_member`
--

DROP TABLE IF EXISTS `playdate_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playdate_member` (
  `playdate_member_nr` int(11) NOT NULL AUTO_INCREMENT,
  `playdate_nr` int(11) NOT NULL,
  `pack_member_nr` int(11) NOT NULL,
  `status` text,
  `create_date` datetime NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`playdate_member_nr`),
  UNIQUE KEY `playdate_member_playdate_member_nr_uindex` (`playdate_member_nr`),
  KEY `playdate_member_playdate_playdate_nr_fk` (`playdate_nr`),
  KEY `playdate_member_pack_member_pack_member_nr_fk` (`pack_member_nr`),
  CONSTRAINT `playdate_member_pack_member_pack_member_nr_fk` FOREIGN KEY (`pack_member_nr`) REFERENCES `pack_member` (`pack_member_nr`),
  CONSTRAINT `playdate_member_playdate_playdate_nr_fk` FOREIGN KEY (`playdate_nr`) REFERENCES `playdate` (`playdate_nr`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playdate_member`
--

LOCK TABLES `playdate_member` WRITE;
/*!40000 ALTER TABLE `playdate_member` DISABLE KEYS */;
INSERT INTO `playdate_member` VALUES (1,3,1,'Pending','2018-05-02 21:54:07','2018-05-02 21:54:07'),(2,3,10,'Pending','2018-05-02 21:54:08','2018-05-02 21:54:08'),(3,3,5,'Pending','2018-05-02 21:54:08','2018-05-02 21:54:08'),(4,3,9,'Pending','2018-05-02 21:54:08','2018-05-02 21:54:08'),(5,4,1,'Pending','2018-05-02 22:15:05','2018-05-02 22:15:05'),(6,4,10,'Pending','2018-05-02 22:15:05','2018-05-02 22:15:05'),(7,4,4,'Pending','2018-05-02 22:15:05','2018-05-02 22:15:05'),(8,4,5,'Pending','2018-05-02 22:15:05','2018-05-02 22:15:05'),(9,4,7,'Pending','2018-05-02 22:15:05','2018-05-02 22:15:05'),(10,5,1,'Pending','2018-05-04 00:49:26','2018-05-04 00:49:26'),(11,5,10,'Pending','2018-05-04 00:49:27','2018-05-04 00:49:27'),(12,5,2,'Pending','2018-05-04 00:49:27','2018-05-04 00:49:27'),(13,5,3,'Pending','2018-05-04 00:49:27','2018-05-04 00:49:27'),(14,5,11,'Pending','2018-05-04 00:49:28','2018-05-04 00:49:28');
/*!40000 ALTER TABLE `playdate_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role_name` varchar(30) DEFAULT NULL,
  `updateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pack_name` varchar(30) NOT NULL,
  `pack_nr` int(11) NOT NULL,
  PRIMARY KEY (`roleId`),
  KEY `role_pack_pack_nr_fk` (`pack_nr`),
  KEY `role_pack_name_index` (`pack_name`),
  CONSTRAINT `role_pack_pack_nr_fk` FOREIGN KEY (`pack_nr`) REFERENCES `pack` (`pack_nr`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2018-04-29 21:05:57','admin','2018-04-29 21:05:57','admin',1),(2,'2018-04-29 21:05:57','user','2018-04-29 21:05:57','user',2),(3,'2018-04-29 21:05:57','user','2018-04-29 21:05:57','Awesome Pack!',3),(4,'2018-04-29 21:05:57','user','2018-04-29 21:05:57','Number 4',4),(5,'2018-04-29 21:05:57','user','2018-04-29 21:05:57','random_pack',5),(6,'2018-04-29 21:05:57','user','2018-04-29 21:05:57','dog',6);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-04 19:53:49
