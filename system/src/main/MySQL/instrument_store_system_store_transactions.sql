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
-- Table structure for table `store_transactions`
--

DROP TABLE IF EXISTS `store_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_transactions` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `transaction_date` date NOT NULL,
  `purchase_description` text,
  `price` decimal(10,2) NOT NULL,
  `item_itemID` int NOT NULL,
  PRIMARY KEY (`transaction_id`,`item_itemID`),
  KEY `fk_store_transactions_item1_idx` (`item_itemID`),
  CONSTRAINT `fk_store_transactions_item1` FOREIGN KEY (`item_itemID`) REFERENCES `item` (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=2143964332 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_transactions`
--

LOCK TABLES `store_transactions` WRITE;
/*!40000 ALTER TABLE `store_transactions` DISABLE KEYS */;
INSERT INTO `store_transactions` VALUES (-1812993928,'2024-04-01','Roland TR-808',700.00,7),(-1657485607,'2024-04-01','Fender Stratocaster',300.00,2),(-600434607,'2024-04-01','Ludwig Supraphonic',150.00,3),(-113176405,'2024-04-02','Ludwig Vistalite',1200.00,16),(74,'2024-04-01','Moog Minimoog Model D',1000.00,6),(77,'2024-04-01','Steinway Model D',5000.00,9),(1714,'2024-04-02','Moog Modular System 55',5000.00,18),(51527,'2024-04-02','Gibson Super 400',2000.00,19),(68135,'2024-04-01','Yamaha DX7',500.00,4),(74492,'2024-04-01',' Gibson L-5',1000.00,8),(1537241,'2024-04-01','Wurlitzer 200A',300.00,10),(2063115,'2024-04-02','Fender Precision Bass',600.00,12),(2070644,'2024-04-01','Martin D-28',400.00,5),(76547653,'2024-04-02','ARP Odyssey',900.00,13),(1102941194,'2024-04-02','Fender Jazzmaster',900.00,17),(1377272542,'2024-04-01','Gibson Les Paul Standard',350.00,1),(2053454913,'2024-04-02','Gibson ES-335',800.00,11),(2107489982,'2024-04-02','Gibson Flying',1000.00,15);
/*!40000 ALTER TABLE `store_transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-30  6:40:13
