CREATE DATABASE  IF NOT EXISTS `caista` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `caista`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: caista
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `assetassignment`
--

DROP TABLE IF EXISTS `assetassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetassignment` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `employee` int(11) NOT NULL DEFAULT '0',
  `project` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`,`employee`,`project`),
  KEY `AssetAssignmentfk_2` (`employee`),
  KEY `AssetAssignmentfk_3` (`project`),
  CONSTRAINT `AssetAssignmentfk_1` FOREIGN KEY (`ID`) REFERENCES `itasset` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `AssetAssignmentfk_2` FOREIGN KEY (`employee`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `AssetAssignmentfk_3` FOREIGN KEY (`project`) REFERENCES `project` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetassignment`
--

LOCK TABLES `assetassignment` WRITE;
/*!40000 ALTER TABLE `assetassignment` DISABLE KEYS */;
INSERT INTO `assetassignment` VALUES (5638,937294795,'Code all the things'),(475,987654321,'Confirm Hoenn'),(7368,1234567890,'Woodstock');
/*!40000 ALTER TABLE `assetassignment` ENABLE KEYS */;
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
INSERT INTO `contract` VALUES (475,'2002-05-05','2050-12-12',746),(5638,'1980-12-14','2034-06-07',4000),(7368,'1985-10-26','2015-10-21',100),(9385,'2015-03-14','2015-03-14',444),(9386,'2015-03-14','2015-03-14',444),(9393,'2015-03-15','2015-03-15',300),(9394,'2015-03-16','2017-03-04',1000000);
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
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (937294795,'Giovanni Neve'),(987654321,'Emmett Lathrop'),(1234567890,'Martin Seamus');
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
INSERT INTO `hardwareitem` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(15),(16),(17),(18),(19),(475),(5638),(7368),(9385),(9386),(9393),(9394),(9395),(9396);
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
) ENGINE=InnoDB AUTO_INCREMENT=9397 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventoryitem`
--

LOCK TABLES `inventoryitem` WRITE;
/*!40000 ALTER TABLE `inventoryitem` DISABLE KEYS */;
INSERT INTO `inventoryitem` VALUES (1,'Dell Laptop','In Use','IT','12345','One World Square'),(2,'Dell Laptop','In Use','IT','12345','One World Square'),(3,'Dell Laptop','In Use','IT','12345','One World Square'),(4,'Dell Laptop','In Use','IT','12345','One World Square'),(5,'Dell Laptop','In Use','IT','12345','One World Square'),(6,'Dell Laptop','In Use','IT','12345','One World Square'),(7,'Dell Laptop','In Use','IT','12345','One World Square'),(8,'EPSON Projector','Junk','IT','12345','One World Square'),(9,'EPSON Projector','In Use','IT','12345','One World Square'),(10,'Stabilo Eraser','In Use','other','12344','One World Square'),(11,'Stabilo Eraser','In Use','other','12344','DAO'),(12,'Very Good Table','In Use','other','12344','One World Square'),(13,'Very Good Table','In Use','other','12344','DAO'),(14,'Very Good Table','In Use','other','12344','DAO'),(15,'8GB RAM','In Use','hardware','12334','One World Square'),(16,'8GB RAM','In Use','hardware','12334','One World Square'),(17,'i7 CPU','In Use','hardware','12334','One World Square'),(18,'i7 CPU','In Use','hardware','12334','One World Square'),(19,'i7 CPU','In Use','hardware','12334','One World Square'),(20,'Adobe Photoshop','In Use','software','17334','One World Square'),(21,'Adobe Photoshop','In Use','software','17334','One World Square'),(22,'Adobe Photoshop','In Use','software','17334','One World Square'),(23,'Adobe Photoshop','In Use','software','17334','One World Square'),(24,'Adobe Photoshop','In Use','software','17334','One World Square'),(25,'WinRAR','In Use','software','17334','One World Square'),(26,'WinRAR','In Use','software','17334','One World Square'),(27,'WinRAR','Junk','software','17334','One World Square'),(475,'Laptop','assigned','hardware','58923','3XR'),(999,'Red Guitar','Assigned','IT','iv90','1WS'),(4726,'Word Processor','not assigned','software','72947','3XR'),(4944,'Driver','fine','software','56456','2VT'),(5638,'Coffee Machine','assigned','hardware','85950','2VT'),(7368,'Printer','assigned','hardware','44645','1WS'),(9384,'Minecraft','blocky','software','46546','1WS'),(9385,'Green Guitar','Assigned','IT','iv7','1WS'),(9386,'Red Guitar','Assigned','IT','iv9','1WS'),(9387,'Rissa','Assigned','Software','iv9','1WS'),(9392,'Computer The Anti','Assigned','Software','iv9','1WS'),(9393,'Super conyo Ultra Man','Assigned','IT','8MCDO','1WS'),(9394,'Iron Man','Unassigned','IT','IRON123','Somewhere Else'),(9395,'The Incredible Hulk','Assigned','Non-IT','SMASH','1WS'),(9396,'The Incredible Hulk','Assigned','Non-IT','212','1WS');
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
INSERT INTO `itasset` VALUES (1,12345,'67890','2015-03-15'),(2,12346,'67891','2015-03-15'),(3,12347,'67892','2015-03-15'),(4,12348,'67893','2015-03-15'),(5,12349,'67894','2015-03-15'),(6,12355,'67895','2015-03-15'),(7,12365,'67896','2015-03-15'),(8,12375,'67897','2015-03-15'),(9,12385,'67898','2015-03-15'),(475,346866,'345RGRE','2007-12-05'),(5638,854532,'EF34JY','2004-12-21'),(7368,345354,'SDFJ43','2013-01-10'),(9385,22222,'333GG','2015-03-14'),(9386,22222,'333TS','2015-03-14'),(9393,11111,'222UM','2015-03-15'),(9394,1234,'IRON123','2015-03-16');
/*!40000 ALTER TABLE `itasset` ENABLE KEYS */;
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
INSERT INTO `itemdata` VALUES ('8GB RAM','More RAM',5000),('Adobe Photoshop','PS',10000),('Coffee Machine','Caffeine is integral to programmers',500),('Computer The Anti','Computer apocalypse',911),('Dell Laptop','DELL Laptop',35000),('Driver','Allows any hardware to run',10000),('EPSON Projector','EPSON Projector',20000),('ere','ere',444),('Green Guitar','A very green guitar',999),('Heisenberg','You\'re Goddamn Right!',1000),('i7 CPU','Very Fast CPU',19000),('Iron Man','Man of Iron',5000000),('item1','item1',12),('Laptop','Reliable and foldable',15000),('LolMan','I am the one who lols',12000),('Minecraft','Enhances creativity and generally fun',20),('Printer','Paper goes in documents come out',2500),('RANDOM','RANDOM',234),('Red Guitar','a very red guitar',888),('Rissa','Quindoza itam',45),('Shayane Tan','Shark bear student',11345),('Shyane Tan','hark bear ewan',12345),('Stabilo Eraser','Stabilo Eraser',80),('Stranger','I AM THE STRANGER',3400),('Super conyo Ultra Man','a very annoying ultra man',1300),('The Incredible Hulk','SMASH!',1000000),('Uber Red Guitar','people fall in love',13),('Very Good Table','Very Good Table',3900),('WinRAR','Do you have to buy this?',2000),('Word processor','Processes words',1500),('XGB WINONA JAKE','NETCENTRIC MEMEBRS',1234);
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
INSERT INTO `poitem` VALUES ('hardware',1,'Printer',11,9),('hardware',4,'Laptop',10,7),('hardware',5,'Coffee Machine',20,20),('Hardware',9,'Green Guitar',1,1),('Hardware',10,'Red Guitar',1,1),('Hardware',11,'Uber Red Guitar',1,1),('Hardware',12,'Item1',12,12),('Hardware',13,'Heisenberg',1000,1000),('Hardware',13,'LolMan',300,300),('Hardware',13,'Stranger',34,34),('Hardware',14,'ere',444,444),('software',2,'Driver',7,7),('software',3,'Minecraft',5,5),('software',6,'Word Processor',14,14);
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
INSERT INTO `project` VALUES ('Code all the things','2015-01-07','2015-04-27'),('Confirm Hoenn','2002-11-21','2014-11-21'),('Woodstock','1994-10-27','2016-03-13');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorder`
--

