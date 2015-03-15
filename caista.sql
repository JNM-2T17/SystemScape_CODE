SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `caista`;
USE `caista`;

CREATE TABLE IF NOT EXISTS Supplier (
	name VARCHAR(255) PRIMARY KEY,
	country VARCHAR(255),
    state VARCHAR(255),
    city VARCHAR(255)
) engine = innoDB;

CREATE TABLE IF NOT EXISTS SupplierContact (
	supplier VARCHAR(255),
	type VARCHAR(255),
	value INT,
	PRIMARY KEY(supplier,type,value),
	CONSTRAINT SupplierContactfk_1
		FOREIGN KEY (supplier) 
		REFERENCES Supplier(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS PurchaseOrder ( 
	type VARCHAR(255),
    no INT AUTO_INCREMENT, 
	date DATE NOT NULL, 
	supplier VARCHAR(255) NOT NULL,
    invoiceNo VARCHAR(45) NOT NULL UNIQUE,
	PRIMARY KEY (type,no),
	CONSTRAINT PurchaseOrderfk_1
		FOREIGN KEY (supplier) 
		REFERENCES Supplier(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	INDEX PurchaseOrderidx_1 (supplier ASC)
) engine = innoDB; 

CREATE TABLE IF NOT EXISTS ItemData (
	name VARCHAR(255) PRIMARY KEY,
	description VARCHAR(255),
	unitPrice INT NOT NULL
) engine = innoDB;

CREATE TABLE IF NOT EXISTS POItem (
	type VARCHAR(255),
	no VARCHAR(15),
	itemname VARCHAR(255),
	quantityOrdered INT NOT NULL,
    quantityReceived INT,
	PRIMARY KEY(type,no,itemname),
	CONSTRAINT POItemfk_1
		FOREIGN KEY (type,no) 
		REFERENCES PurchaseOrder(type,no)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT POItemfk_2
		FOREIGN KEY (itemname) 
		REFERENCES ItemData(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS InventoryItem (
	ID INT PRIMARY KEY,
	itemData VARCHAR(255) NOT NULL,
	status VARCHAR(255) NOT NULL,
	classification VARCHAR(255) NOT NULL,
    invoiceNo VARCHAR(45) NOT NULL,
    location VARCHAR(255) NOT NULL,
	CONSTRAINT InventoryItemfk_1
		FOREIGN KEY (itemData) 
		REFERENCES ItemData(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT InventoryItemfk_2
		FOREIGN KEY (invoiceNo)
        REFERENCES PurchaseOrder(invoiceNo)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	INDEX InventoryItemidx_1 (itemData ASC),
    INDEX InventoryItemidx_2 (invoiceNo ASC)
) engine = innoDB;

CREATE TABLE IF NOT EXISTS HardwareItem (
	ID INT PRIMARY KEY,
	CONSTRAINT HardwareItemfk_1
		FOREIGN KEY (ID) 
		REFERENCES InventoryItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Contract (
	hardware INT,
	startDate Date NOT NULL,
	endDate Date NOT NULL,
	maINTainanceCost FLOAT NOT NULL,
	PRIMARY KEY( hardware, startDate ),
	CONSTRAINT Contractfk_1
		FOREIGN KEY (hardware) 
		REFERENCES HardwareItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS ITAsset (
	ID INT PRIMARY KEY,
	assetTag INT NOT NULL,
	serviceTag INT NOT NULL,
	deliveryDate Date NOT NULL,
	CONSTRAINT ITAssetfk_1
		FOREIGN KEY (ID) 
		REFERENCES HardwareItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Warranty (
	hardware INT,
	startDate Date,
	endDate Date NOT NULL,
	PRIMARY KEY (hardware, startDate),
	CONSTRAINT Warrantyfk_1
		FOREIGN KEY (hardware) 
		REFERENCES HardwareItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS SoftwareItem (
	ID INT PRIMARY KEY,
	licenseKey VARCHAR(255) NOT NULL,
	CONSTRAINT SoftwareItemfk_1
		FOREIGN KEY (ID) 
		REFERENCES InventoryItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Employee (
	ID INT PRIMARY KEY,
	name VARCHAR(255) NOT NULL
) engine = innoDB;

CREATE TABLE IF NOT EXISTS SoftwareAssignment (
	ID INT,
	employee INT,
	project VARCHAR(255),
	PRIMARY KEY( ID, employee, project ),
	CONSTRAINT SoftwareAssignmentfk_1
		FOREIGN KEY (ID) 
		REFERENCES SoftwareItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT SoftwareAssignmentfk_2
		FOREIGN KEY (employee) 
		REFERENCES Employee(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT SoftwareAssignmentfk_3
		FOREIGN KEY (project)
		REFERENCES Project(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS AssetAssignment (
	ID INT,
	employee INT,
	project VARCHAR(255),
	PRIMARY KEY (ID, employee,project),
	CONSTRAINT AssetAssignmentfk_1
		FOREIGN KEY (ID)
		REFERENCES ITAsset(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT AssetAssignmentfk_2
		FOREIGN KEY (employee) 
		REFERENCES Employee(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT AssetAssignmentfk_3
		FOREIGN KEY (project)
		REFERENCES Project(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Project (
	name VARCHAR(255) PRIMARY KEY,
	startDate DATE NOT NULL,
	endDate DATE NOT NULL	
) engine = innoDB;

CREATE TABLE IF NOT EXISTS User (
	username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(45) NOT NULL
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Admin (
	username VARCHAR(255) PRIMARY KEY,
    CONSTRAINT Adminfk_1
		FOREIGN KEY (username)
        REFERENCES User(username)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) engine = innoDB;

CREATE VIEW `Inventory` AS
SELECT I.ID, ID.name, ID.description, ID.unitprice, status, classification, 
		invoiceNo, location, assetTag, serviceTag, deliveryDate, licenseKey, 
		W.startDate `Warranty Start`, W.endDate `Warranty End`, 
		C.startDate `Contract Start`, C.endDate `Contract End`, 
		C.maintainanceCost
FROM inventoryItem I 
	LEFT JOIN hardwareItem H ON I.ID = H.ID
    LEFT JOIN itasset IA ON H.ID = IA.ID
    LEFT JOIN softwareitem S ON I.ID = S.ID
    LEFT JOIN warranty W ON W.hardware = H.ID AND 
							W.endDate = (SELECT MAX(endDate)
										 FROM warranty
										 WHERE hardware = H.ID)
	LEFT JOIN contract C ON C.hardware = H.ID AND 
							C.endDate = (SELECT MAX(endDate)
										 FROM contract
										 WHERE hardware = H.ID), 
    itemdata ID
WHERE I.itemData = ID.name

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
