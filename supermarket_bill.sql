CREATE DATABASE  IF NOT EXISTS `supermarket` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `supermarket`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: supermarket
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contactnumber` varchar(255) DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `paymentmethod` varchar(255) DEFAULT NULL,
  `productdetails` json DEFAULT NULL,
  `total` int DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (3,'1234567899','dvm1@mailinator.com','h@gmail.com','dha','Cash','[{\"id\": 1, \"name\": \"Basmati Rice 25Kg Bag\", \"price\": 1400, \"total\": 7000, \"category\": \"Rice\", \"quantity\": \"5\"}]',7000,'BILL-1695366350754'),(7,'1234567898','dvm1@mailinator.com','dha@gmail.com','dha','Credit Card','[{\"id\": 1, \"name\": \"Basmati Rice 25Kg Bag\", \"price\": 1400, \"total\": 7000, \"category\": \"Rice\", \"quantity\": \"5\"}]',7000,'BILL-1695615091422'),(11,'8838407936','dvm1@mailinator.com','dvm986671@gmail.com','Dhanush V','Cash','[{\"id\": 1, \"name\": \"Basmati Rice 25Kg Bag\", \"price\": 1400, \"total\": 1400, \"category\": \"Rice\", \"quantity\": \"1\"}, {\"id\": 4, \"name\": \"Jeera Rice 1Kg\", \"price\": 110, \"total\": 110, \"category\": \"Rice\", \"quantity\": \"1\"}, {\"id\": 2, \"name\": \"Mtr oil 1Lt\", \"price\": 110, \"total\": 110, \"category\": \"Oil\", \"quantity\": \"1\"}, {\"id\": 3, \"name\": \"Cup Ice\", \"price\": 20, \"total\": 20, \"category\": \"IceCream\", \"quantity\": \"1\"}, {\"id\": 5, \"name\": \"Samosa\", \"price\": 10, \"total\": 10, \"category\": \"Snacks\", \"quantity\": \"1\"}, {\"id\": 8, \"name\": \"Wheat Flour 1Kg\", \"price\": 76, \"total\": 76, \"category\": \"Products \", \"quantity\": \"1\"}, {\"id\": 7, \"name\": \"onion 1Kg\", \"price\": 30, \"total\": 30, \"category\": \"Vegetables\", \"quantity\": \"1\"}, {\"id\": 9, \"name\": \"Wheat Bread 1Packet\", \"price\": 50, \"total\": 50, \"category\": \"Bread\", \"quantity\": \"1\"}, {\"id\": 11, \"name\": \"Apple 1Kg\", \"price\": 120, \"total\": 120, \"category\": \"Fruit\", \"quantity\": \"1\"}, {\"id\": 12, \"name\": \"Badam 1Kg\", \"price\": 800, \"total\": 800, \"category\": \"Dry Fruit\", \"quantity\": \"1\"}, {\"id\": 14, \"name\": \"Urad dal 1Kg\", \"price\": 130, \"total\": 130, \"category\": \"Dal\", \"quantity\": \"1\"}, {\"id\": 15, \"name\": \"Milk 1Lt\", \"price\": 34, \"total\": 170, \"category\": \"Dairy Products\", \"quantity\": \"5\"}]',3026,'BILL-1696006281811'),(12,'1234567899','dvm1@mailinator.com','abcd@gmail.com','dharish','Credit Card','[{\"id\": 4, \"name\": \"Jeera Rice 1Kg\", \"price\": 110, \"total\": 440, \"category\": \"Rice\", \"quantity\": \"4\"}, {\"id\": 15, \"name\": \"Milk 1Lt\", \"price\": 34, \"total\": 34, \"category\": \"Dairy Products\", \"quantity\": \"1\"}]',474,'BILL-1696045684582');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-30 10:30:06
