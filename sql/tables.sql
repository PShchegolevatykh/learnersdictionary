DROP TABLE IF EXISTS `Profile`;
DROP TABLE IF EXISTS Entry;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS Category;

CREATE TABLE `User`
(
	Id int(10) NOT NULL AUTO_INCREMENT,
	Email varchar(60) NOT NULL,
	AuthToken varchar(32) NOT NULL,
	`Password` varchar(100) NOT NULL,
	Salt varchar(100) NOT NULL,
	PRIMARY KEY(Id)
);

CREATE TABLE `Profile`
(
	UserId int(10) NOT NULL,
	FirstName varchar(60) NOT NULL,
	LastName varchar(60) NOT NULL,
	PRIMARY KEY(UserId)
);

CREATE TABLE Category
(
	Id int(10) NOT NULL,
	`Name` varchar(60) NOT NULL,
	PRIMARY KEY(Id)
);

CREATE TABLE Entry
(
	Id int(10) NOT NULL AUTO_INCREMENT,
	Word varchar(100) NOT NULL,
	Meaning varchar(1000) NULL,
	CategoryId int(10) NOT NULL,
	UserId int(10) NOT NULL,
	Transcript varchar(100) NULL,
	Popularity int(10) NOT NULL DEFAULT 0,
	PRIMARY KEY(Id)
);	

ALTER TABLE `User`
	ADD CONSTRAINT UC_User_Email UNIQUE (Email),
	ADD CONSTRAINT UC_User_AuthToken UNIQUE (AuthToken);

ALTER TABLE `Profile`
	ADD CONSTRAINT FK_Profile_UserId FOREIGN KEY (UserId) REFERENCES `User`(Id);

ALTER TABLE Entry
	ADD CONSTRAINT UC_Entry_Word UNIQUE (Word),
	ADD CONSTRAINT FK_Entry_CategoryId FOREIGN KEY (CategoryId) REFERENCES Category (Id),
	ADD CONSTRAINT FK_Entry_UserId FOREIGN KEY (UserId) REFERENCES `User`(Id);

CREATE TRIGGER LowerCaseInsert BEFORE INSERT ON Entry FOR EACH ROW
SET NEW.Word = LOWER(NEW.Word);

CREATE TRIGGER LowerCaseUpdate BEFORE UPDATE ON Entry FOR EACH ROW
SET NEW.Word = LOWER(NEW.Word);