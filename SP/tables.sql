/*Sql development*/


CREATE TABLE `Users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(200) DEFAULT NULL,
  `Password` varchar(500) DEFAULT NULL,
  `FirstName` varchar(200) DEFAULT NULL,
  `LastName` varchar(200) DEFAULT NULL,
  `ConfirmPassword` varchar(500) DEFAULT NULL,
  `PhoneNumber` bigint(20) DEFAULT NULL,
  `AddressLine1` varchar(500) DEFAULT NULL,
  `AddressLine2` varchar(500) DEFAULT NULL,
  `AddressLine3` varchar(500) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `Field12` varchar(200) DEFAULT NULL,
  `Field13` varchar(200) DEFAULT NULL,
  `Field14` varchar(200) DEFAULT NULL,
  `Field15` varchar(200) DEFAULT NULL,
  `Field16` varchar(200) DEFAULT NULL,
  `Field17` varchar(200) DEFAULT NULL,
  `Field18` varchar(200) DEFAULT NULL,
  `CreatedBy` int(11) NOT NULL,
  `CreatedTS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UpdatedBy` int(11) DEFAULT NULL,
  `UpdatedTS` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



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
