CREATE DATABASE  IF NOT EXISTS `caista` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `caista`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: caista
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `warrantyDuration` varchar(50) NOT NULL,
  `contractDuration` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `Adminfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('Admin','1 weeks','1 weeks');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `hardware` int(11) NOT NULL DEFAULT '0',
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `maintenanceCost` float NOT NULL,
  PRIMARY KEY (`hardware`,`startDate`),
  CONSTRAINT `Contractfk_1` FOREIGN KEY (`hardware`) REFERENCES `hardwareitem` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `count inventory`
--

DROP TABLE IF EXISTS `count inventory`;
/*!50001 DROP VIEW IF EXISTS `count inventory`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `count inventory` AS SELECT 
 1 AS `Item`,
 1 AS `Description`,
 1 AS `Type`,
 1 AS `Quantity`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hardwareitem`
--

DROP TABLE IF EXISTS `hardwareitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hardwareitem` (
  `ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `HardwareItemfk_1` FOREIGN KEY (`ID`) REFERENCES `inventoryitem` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hardwareitem`
--

LOCK TABLES `hardwareitem` WRITE;
/*!40000 ALTER TABLE `hardwareitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `hardwareitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!50001 DROP VIEW IF EXISTS `inventory`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `inventory` AS SELECT 
 1 AS `ID`,
 1 AS `name`,
 1 AS `description`,
 1 AS `unitprice`,
 1 AS `status`,
 1 AS `classification`,
 1 AS `invoiceNo`,
 1 AS `location`,
 1 AS `assetTag`,
 1 AS `serviceTag`,
 1 AS `deliveryDate`,
 1 AS `licenseKey`,
 1 AS `Warranty Start`,
 1 AS `Warranty End`,
 1 AS `Contract Start`,
 1 AS `Contract End`,
 1 AS `maintenanceCost`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `inventoryitem`
--

DROP TABLE IF EXISTS `inventoryitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventoryitem` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `itemData` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `classification` varchar(255) NOT NULL,
  `invoiceNo` varchar(45) NOT NULL,
  `location` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `inventoryitemfk_1` (`itemData`),
  CONSTRAINT `inventoryitemfk_1` FOREIGN KEY (`itemData`) REFERENCES `itemdata` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventoryitem`
--

LOCK TABLES `inventoryitem` WRITE;
/*!40000 ALTER TABLE `inventoryitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventoryitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itasset`
--

DROP TABLE IF EXISTS `itasset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itasset` (
  `ID` int(11) NOT NULL,
  `assetTag` int(11) NOT NULL,
  `serviceTag` varchar(45) NOT NULL,
  `deliveryDate` date NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `ITAssetfk_1` FOREIGN KEY (`ID`) REFERENCES `hardwareitem` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itasset`
--

LOCK TABLES `itasset` WRITE;
/*!40000 ALTER TABLE `itasset` DISABLE KEYS */;
/*!40000 ALTER TABLE `itasset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemassignment`
--

DROP TABLE IF EXISTS `itemassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemassignment` (
  `itemID` int(11) NOT NULL DEFAULT '0',
  `employeeID` int(11) NOT NULL DEFAULT '0',
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`itemID`,`employeeID`),
  KEY `ItemAssignmentfk_2` (`employeeID`),
  CONSTRAINT `ItemAssignmentfk_1` FOREIGN KEY (`itemID`) REFERENCES `inventoryitem` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ItemAssignmentfk_2` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemassignment`
--

LOCK TABLES `itemassignment` WRITE;
/*!40000 ALTER TABLE `itemassignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemassignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemdata`
--

DROP TABLE IF EXISTS `itemdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemdata` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `unitPrice` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemdata`
--

LOCK TABLES `itemdata` WRITE;
/*!40000 ALTER TABLE `itemdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poitem`
--

DROP TABLE IF EXISTS `poitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poitem` (
  `type` varchar(255) NOT NULL,
  `no` int(11) NOT NULL,
  `itemname` varchar(255) NOT NULL DEFAULT '',
  `quantityOrdered` int(11) NOT NULL,
  `quantityReceived` int(11) DEFAULT NULL,
  PRIMARY KEY (`type`,`no`,`itemname`),
  KEY `POItemfk_2` (`itemname`),
  CONSTRAINT `POItemfk_1` FOREIGN KEY (`type`, `no`) REFERENCES `purchaseorder` (`type`, `no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `POItemfk_2` FOREIGN KEY (`itemname`) REFERENCES `itemdata` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poitem`
--

LOCK TABLES `poitem` WRITE;
/*!40000 ALTER TABLE `poitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `poitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `name` varchar(255) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectassignment`
--

DROP TABLE IF EXISTS `projectassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projectassignment` (
  `project` varchar(255) NOT NULL DEFAULT '',
  `employeeID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`project`,`employeeID`),
  KEY `ProjectAssignmentfk_2` (`employeeID`),
  CONSTRAINT `ProjectAssignmentfk_1` FOREIGN KEY (`project`) REFERENCES `project` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ProjectAssignmentfk_2` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectassignment`
--

LOCK TABLES `projectassignment` WRITE;
/*!40000 ALTER TABLE `projectassignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `projectassignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseorder`
--

DROP TABLE IF EXISTS `purchaseorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseorder` (
  `type` varchar(255) NOT NULL,
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `supplier` varchar(255) NOT NULL,
  `invoiceNo` varchar(45) NOT NULL,
  `currency` varchar(45) NOT NULL,
  PRIMARY KEY (`type`,`no`),
  UNIQUE KEY `no` (`no`),
  UNIQUE KEY `invoiceNo` (`invoiceNo`),
  KEY `PurchaseOrderidx_1` (`supplier`),
  CONSTRAINT `PurchaseOrderfk_1` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorder`
--

LOCK TABLES `purchaseorder` WRITE;
/*!40000 ALTER TABLE `purchaseorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `softwareitem`
--

DROP TABLE IF EXISTS `softwareitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `softwareitem` (
  `ID` int(11) NOT NULL,
  `licenseKey` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `SoftwareItemfk_1` FOREIGN KEY (`ID`) REFERENCES `inventoryitem` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `softwareitem`
--

LOCK TABLES `softwareitem` WRITE;
/*!40000 ALTER TABLE `softwareitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `softwareitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `name` varchar(255) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliercontact`
--

DROP TABLE IF EXISTS `suppliercontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliercontact` (
  `supplier` varchar(255) NOT NULL DEFAULT '',
  `type` varchar(255) NOT NULL DEFAULT '',
  `value` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`supplier`,`type`,`value`),
  CONSTRAINT `SupplierContactfk_1` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliercontact`
--

LOCK TABLES `suppliercontact` WRITE;
/*!40000 ALTER TABLE `suppliercontact` DISABLE KEYS */;
/*!40000 ALTER TABLE `suppliercontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `employeeID` int(11) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Admin','A3THHHwldpgQs6XxAHKZhQ==',16);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty`
--

DROP TABLE IF EXISTS `warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warranty` (
  `hardware` int(11) NOT NULL DEFAULT '0',
  `startDate` date NOT NULL DEFAULT '0000-00-00',
  `endDate` date NOT NULL,
  PRIMARY KEY (`hardware`,`startDate`),
  CONSTRAINT `Warrantyfk_1` FOREIGN KEY (`hardware`) REFERENCES `hardwareitem` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty`
--

LOCK TABLES `warranty` WRITE;
/*!40000 ALTER TABLE `warranty` DISABLE KEYS */;
/*!40000 ALTER TABLE `warranty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `count inventory`
--

/*!50001 DROP VIEW IF EXISTS `count inventory`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `count inventory` AS select `id`.`name` AS `Item`,`id`.`description` AS `Description`,`ii`.`classification` AS `Type`,count(`ii`.`ID`) AS `Quantity` from (`inventoryitem` `ii` join `itemdata` `id`) where (`ii`.`itemData` = `id`.`name`) group by `id`.`name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `inventory`
--

/*!50001 DROP VIEW IF EXISTS `inventory`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `inventory` AS select `i`.`ID` AS `ID`,`id`.`name` AS `name`,`id`.`description` AS `description`,`id`.`unitPrice` AS `unitprice`,`i`.`status` AS `status`,`i`.`classification` AS `classification`,`i`.`invoiceNo` AS `invoiceNo`,`i`.`location` AS `location`,`ia`.`assetTag` AS `assetTag`,`ia`.`serviceTag` AS `serviceTag`,`ia`.`deliveryDate` AS `deliveryDate`,`s`.`licenseKey` AS `licenseKey`,`w`.`startDate` AS `Warranty Start`,`w`.`endDate` AS `Warranty End`,`c`.`startDate` AS `Contract Start`,`c`.`endDate` AS `Contract End`,`c`.`maintenanceCost` AS `maintenanceCost` from ((((((`inventoryitem` `i` left join `hardwareitem` `h` on((`i`.`ID` = `h`.`ID`))) left join `itasset` `ia` on((`h`.`ID` = `ia`.`ID`))) left join `softwareitem` `s` on((`i`.`ID` = `s`.`ID`))) left join `warranty` `w` on(((`w`.`hardware` = `h`.`ID`) and (`w`.`endDate` = (select max(`warranty`.`endDate`) from `warranty` where (`warranty`.`hardware` = `h`.`ID`)))))) left join `contract` `c` on(((`c`.`hardware` = `h`.`ID`) and (`c`.`endDate` = (select max(`contract`.`endDate`) from `contract` where (`contract`.`hardware` = `h`.`ID`)))))) join `itemdata` `id`) where (`i`.`itemData` = `id`.`name`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-23 12:05:25
