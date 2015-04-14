CREATE DATABASE  IF NOT EXISTS `caista` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `caista`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: caista
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
  PRIMARY KEY (`username`),
  CONSTRAINT `Adminfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('hot pie'),('RAFernandez');
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
INSERT INTO `contract` VALUES (1,'2015-04-09','2015-07-17',34500),(3,'2015-04-10','2015-11-26',345678),(4,'2015-04-10','2015-09-30',67890),(5,'2015-04-10','2015-10-13',56789),(6,'2015-04-16','2015-04-17',1231230);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `count inventory`
--

DROP TABLE IF EXISTS `count inventory`;
/*!50001 DROP VIEW IF EXISTS `count inventory`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `count inventory` (
  `Item` tinyint NOT NULL,
  `Description` tinyint NOT NULL,
  `Type` tinyint NOT NULL,
  `Quantity` tinyint NOT NULL
) ENGINE=MyISAM */;
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
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Karen Sicat','Activate'),(2,'Joey Murillo','Active'),(3,'Elenita Maureen','Active'),(4,'XGB','Active'),(5,'Loko','Active'),(6,'Lolo Mo','Active'),(7,'Mage','Active');
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
INSERT INTO `hardwareitem` VALUES (1),(3),(4),(5),(6),(8);
/*!40000 ALTER TABLE `hardwareitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!50001 DROP VIEW IF EXISTS `inventory`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `inventory` (
  `ID` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `description` tinyint NOT NULL,
  `unitprice` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `classification` tinyint NOT NULL,
  `invoiceNo` tinyint NOT NULL,
  `location` tinyint NOT NULL,
  `assetTag` tinyint NOT NULL,
  `serviceTag` tinyint NOT NULL,
  `deliveryDate` tinyint NOT NULL,
  `licenseKey` tinyint NOT NULL,
  `Warranty Start` tinyint NOT NULL,
  `Warranty End` tinyint NOT NULL,
  `Contract Start` tinyint NOT NULL,
  `Contract End` tinyint NOT NULL,
  `maintenanceCost` tinyint NOT NULL
) ENGINE=MyISAM */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventoryitem`
--

LOCK TABLES `inventoryitem` WRITE;
/*!40000 ALTER TABLE `inventoryitem` DISABLE KEYS */;
INSERT INTO `inventoryitem` VALUES (1,'Latitude D611','Unassigned','IT','98712','1WS'),(2,'Crystal Report 9 Developer Ed 5 Users FD','Unassigned','IT','210011453','1WS'),(3,'OptiPlex 745','Assigned','IT','210015772','1WS'),(4,'Latitude D611','Assigned','IT ','2323','1WS'),(5,'OptiPlex 745','Unassigned','IT','210014501','1WS'),(6,'Lol','Assigned','IT','213123','1WS'),(7,'Loko loko','Unassigned','Soft','2313212','Somewhere Else'),(8,'Haha','Assigned','Non-IT','31231','1WS');
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
INSERT INTO `itasset` VALUES (1,900047,'34N1J1S','2015-04-14'),(3,900561,'3HVNQ1s','2015-04-10'),(4,900522,'F4N1J1S','2015-04-10'),(5,900566,'91GHQ1S','2015-04-10'),(6,12312,'31231','2015-04-14');
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
INSERT INTO `itemdata` VALUES ('Adobe Robohelp Office X5','Software',18667),('Adobe Robohelp V6','Software',66000),('Crystal Report 9 Developer Ed 5 Users FD','Software',142242),('Haha','21		',131),('Latitude D611','Laptop ko',99500),('Latitude D612','Laptop',116000),('Latitude D620','Laptop',87900),('Loko loko','31231231',2312),('Lol','masakit',13),('OptiPlex','Desktop',54509),('OptiPlex 745','Desktop',54509);
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
INSERT INTO `poitem` VALUES ('Hard',1,'Latitude D611',2,2),('Hard',1,'Latitude D612',3,3),('Hard',3,'Latitude D611',1,1),('Hard',3,'Latitude D620',1,1),('Hard',4,'OptiPlex 745',1,1),('Soft',2,'Adobe Robohelp Office X5',3,3),('Soft',2,'Adobe Robohelp V6',1,1),('Soft',2,'Crystal Report 9 Developer Ed 5 Users FD',1,1);
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
INSERT INTO `project` VALUES ('Code all the thing','2015-01-19','2015-04-28'),('Code Master','2015-04-14','2015-04-14'),('Confirm Hoenn','2002-11-21','2014-11-21'),('Hi','2015-04-15','2015-04-15'),('Money','2015-04-12','2015-04-12'),('Nanay','2015-04-13','2015-04-13'),('Woodstock','1994-10-27','2000-03-13');
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
INSERT INTO `projectassignment` VALUES ('Code all the thing',1),('Code all the thing',2),('Code all the thing',3),('Code all the thing',4),('Code all the thing',5),('Code all the thing',6),('Code all the thing',7);
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
INSERT INTO `purchaseorder` VALUES ('Hard',1,'2015-04-09','Supplier 1','iv0'),('Hard',3,'2015-04-10','Supplier 1','iv2'),('Hard',4,'2015-04-10','Supplier 4','iv3'),('Soft',2,'2015-04-09','Supplier 3','iv1');
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
INSERT INTO `softwareitem` VALUES (2,'12234GG'),(7,'313212');
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
INSERT INTO `supplier` VALUES ('Supplier 1','Philippines','Metro Manila','Pasig City'),('Supplier 3','United States','California','Sacramento'),('Supplier 4','China','N/A','Guangzhou'),('Supplier 5','Philippines','Metro Manila','Quezon City');
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
INSERT INTO `suppliercontact` VALUES ('Supplier 1','FAX','321'),('Supplier 1','Telephone','312312'),('Supplier 3','FAX','1231231'),('Supplier 3','Telephone','18008159387'),('Supplier 5','Telephone','7889765');
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
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('colress','power'),('hello','hello'),('hello2','hello'),('hello3','hello'),('hot pie','bread'),('new','new'),('new2','new'),('newer','new'),('newnew','new'),('RAFernandez','fuckmylifelol'),('sweng','caffeine');
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
INSERT INTO `warranty` VALUES (1,'2015-04-17','2015-07-17'),(3,'2015-04-10','2015-11-26'),(4,'2015-04-10','2015-08-19'),(5,'2015-04-10','2015-12-31'),(6,'2015-04-16','2015-04-30');
/*!40000 ALTER TABLE `warranty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `count inventory`
--

/*!50001 DROP TABLE IF EXISTS `count inventory`*/;
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

/*!50001 DROP TABLE IF EXISTS `inventory`*/;
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

-- Dump completed on 2015-04-15  2:05:33
