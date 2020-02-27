/*Sql development*/

CREATE TABLE `Users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(200) DEFAULT NULL,
  `Field2` varchar(200) DEFAULT NULL,
  `Field3` varchar(200) DEFAULT NULL,
  `Field4` varchar(200) DEFAULT NULL,
  `Field5` varchar(200) DEFAULT NULL,
  `Field6` varchar(200) DEFAULT NULL,
  `Field7` varchar(200) DEFAULT NULL,
  `Field8` varchar(200) DEFAULT NULL,
  `Field9` varchar(200) DEFAULT NULL,
  `Field10` varchar(200) DEFAULT NULL,
  `Field11` varchar(200) DEFAULT NULL,
  `Field12` varchar(200) DEFAULT NULL,
  `Field13` varchar(200) DEFAULT NULL,
  `Field14` varchar(200) DEFAULT NULL,
  `Field15` varchar(200) DEFAULT NULL,
  `Field16` varchar(200) DEFAULT NULL,
  `Field17` varchar(200) DEFAULT NULL,
  `Field18` varchar(200) DEFAULT NULL,
  `CreatedBy` int NOT NULL,
  `CreatedTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedBy` int DEFAULT NULL,
  `UpdatedTS` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`)
);

CREATE TABLE Items (
  ItemID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  ItemName VARCHAR(200),
  Field2 VARCHAR(200),
  Field3 VARCHAR(200),
  Field4 VARCHAR(200),
  Field5 VARCHAR(200),
  Field6 VARCHAR(200),
  Field7 VARCHAR(200),
  Field8 VARCHAR(200),
  Field9 VARCHAR(200),
  Field10 VARCHAR(200),
  Field11 VARCHAR(200),
  Field12 VARCHAR(200),
  Field13 VARCHAR(200),
  Field14 VARCHAR(200),
  Field15 VARCHAR(200),
  Field16 VARCHAR(200),
  Field17 VARCHAR(200),
  Field18 VARCHAR(200), 
  CreatedBy INT NOT NULL,
  CreatedTS TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  UpdatedBy INT,
  UpdatedTS TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (CreatedBy) REFERENCES Users(UserID)
);

CREATE TABLE `Event_Master` (
  `EventID` int(11) NOT NULL AUTO_INCREMENT,
  `EventName` varchar(200) DEFAULT NULL,
  `Location` varchar(200) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL,
  `CreatedBy` int(11) NOT NULL,
  `CreatedTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `UpdatedTS` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`EventID`)
);
CREATE TABLE `Events` (
  `SeqNo` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `EventID` int(11) NOT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `City` varchar(200) DEFAULT NULL,
  `State` varchar(200) DEFAULT NULL,
  `Country` varchar(200) DEFAULT NULL,
  `Phone Number` bigint(20) DEFAULT NULL,
  `Hall Name` varchar(200) DEFAULT NULL,
  `CreatedBy` int(11) NOT NULL,
  `CreatedTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `UpdatedTS` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
   FOREIGN KEY (`EventID`) REFERENCES `Event_Master` (`EventID`)
) ;

