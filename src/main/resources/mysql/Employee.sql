-- Author Piyush
-- Employee Database Tables
--

create database EMPLOYEE;
use EMPLOYEE;

CREATE TABLE `Name` 
(
	`NameKey` INT NOT NULL AUTO_INCREMENT, 
	`FirstName` VARCHAR(30), 
	`MiddleName` VARCHAR(30), 
	`LastName` VARCHAR(30), 
	PRIMARY KEY(`NameKey`)
) ENGINE=INNODB DEFAULT CHARSET = utf8 AUTO_INCREMENT = 1;

CREATE TABLE `Company` 
(
	`CompanyKey` INT NOT NULL AUTO_INCREMENT, 
	`CompanyName` VARCHAR(40), 
	PRIMARY KEY(`CompanyKey`)
) ENGINE=INNODB DEFAULT CHARSET = UTF8 AUTO_INCREMENT=1;

CREATE TABLE `Employment` 
(
	`EmploymentKey` INT NOT NULL AUTO_INCREMENT, 
	`EmployeeId` VARCHAR(30) NOT NULL , 
	`NameKey` INT NOT NULL, 
	`CompanyKey` INT NOT NULL, 
	PRIMARY KEY (`EmploymentKey`), 
	INDEX `fk_NameKey_idx` (`NameKey` ASC), 
	INDEX `fk_CompanyKey_idx` (`CompanyKey` ASC), 
	CONSTRAINT `fk_NameKey` FOREIGN KEY (`NameKey` ) REFERENCES `Name` (`NameKey`) ON DELETE RESTRICT ON UPDATE CASCADE, 
	CONSTRAINT `fk_CompanyKey` FOREIGN KEY (`CompanyKey` ) REFERENCES `Company` (`CompanyKey`) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO `Name`(`FirstName`,`MiddleName`,`LastName`) VALUES
('Name1','Middle1','Last1'),
('Name2','','Last2'),
('Name3','Middle3','Last3');

INSERT INTO `Company`(`CompanyName`) VALUES
('Company1'),
('Company2');

INSERT INTO `Employment`(`EmployeeId`, `NameKey`, `CompanyKey`) VALUES
('CP1', 1, 1),
('CP2', 2, 1),
('CP1', 3, 2);