LOCK TABLES `purchaseorder` WRITE;
/*!40000 ALTER TABLE `purchaseorder` DISABLE KEYS */;
INSERT INTO `purchaseorder` VALUES ('hardware',1,'2002-11-09','Hewlett-Pakcard','44645'),('hardware',4,'2001-12-11','Hewlett-Packard','58923'),('hardware',5,'2006-09-01','Asus','85950'),('Hardware',7,'2015-03-11','Asus','invoiceNoTemp'),('Hardware',9,'2015-03-14','Ed Sheeran','iv7'),('Hardware',10,'2015-03-14','Taylor Swift','iv9'),('Hardware',11,'2015-03-14','Taylor Swift','iv10'),('Hardware',12,'2015-03-15','Pokachu Enterprise Shipping','iv0'),('Hardware',13,'2015-03-16','Pokachu Enterprise Shipping','iv12'),('Hardware',14,'2015-03-16','Asus','iv13'),('software',2,'2008-02-04','Asus','56456'),('software',3,'2013-07-23','Mojang','46546'),('software',6,'2015-01-15','Mojang','72947');
/*!40000 ALTER TABLE `purchaseorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `softwareassignment`
--

DROP TABLE IF EXISTS `softwareassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `softwareassignment` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `employee` int(11) NOT NULL DEFAULT '0',
  `project` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`,`employee`,`project`),
  KEY `SoftwareAssignmentfk_2` (`employee`),
  KEY `SoftwareAssignmentfk_3` (`project`),
  CONSTRAINT `SoftwareAssignmentfk_1` FOREIGN KEY (`ID`) REFERENCES `softwareitem` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `SoftwareAssignmentfk_2` FOREIGN KEY (`employee`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `SoftwareAssignmentfk_3` FOREIGN KEY (`project`) REFERENCES `project` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `softwareassignment`
--

LOCK TABLES `softwareassignment` WRITE;
/*!40000 ALTER TABLE `softwareassignment` DISABLE KEYS */;
INSERT INTO `softwareassignment` VALUES (4726,937294795,'Code all the things'),(9384,987654321,'Confirm Hoenn'),(4944,1234567890,'Woodstock');
/*!40000 ALTER TABLE `softwareassignment` ENABLE KEYS */;
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
INSERT INTO `softwareitem` VALUES (20,'1234-ecee-eee9'),(21,'1234-ecee-eee9'),(22,'1234-ecee-eee9'),(23,'1234-ecee-eee9'),(24,'1234-ecee-eee9'),(25,'1234-ecee-eee9'),(26,'1234-ecee-eee9'),(27,'1234-ecee-eee9'),(4726,'WP43GG'),(4944,'6B6579'),(9384,'FUEM87'),(9392,'CTAXX');
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
INSERT INTO `supplier` VALUES ('Asus','Taiwan','North Taiwan','Taipei'),('Christian Gabe','Philippines','Metro Manila','Quezon City'),('Ed Sheeran','Taiwan','North Taiwan','Taipei'),('Hewlett-Packard','United States of America','California','Palo Alto'),('LEGOHOUSE','Taiwan','North Taiwan','Taipei'),('MoKang','Sweden','Uppland','Stockholm'),('Pokachu Enterprise Shipping','Kanto','South','Pallet Town'),('Pokachu Shipping','Kanto','South','Pallet Town'),('Taylor Swift','Taiwan','North Taiwan','Taipei'),('Tristan Martin','zxc','asd','qwe'),('Winona','qwe','asd','zxc');
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
  `value` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`supplier`,`type`,`value`),
  CONSTRAINT `SupplierContactfk_1` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliercontact`
--

LOCK TABLES `suppliercontact` WRITE;
/*!40000 ALTER TABLE `suppliercontact` DISABLE KEYS */;
INSERT INTO `suppliercontact` VALUES ('Asus','FAX',1232),('Asus','fax',90823843),('Christian Gabe','FAX',112233),('Ed Sheeran','Cellphone',112233),('Hewlett-Packard','mobile',34587234),('LEGOHOUSE','FAX',12345),('Mojang','landline',9374834),('Pokachu Enterprise Shipping','Cellphone',12131312),('Taylor Swift','Cellphone',600),('Tristan Martin','FAX',1122),('Winona','FAX',111);
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
INSERT INTO `user` VALUES ('colress','power'),('hot pie','bread'),('RAFernandez','fuckmylifelol'),('sweng','caffeine');
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
INSERT INTO `warranty` VALUES (475,'2006-07-14','2015-06-21'),(5638,'1984-05-15','2047-08-26'),(7368,'1955-11-05','1985-12-10'),(9385,'2015-03-14','2015-03-14'),(9386,'2015-03-14','2015-03-14'),(9393,'2015-03-15','2015-03-15'),(9394,'2015-03-16','2017-03-04');
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

-- Dump completed on 2015-03-16  3:56:24
