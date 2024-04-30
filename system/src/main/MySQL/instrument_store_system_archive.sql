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
-- Table structure for table `archive`
--

DROP TABLE IF EXISTS `archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `archive` (
  `itemID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `dateManufactured` date DEFAULT NULL,
  `serialNumber` int DEFAULT NULL,
  `manufacturerPrice` decimal(10,2) DEFAULT NULL,
  `retailPrice` decimal(10,2) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=28682351 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive`
--

LOCK TABLES `archive` WRITE;
/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
INSERT INTO `archive` VALUES (205441,'Steinway Model D','Grand Piano',' Steinway & Sons','2024-04-10',205432,5000.00,75000.00,'Exquisite grand piano renowned for its unparalleled craftsmanship and rich, expressive sound.'),(589888,'Fender Precision Bass','Electric Bass Guitar','Fender','2024-04-11',589876,600.00,8000.00,'Revolutionary electric bass guitar known for its solid build, deep tones, and pivotal role in shaping modern music.'),(591235,'Gibson Les Paul Standard','Electric Guitar','Gibson','2024-04-17',591234,350.00,5000.00,'Iconic electric guitar model from the late 1950s known for its warm tone and sustain.'),(624578,'Gibson ES-335','Semi-Hollow Electric Guitar','Gibson','2024-04-11',624567,800.00,7000.00,'Classic semi-hollow electric guitar with a versatile tone, favored by blues and rock guitarists alike.'),(655680,'Fender Stratocaster','Electric Guitar','Fender','2024-04-12',655678,300.00,4500.00,'Classic electric guitar model featuring a versatile sound and comfortable body design.'),(727900,'Wurlitzer 200A','Electric Piano','Wurlitzer','2024-04-10',727890,300.00,2500.00,'Vintage electric piano known for its warm, bell-like tones and distinctive vibrato effect.'),(729879,'Ludwig Supraphonic',' Snare Drum','Ludwig','2024-04-18',729876,150.00,1200.00,'Highly sought-after snare drum known for its crisp, versatile sound and durable construction.'),(756795,'Moog Minimoog Model D','Analog Synthesizer','Moog','2024-04-17',756789,1000.00,8000.00,'Classic analog synthesizer renowned for its fat bass sounds and expressive control interface.'),(804328,'Roland TR-808','Drum Machine','Roland','2024-04-10',804321,700.00,5000.00,' Iconic drum machine beloved for its punchy analog drum sounds and influential in shaping modern music genres.'),(833460,'Yamaha DX7','Synthesizer','Yamaha','2024-04-18',833456,500.00,2500.00,'Revolutionary FM synthesizer offering a wide range of digital sounds and programmable parameters.'),(5358773,' Gibson L-5','Archtop Guitar','Gibson','2024-04-10',5358765,1000.00,15000.00,'Historic archtop guitar model favored by jazz musicians for its warm, resonant tone and elegant design.'),(28682350,'Martin D-28','Acoustic Guitar','Martin','2024-04-17',28682345,400.00,6000.00,'Legendary acoustic guitar model featuring a rich, full-bodied tone and impeccable craftsmanship.');
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-30  6:40:14
