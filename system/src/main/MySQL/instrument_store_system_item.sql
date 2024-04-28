CREATE DATABASE  IF NOT EXISTS `instrument_store_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `instrument_store_system`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: instrument_store_system
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `itemID` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `dateManufactured` date DEFAULT NULL,
  `serialNumber` int DEFAULT NULL,
  `manufacturerPrice` double DEFAULT NULL,
  `retailPrice` double DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Piano','Percussion','Kawai','2023-01-01',123456,2000,3000,'Acoustic piano by Kawai'),(2,'Piano','Percussion','Yamaha','2023-01-05',654321,2500,3500,'Acoustic piano by Yamaha'),(4,'Drums','Percussion','Ludwig','2023-02-01',111111,1000,1500,'Acoustic drums by Ludwig'),(6,'Drums','Percussion','Yamaha','2023-02-10',333333,1500,2000,'Acoustic drums by Yamaha'),(7,'Guitar','String','Fender','2023-03-01',444444,800,1200,'Acoustic/electric guitar by Fender'),(8,'Guitar','String','Gibson','2023-03-05',555555,1200,1800,'Acoustic/electric guitar by Gibson'),(9,'Guitar','String','Yamaha','2023-03-10',666666,900,1500,'Acoustic/electric guitar by Yamaha'),(10,'Bass','String','Fender','2023-04-01',777777,600,1000,'Electric bass by Fender'),(11,'Bass','String','Squier','2023-04-05',888888,400,800,'Electric bass by Squier'),(15,'Trumpet','Brass','Kanstul','2023-05-10',789789,1000,1700,'Trumpet by Kanstul'),(17,'Trombone','Brass','Bach','2023-06-05',111111,800,1300,'Trombone by Bach'),(18,'Trombone','Brass','Etude','2023-06-10',121212,600,1100,'Trombone by Etude'),(19,'Flute','Woodwind','Yamaha','2023-07-01',131313,600,1000,'Flute by Yamaha'),(20,'Flute','Woodwind','Powell','2023-07-05',141414,800,1200,'Flute by Powell'),(21,'Flute','Woodwind','Jupiter','2023-07-10',151515,700,1100,'Flute by Jupiter'),(22,'Clarinet','Woodwind','Buffet Crampon','2023-08-01',161616,900,1500,'Clarinet by Buffet Crampon'),(23,'Clarinet','Woodwind','Leitner & Kraus','2023-08-05',171717,700,1200,'Clarinet by Leitner & Kraus'),(25,'Organ','Keyboard','Vox','2023-09-01',191919,1500,2500,'Electric organ by Vox'),(27,'Organ','Keyboard','Hammond','2023-09-10',212121,2000,3000,'Electric organ by Hammond'),(29,'Synthesizer','Keyboard','Yamaha','2023-10-05',232323,2000,3000,'Synthesizer by Yamaha'),(30,'Synthesizer','Keyboard','Korg','2023-10-10',242424,1800,2800,'Synthesizer by Korg'),(32,'Amplifiers','Other','Fender','2023-11-05',262626,600,900,'Amplifier by Fender'),(33,'Amplifiers','Other','Marshall','2023-11-10',272727,700,1000,'Amplifier by Marshall'),(41,'elevatorsan','mover','elnor','2024-04-16',2165,200,300,'new elevator');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-28  0:32:02
