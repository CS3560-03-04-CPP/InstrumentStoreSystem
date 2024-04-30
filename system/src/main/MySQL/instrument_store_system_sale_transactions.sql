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
-- Table structure for table `sale_transactions`
--

DROP TABLE IF EXISTS `sale_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_transactions` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `buyer_name` varchar(100) DEFAULT NULL,
  `card_number` varchar(16) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `CVV` varchar(4) DEFAULT NULL,
  `billing_address` varchar(255) DEFAULT NULL,
  `billing_postal_code` varchar(10) DEFAULT NULL,
  `transaction_amount` decimal(10,2) DEFAULT NULL,
  `item_itemID` int NOT NULL,
  PRIMARY KEY (`transaction_id`,`item_itemID`),
  KEY `fk_sale_transactions_item1_idx` (`item_itemID`),
  CONSTRAINT `fk_sale_transactions_item1` FOREIGN KEY (`item_itemID`) REFERENCES `item` (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=2106824352 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_transactions`
--

LOCK TABLES `sale_transactions` WRITE;
/*!40000 ALTER TABLE `sale_transactions` DISABLE KEYS */;
INSERT INTO `sale_transactions` VALUES (-2075143582,'Howels','6454654','2024-04-17','444','townes wed','55465',2500.00,10),(-1810179914,'Tim Charles','123654781','2024-04-18','456','123 apple rd','215874',75000.00,9),(-1618258098,'Gillian','54654657','2024-04-10','464','woah','544654',8000.00,12),(-1618257080,'Charles Mitch','54654774','2024-04-18','654','1231524 blv','5465',2500.00,10),(-1330251647,'Greatness the Third','2452525','2024-04-10','654','454646587 rd','231354',15000.00,8),(1513352,'Hill May','1654','2024-04-19','484','165467 rd','231654',2500.00,4),(1661326,'John smith','6546','2024-04-02','544','654654','654654',6000.00,13),(47744121,'Lowes Will','23131','2024-04-11','465','wfwfw rd','564654',7000.00,11),(1360084344,'Gab New','5546577','2024-04-24','654','65454','56464',6000.00,5),(1566881451,'Mike Hall','541654','2024-04-25','144','416546584 rd','5654564',15000.00,8),(1567037764,'Kale Weed','32146544','2024-04-24','845','115465 rd','21654',1200.00,3),(1596522560,'Hilly Mills','654564','2024-04-11','748','cart rd','5646847',4500.00,2),(1865672314,'John Smith','123456789000000','2024-04-11','456','8574 Baker Ave.Auburndale, FL','33823',6000.00,5),(2018076949,'Kim Willus','1231564','2024-04-17','645','888 rd','64586974',5000.00,1);
/*!40000 ALTER TABLE `sale_transactions` ENABLE KEYS */;
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
