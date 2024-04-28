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
-- Table structure for table `inventory_analytics`
--

DROP TABLE IF EXISTS `inventory_analytics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_analytics` (
  `time` datetime NOT NULL,
  `item_stock_count` int DEFAULT NULL,
  `sales_revenue` double DEFAULT NULL,
  `repairs_performed_count` int DEFAULT NULL,
  `average_age_of_inventory` double DEFAULT NULL,
  `total_inventory_value` double DEFAULT NULL,
  `users_employeeID` varchar(45) NOT NULL,
  PRIMARY KEY (`time`,`users_employeeID`),
  KEY `fk_inventory_analytics_users1_idx` (`users_employeeID`),
  CONSTRAINT `fk_inventory_analytics_users1` FOREIGN KEY (`users_employeeID`) REFERENCES `users` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_analytics`
--

LOCK TABLES `inventory_analytics` WRITE;
/*!40000 ALTER TABLE `inventory_analytics` DISABLE KEYS */;
INSERT INTO `inventory_analytics` VALUES ('2024-04-20 17:32:48',8,44.34180627992536,3,92.21251875927004,42.508819335798286,'0'),('2024-04-20 17:32:50',1,94.90446430345042,2,40.35529588162867,11.231385300484465,'0'),('2024-04-27 19:39:16',8,8.798480200402048,1,68.46679382072716,31.424818413589733,'0'),('2024-04-28 00:27:59',3,4.921253193972442,0,46.70684743106668,37.75358461676072,'0');
/*!40000 ALTER TABLE `inventory_analytics` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-28  0:32:03
