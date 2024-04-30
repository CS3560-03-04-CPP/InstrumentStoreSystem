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
INSERT INTO `item` VALUES (1,'Gibson Les Paul Standard','Electric Guitar','Gibson','2024-04-17',591234,350,5000,'Iconic electric guitar model from the late 1950s known for its warm tone and sustain.'),(2,'Fender Stratocaster','Electric Guitar','Fender','2024-04-12',655678,300,4500,'Classic electric guitar model featuring a versatile sound and comfortable body design.'),(3,'Ludwig Supraphonic',' Snare Drum','Ludwig','2024-04-18',729876,150,1200,'Highly sought-after snare drum known for its crisp, versatile sound and durable construction.'),(4,'Yamaha DX7','Synthesizer','Yamaha','2024-04-18',833456,500,2500,'Revolutionary FM synthesizer offering a wide range of digital sounds and programmable parameters.'),(5,'Martin D-28','Acoustic Guitar','Martin','2024-04-17',28682345,400,6000,'Legendary acoustic guitar model featuring a rich, full-bodied tone and impeccable craftsmanship.'),(6,'Moog Minimoog Model D','Analog Synthesizer','Moog','2024-04-17',756789,1000,8000,'Classic analog synthesizer renowned for its fat bass sounds and expressive control interface.'),(7,'Roland TR-808','Drum Machine','Roland','2024-04-10',804321,700,5000,' Iconic drum machine beloved for its punchy analog drum sounds and influential in shaping modern music genres.'),(8,' Gibson L-5','Archtop Guitar','Gibson','2024-04-10',5358765,1000,15000,'Historic archtop guitar model favored by jazz musicians for its warm, resonant tone and elegant design.'),(9,'Steinway Model D','Grand Piano',' Steinway & Sons','2024-04-10',205432,5000,75000,'Exquisite grand piano renowned for its unparalleled craftsmanship and rich, expressive sound.'),(10,'Wurlitzer 200A','Electric Piano','Wurlitzer','2024-04-10',727890,300,2500,'Vintage electric piano known for its warm, bell-like tones and distinctive vibrato effect.'),(11,'Gibson ES-335','Semi-Hollow Electric Guitar','Gibson','2024-04-11',624567,800,7000,'Classic semi-hollow electric guitar with a versatile tone, favored by blues and rock guitarists alike.'),(12,'Fender Precision Bass','Electric Bass Guitar','Fender','2024-04-11',589876,600,8000,'Revolutionary electric bass guitar known for its solid build, deep tones, and pivotal role in shaping modern music.'),(13,'ARP Odyssey','Analog Synthesizer','ARP Instruments','2024-04-03',456464,900,6000,'Legendary analog synthesizer renowned for its versatile sound-shaping capabilities and distinctive filter characteristics.'),(14,'Rickenbacker 4001','Bass Guitar','Rickenbacker','1974-03-15',74321234,800,6500,'Iconic bass guitar known for its distinctive \"cresting wave\" body shape and bright articulate tone.'),(15,'Gibson Flying V','Electric Guitar','Gibson','1958-11-20',58112345,1000,8000,'Futuristic electric guitar design with a radical V-shaped body favored by players seeking a unique visual and sonic statement.'),(16,'Ludwig Vistalite','Drum Kit','Ludwige','1976-09-05',76099876,1200,10000,'Eye-catching acrylic drum kit known for its powerful projection and distinctive visual appeal popularized in the 1970s.'),(17,'Fender Jazzmaster','Electric Guitar','Fender','1961-08-25',61082456,900,7000,'Versatile electric guitar model with a smooth balanced tone and unique offset body design favored by indie and alternative rock musicians.'),(18,'Moog Modular System 55','Analog Synthesizer','Moog','1973-06-30',73056789,5000,30000,'Massive modular synthesizer system offering unparalleled flexibility and sonic exploration coveted by electronic music pioneers.'),(19,'Gibson Super 400','Archtop Guitar','Gibson','1940-12-12',40125432,2000,25000,'Exquisite archtop guitar revered for its stunning aesthetic superior craftsmanship and rich resonant tone often seen in the hands of jazz virtuosos.'),(20,'Allen Turing','Repair','Unknown','2024-04-30',0,0,0,'Temporary repair item');
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

-- Dump completed on 2024-04-30  6:40:14
