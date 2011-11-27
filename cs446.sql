-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: eventsfinder
-- ------------------------------------------------------
-- Server version	5.5.17

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` text,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Sports'),(2,'Concert'),(3,'Sight-seeing'),(4,'Bars'),(5,'Night Clubs');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `eventId` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `eventDescription` text,
  `eventName` varchar(255) DEFAULT NULL,
  `eventWebsite` varchar(255) DEFAULT NULL,
  `eventVideo` varchar(255) DEFAULT NULL,
  `eventPhoneNumber` varchar(255) DEFAULT NULL,
  `eventEmail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`eventId`),
  KEY `userID` (`userID`),
  KEY `category` (`category`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userId`),
  CONSTRAINT `event_ibfk_2` FOREIGN KEY (`category`) REFERENCES `category` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,3,1,'2012-10-02 15:33:16','2012-10-02 15:33:16','location1','event1','event1 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(2,3,1,'2012-10-02 15:33:16','2012-10-02 15:33:16','location2','event2','event2 name','event2.com','youtube.com/event2','5198078888','event2@event.com'),(3,4,1,'2012-10-02 15:33:16','2012-10-02 15:33:16','location3','event3','event3 name','event3.com','youtube.com/event3','5198078888','event3@event.com'),(4,4,1,'2012-10-02 15:33:16','2012-10-02 15:33:16','location4','event4','event4 name','event4.com','youtube.com/event4','5198078888','event4@event.com'),(5,1,4,'2012-11-02 15:33:16','2012-10-02 15:33:16','123 Sesame St.','test event1','test event1 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(6,2,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','505 King st.','test event2','test event2 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(7,3,2,'2012-11-02 15:33:16','2012-10-02 15:33:16','201 University Ave.','test event3','test event3 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(8,4,1,'2012-11-02 15:33:16','2012-10-02 15:33:16','1923 Weber St.','test event4','test event4 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(9,1,5,'2012-11-02 15:33:16','2012-10-02 15:33:16','123 Sesame St.','test event5','test event5 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(10,2,4,'2012-11-02 15:33:16','2012-10-02 15:33:16','505 King st.','test event6','test event6 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(11,3,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','201 University Ave.','test event7','test event7 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(12,4,2,'2012-11-02 15:33:16','2012-10-02 15:33:16','1923 Weber St.','test event8','test event8 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(13,4,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','123 Sesame St.','test event1','test event1 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(14,3,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','505 King st.','test event2','test event2 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(15,2,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','201 University Ave.','test event3','test event3 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(16,5,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','1923 Weber St.','test event4','test event4 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(17,1,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','123 Sesame St.','test event5','test event5 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(18,5,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','505 King st.','test event6','test event6 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(19,2,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','201 University Ave.','test event7','test event7 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(20,3,3,'2012-11-02 15:33:16','2012-10-02 15:33:16','1923 Weber St.','test event8','test event8 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(21,4,2,'2012-11-02 15:33:16','2012-11-03 15:33:16','123 Sesame St.','test event1','test event1 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(22,3,2,'2012-11-02 15:33:16','2012-11-03 15:33:16','505 King st.','test event2','test event2 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(23,2,2,'2012-11-02 15:33:16','2012-11-03 15:33:16','201 University Ave.','test event3','test event3 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(24,5,2,'2012-11-02 15:33:16','2012-11-03 15:33:16','1923 Weber St.','test event4','test event4 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(25,1,2,'2012-11-02 15:33:16','2012-11-03 15:33:16','123 Sesame St.','test event5','test event5 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(26,5,2,'2012-11-02 15:33:16','2012-11-03 15:33:16','505 King st.','test event6','test event6 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(27,2,2,'2012-11-02 15:33:16','2012-11-03 15:33:16','201 University Ave.','test event7','test event7 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(28,3,2,'2012-11-02 15:33:16','2012-11-03 15:33:16','1923 Weber St.','test event8','test event8 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(29,1,1,'2012-11-03 15:33:16','2012-11-05 15:33:16','123 Sesame St.','test event1','test event1 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(30,1,1,'2012-11-03 15:33:16','2012-11-05 15:33:16','505 King st.','test event2','test event2 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(31,1,1,'2012-11-03 15:33:16','2012-11-05 15:33:16','201 University Ave.','test event3','test event3 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(32,1,1,'2012-11-03 15:33:16','2012-11-05 15:33:16','1923 Weber St.','test event4','test event4 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(33,1,1,'2012-11-03 15:33:16','2012-11-05 15:33:16','123 Sesame St.','test event5','test event5 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(34,1,1,'2012-11-03 15:33:16','2012-11-05 15:33:16','505 King st.','test event6','test event6 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(35,1,1,'2012-11-03 15:33:16','2012-11-05 15:33:16','201 University Ave.','test event7','test event7 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(36,1,1,'2012-11-03 15:33:16','2012-11-05 15:33:16','1923 Weber St.','test event8','test event8 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(37,3,4,'2012-12-03 15:33:16','2012-12-05 15:33:16','123 Sesame St.','test event1','test event1 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(38,1,5,'2012-12-03 15:33:16','2012-12-05 15:33:16','505 King st.','test event2','test event2 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(39,2,4,'2012-12-03 15:33:16','2012-12-05 15:33:16','201 University Ave.','test event3','test event3 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(40,4,5,'2012-12-03 15:33:16','2012-12-05 15:33:16','1923 Weber St.','test event4','test event4 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com'),(41,5,4,'2012-12-03 15:33:16','2012-12-05 15:33:16','123 Sesame St.','test event5','test event5 name','event1.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event1@event.com'),(42,4,5,'2012-12-03 15:33:16','2012-12-05 15:33:16','505 King st.','test event6','test event6 name','event2.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event2@event.com'),(43,2,4,'2012-12-03 15:33:16','2012-12-05 15:33:16','201 University Ave.','test event7','test event7 name','event3.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event3@event.com'),(44,1,5,'2012-12-03 15:33:16','2012-12-05 15:33:16','1923 Weber St.','test event8','test event8 name','event4.com','http://www.youtube.com/embed/qtHMMWNmVkg?rel=0','5198078888','event4@event.com');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `idsession` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `sessionID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsession`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (8,1,'1psl30iuh6nfx');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time`
--

DROP TABLE IF EXISTS `time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time` (
  `timeId` int(11) NOT NULL AUTO_INCREMENT,
  `timeDisplayName` varchar(45) DEFAULT NULL,
  `timeInDays` int(11) DEFAULT NULL,
  PRIMARY KEY (`timeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time`
--

LOCK TABLES `time` WRITE;
/*!40000 ALTER TABLE `time` DISABLE KEYS */;
INSERT INTO `time` VALUES (1,'Upcoming',1),(2,'Tomorrow',1),(3,'In 2 days',2),(4,'In 5 days',5),(5,'In 1 week',7),(6,'In 2 weeks',14);
/*!40000 ALTER TABLE `time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `description` text,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`userId`),
  KEY `userType` (`userType`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`userType`) REFERENCES `usertype` (`userTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mike','mike',1,'mike y','admin mike','5194444444','mike@ye.com',0),(2,'martin','martin',1,'martin l','admin martin','5194444445','martin@lacombe.com',0),(3,'alice','alice',2,'alice ','moderator alice','5194444442','alice@test.com',0),(4,'bob','bob',3,'bob','user bob','5194444424','bob@test.com',1),(5,'test','test',3,'test','test account','5198075555','test@test.com',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertype` (
  `userTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `userType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES (1,'Administrator'),(2,'Moderator'),(3,'User');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-11-26 18:55:50
