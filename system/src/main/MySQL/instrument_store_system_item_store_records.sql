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
-- Table structure for table `item_store_records`
--

DROP TABLE IF EXISTS `item_store_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_store_records` (
  `store_transactions_transaction_id` int NOT NULL,
  `store_records_invoice_number` varchar(100) NOT NULL,
  PRIMARY KEY (`store_records_invoice_number`,`store_transactions_transaction_id`),
  KEY `fk_item_store_records_store_transactions1_idx` (`store_transactions_transaction_id`),
  KEY `fk_item_store_records_store_records1_idx` (`store_records_invoice_number`),
  CONSTRAINT `fk_item_store_records_store_records1` FOREIGN KEY (`store_records_invoice_number`) REFERENCES `store_records` (`invoice_number`),
  CONSTRAINT `fk_item_store_records_store_transactions1` FOREIGN KEY (`store_transactions_transaction_id`) REFERENCES `store_transactions` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_store_records`
--

LOCK TABLES `item_store_records` WRITE;
/*!40000 ALTER TABLE `item_store_records` DISABLE KEYS */;
INSERT INTO `item_store_records` VALUES (-1812993928,'-1771922999'),(-1657485607,'101141493'),(-600434607,'-2012955832'),(-113176405,'-1267717611'),(74,'949105668'),(77,'-1689922829'),(1714,'776286421'),(51527,'1127386689'),(68135,'410087480'),(74492,'2016264166'),(1537241,'-2021532533'),(2063115,'-48703713'),(2070644,'-517648684'),(76547653,'1672474967'),(1102941194,'1225441431'),(1377272542,'-822522559'),(2053454913,'240643620'),(2107489982,'294678685');
/*!40000 ALTER TABLE `item_store_records` ENABLE KEYS */;
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
