SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `caista`;
USE `caista`;

CREATE TABLE IF NOT EXISTS Supplier (
	name varchar(255) PRIMARY KEY,
	address varchar(255)
) engine = innoDB;

CREATE TABLE IF NOT EXISTS SupplierContact (
	supplier varchar(255),
	type varchar(255),
	value int,
	PRIMARY KEY(supplier,type,value),
	CONSTRAINT SupplierContactfk_1
		FOREIGN KEY (supplier) 
		REFERENCES Supplier(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS PurchaseOrder ( 
	type varchar(255), 
	no int, 
	date Date NOT NULL, 
	supplier varchar(255) NOT NULL,
	PRIMARY KEY (type,no),
	CONSTRAINT PurchaseOrderfk_1
		FOREIGN KEY (supplier) 
		REFERENCES Supplier(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB; 

CREATE TABLE IF NOT EXISTS ItemData (
	name varchar(255) PRIMARY KEY,
	description varchar(255),
	unitPrice int NOT NULL
) engine = innoDB;

CREATE TABLE IF NOT EXISTS POItem (
	type varchar(255),
	no int,
	itemname varchar(255),
	quantity int NOT NULL,
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
	ID int PRIMARY KEY,
	itemData varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	classification varchar(255) NOT NULL,
	CONSTRAINT InventoryItemfk_1
		FOREIGN KEY (itemData) 
		REFERENCES ItemData(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS HardwareItem (
	ID int PRIMARY KEY,
	CONSTRAINT HardwareItemfk_1
		FOREIGN KEY (ID) 
		REFERENCES InventoryItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Contract (
	hardware int,
	startDate Date NOT NULL,
	endDate Date NOT NULL,
	maintainanceCost int NOT NULL,
	PRIMARY KEY( hardware, startDate ),
	CONSTRAINT Contractfk_1
		FOREIGN KEY (hardware) 
		REFERENCES HardwareItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS ITAsset (
	ID int PRIMARY KEY,
	assetTag int NOT NULL,
	serviceTag int NOT NULL,
	deliveryDate Date NOT NULL,
	CONSTRAINT ITAssetfk_1
		FOREIGN KEY (ID) 
		REFERENCES HardwareItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Warranty (
	hardware int,
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
	ID int PRIMARY KEY,
	licenseKey varchar(255) NOT NULL,
	CONSTRAINT SoftwareItemfk_1
		FOREIGN KEY (ID) 
		REFERENCES InventoryItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Employee (
	ID int PRIMARY KEY,
	name varchar(255) NOT NULL
) engine = innoDB;

CREATE TABLE IF NOT EXISTS SoftwareAssignment (
	ID int,
	employee int,
	PRIMARY KEY( ID, employee ),
	CONSTRAINT SoftwareAssignmentfk_1
		FOREIGN KEY (ID) 
		REFERENCES SoftwareItem(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT SoftwareAssignmentfk_2
		FOREIGN KEY (employee) 
		REFERENCES Employee(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS AssetAssignment (
	ID int,
	employee int,
	PRIMARY KEY (ID, employee),
	CONSTRAINT AssetAssignmentfk_1
		FOREIGN KEY (ID)
		REFERENCES ITAsset(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT AssetAssignmentfk_2
		FOREIGN KEY (employee) 
		REFERENCES Employee(ID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS Project (
	name varchar(255) PRIMARY KEY
) engine = innoDB;

CREATE TABLE IF NOT EXISTS ProjectAssignment (
	projName varchar(255),
	employee int,
	PRIMARY KEY( projName, employee ),
	CONSTRAINT ProjectAssignmentfk_1
		FOREIGN KEY (projName) 
		REFERENCES Project(name)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT ProjectAssignmentfk_2
		FOREIGN KEY (employee)
		REFERENCES Employee(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE
) engine = innoDB;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;