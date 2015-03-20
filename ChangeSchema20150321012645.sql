DROP TABLE SoftwareAssignment;
DROP TABLE AssetAssignment;
CREATE TABLE IF NOT EXISTS ItemAssignment (
	itemID INT,
    employeeID INT,
    startDate DATE NOT NULL,
	endDate DATE NOT NULL,
    PRIMARY KEY( itemID, employeeID ),
    CONSTRAINT ItemAssignmentfk_1
		FOREIGN KEY (itemID)
        REFERENCES InventoryItem(ID)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT ItemAssignmentfk_2
		FOREIGN KEY (employeeID)
        REFERENCES Employee(ID)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE IF NOT EXISTS ProjectAssignment (
	project VARCHAR(255),
    employeeID INT,
    PRIMARY KEY( project, employeeID ),
    CONSTRAINT ProjectAssignmentfk_1
		FOREIGN KEY (project)
        REFERENCES Project(name)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT ProjectAssignmentfk_2
		FOREIGN KEY (employeeID)
        REFERENCES Employee(ID)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) engine = innoDB;


