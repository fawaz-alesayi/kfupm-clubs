package tests;

import java.sql.*;

public final class TestDatabase {
	private static TestDatabase instance;
	private static Connection connection;

	private TestDatabase() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://" + "127.0.0.1/?allowMultiQueries=true", "root", "root");
		buildSchema(connection);
	}

	public static TestDatabase getInstance() throws Exception {
		if (instance == null) {
			instance = new TestDatabase();
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	private void buildSchema(Connection c) throws Exception {
    	final String SQL =
    			"-- --------------------------------------------------------\r\n"
    			+ "-- Host:                         127.0.0.1\r\n"
    			+ "-- Server version:               8.0.21 - MySQL Community Server - GPL\r\n"
    			+ "-- Server OS:                    Win64\r\n"
    			+ "-- HeidiSQL Version:             11.0.0.5919\r\n"
    			+ "-- --------------------------------------------------------\r\n"
    			+ "\r\n"
    			+ "/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;\r\n"
    			+ "/*!40101 SET NAMES utf8 */;\r\n"
    			+ "/*!50503 SET NAMES utf8mb4 */;\r\n"
    			+ "/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;\r\n"
    			+ "/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;\r\n"
    			+ "\r\n"
    			+ "\r\n"
    			+ "-- Dumping database structure for kcs_test_v2\r\n"
    			+ "DROP DATABASE IF EXISTS `kcs_test_v2`;\r\n"
    			+ "CREATE DATABASE IF NOT EXISTS `kcs_test_v2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;\r\n"
    			+ "USE `kcs_test_v2`;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.club\r\n"
    			+ "DROP TABLE IF EXISTS `club`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `club` (\r\n"
    			+ "  `ID` int NOT NULL AUTO_INCREMENT,\r\n"
    			+ "  `Name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `Address` varchar(40) DEFAULT NULL,\r\n"
    			+ "  `Phone` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `Des` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `DepartmentID` int DEFAULT NULL,\r\n"
    			+ "  `StatusID` int DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`),\r\n"
    			+ "  UNIQUE KEY `Name_UNIQUE` (`Name`),\r\n"
    			+ "  KEY `DepartmentID` (`DepartmentID`),\r\n"
    			+ "  KEY `StatusID` (`StatusID`),\r\n"
    			+ "  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`ID`),\r\n"
    			+ "  CONSTRAINT `club_ibfk_2` FOREIGN KEY (`StatusID`) REFERENCES `status` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.club: ~4 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `club` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `club` (`ID`, `Name`, `Address`, `Phone`, `Des`, `DepartmentID`, `StatusID`) VALUES\r\n"
    			+ "	(2, 'ISE CLUB', '3', '4', '5', 4, 1),\r\n"
    			+ "	(3, 'icsClub', 'bld 846', '544', 'hey', 4, 10),\r\n"
    			+ "	(4, 'EE club', 'bld 78', '51234', 'welcome', 4, 10),\r\n"
    			+ "	(12, 'ClubName', 'ClubAddress', '+123452152', 'A Test Club', 1, 11);\r\n"
    			+ "/*!40000 ALTER TABLE `club` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.clubadmin\r\n"
    			+ "DROP TABLE IF EXISTS `clubadmin`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `clubadmin` (\r\n"
    			+ "  `ClubID` int NOT NULL,\r\n"
    			+ "  `StudentId` int NOT NULL,\r\n"
    			+ "  `FromDate` date NOT NULL,\r\n"
    			+ "  `ToDate` date DEFAULT NULL,\r\n"
    			+ "  `Role` varchar(50) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ClubID`,`StudentId`,`FromDate`),\r\n"
    			+ "  KEY `StudentId` (`StudentId`),\r\n"
    			+ "  CONSTRAINT `clubadmin_ibfk_1` FOREIGN KEY (`ClubID`) REFERENCES `club` (`ID`),\r\n"
    			+ "  CONSTRAINT `clubadmin_ibfk_2` FOREIGN KEY (`StudentId`) REFERENCES `student` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.clubadmin: ~5 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `clubadmin` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `clubadmin` (`ClubID`, `StudentId`, `FromDate`, `ToDate`, `Role`) VALUES\r\n"
    			+ "	(1, 1, '2007-00-00', '2006-00-00', 'President'),\r\n"
    			+ "	(2, 1, '2020-12-01', '2020-12-05', 'Secretary'),\r\n"
    			+ "	(2, 3, '2007-00-00', '2003-00-00', 'President'),\r\n"
    			+ "	(3, 1, '2020-01-01', '2021-01-01', 'Prisedent'),\r\n"
    			+ "	(4, 2, '2020-01-01', '2021-01-01', 'Prisedent');\r\n"
    			+ "/*!40000 ALTER TABLE `clubadmin` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.clubmember\r\n"
    			+ "DROP TABLE IF EXISTS `clubmember`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `clubmember` (\r\n"
    			+ "  `ClubID` int NOT NULL,\r\n"
    			+ "  `StudentId` int NOT NULL,\r\n"
    			+ "  `FromDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n"
    			+ "  `ToDate` datetime DEFAULT NULL,\r\n"
    			+ "  `StatusID` int DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ClubID`,`StudentId`,`FromDate`),\r\n"
    			+ "  KEY `StudentId` (`StudentId`),\r\n"
    			+ "  KEY `StatusID` (`StatusID`),\r\n"
    			+ "  CONSTRAINT `clubmember_ibfk_1` FOREIGN KEY (`ClubID`) REFERENCES `club` (`ID`),\r\n"
    			+ "  CONSTRAINT `clubmember_ibfk_2` FOREIGN KEY (`StudentId`) REFERENCES `student` (`ID`),\r\n"
    			+ "  CONSTRAINT `clubmember_ibfk_3` FOREIGN KEY (`StatusID`) REFERENCES `status` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.clubmember: ~7 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `clubmember` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `clubmember` (`ClubID`, `StudentId`, `FromDate`, `ToDate`, `StatusID`) VALUES\r\n"
    			+ "	(1, 1, '2012-01-01 00:00:00', '2013-01-01 00:00:00', 12),\r\n"
    			+ "	(1, 1, '2019-10-11 00:00:00', '2020-10-11 00:00:00', 1),\r\n"
    			+ "	(1, 3, '2020-12-01 00:00:00', '2020-12-31 00:00:00', 1),\r\n"
    			+ "	(2, 3, '2020-12-01 00:00:00', '2020-12-31 00:00:00', 1),\r\n"
    			+ "	(3, 3, '2020-12-01 00:00:00', '2020-12-31 00:00:00', 1),\r\n"
    			+ "	(4, 1, '2020-12-01 00:00:00', '2021-01-29 00:00:00', 1),\r\n"
    			+ "	(4, 3, '2020-12-01 00:00:00', '2020-12-31 00:00:00', 1);\r\n"
    			+ "/*!40000 ALTER TABLE `clubmember` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.club_applicant\r\n"
    			+ "DROP TABLE IF EXISTS `club_applicant`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `club_applicant` (\r\n"
    			+ "  `id` int NOT NULL,\r\n"
    			+ "  `student_id` int NOT NULL,\r\n"
    			+ "  `club_id` int NOT NULL,\r\n"
    			+ "  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n"
    			+ "  PRIMARY KEY (`id`),\r\n"
    			+ "  KEY `student_id_idx` (`student_id`),\r\n"
    			+ "  KEY `club_id_idx` (`club_id`),\r\n"
    			+ "  CONSTRAINT `club_id` FOREIGN KEY (`club_id`) REFERENCES `club` (`ID`),\r\n"
    			+ "  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.club_applicant: ~0 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `club_applicant` DISABLE KEYS */;\r\n"
    			+ "/*!40000 ALTER TABLE `club_applicant` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.department\r\n"
    			+ "DROP TABLE IF EXISTS `department`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `department` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `phone` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `email` varchar(50) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.department: ~4 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `department` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `department` (`ID`, `name`, `phone`, `email`) VALUES\r\n"
    			+ "	(1, 'ICS Department', '135123456', 'ics@kfupm.edu.sa'),\r\n"
    			+ "	(2, 'ISE Department', '135654321', 'ise@kfupm.edu.sa'),\r\n"
    			+ "	(3, 'EE Department', '135123789', 'ee@kfupm.edu.sa'),\r\n"
    			+ "	(4, 'Math Department', '135987321', 'math@kfupm.edu.sa');\r\n"
    			+ "/*!40000 ALTER TABLE `department` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.project\r\n"
    			+ "DROP TABLE IF EXISTS `project`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `project` (\r\n"
    			+ "  `ID` int NOT NULL AUTO_INCREMENT,\r\n"
    			+ "  `Name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `ProjectTypeID` int DEFAULT NULL,\r\n"
    			+ "  `clubID` int NOT NULL,\r\n"
    			+ "  `des` varchar(100) DEFAULT NULL,\r\n"
    			+ "  `StartDate` datetime DEFAULT CURRENT_TIMESTAMP,\r\n"
    			+ "  `EndDate` datetime DEFAULT NULL,\r\n"
    			+ "  `StatusID` int DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`),\r\n"
    			+ "  KEY `ProjectTypeID` (`ProjectTypeID`),\r\n"
    			+ "  KEY `clubID` (`clubID`),\r\n"
    			+ "  KEY `StatusID` (`StatusID`),\r\n"
    			+ "  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`ProjectTypeID`) REFERENCES `projecttype` (`ID`),\r\n"
    			+ "  CONSTRAINT `project_ibfk_2` FOREIGN KEY (`clubID`) REFERENCES `club` (`ID`),\r\n"
    			+ "  CONSTRAINT `project_ibfk_3` FOREIGN KEY (`StatusID`) REFERENCES `status` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB AUTO_INCREMENT=200004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.project: ~6 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `project` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `project` (`ID`, `Name`, `ProjectTypeID`, `clubID`, `des`, `StartDate`, `EndDate`, `StatusID`) VALUES\r\n"
    			+ "	(1, 'COURSE RESOURCES WEBSITE', 1, 2, 'Create website that has helpful resources for students', '2019-10-11 00:00:00', '2020-10-11 00:00:00', 7),\r\n"
    			+ "	(2, 'COURSE RESOURCES library', 1, 2, 'Create website that has helpful resources for students', '2016-10-17 00:00:00', '2018-07-11 00:00:00', 7),\r\n"
    			+ "	(3, 'COURSE RESOURCES WEBSITE', 1, 2, 'Create website that has helpful resources for students', '2009-10-11 00:00:00', '2010-05-11 00:00:00', 7),\r\n"
    			+ "	(4, 'tester', 4, 4, 'hello', '2021-01-02 00:00:00', '2021-01-01 00:00:00', 7);\r\n"
    			+ "/*!40000 ALTER TABLE `project` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.projecttype\r\n"
    			+ "DROP TABLE IF EXISTS `projecttype`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `projecttype` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `Name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `des` varchar(100) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.projecttype: ~4 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `projecttype` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `projecttype` (`ID`, `Name`, `des`) VALUES\r\n"
    			+ "	(1, 'Programming', 'Programming Projects'),\r\n"
    			+ "	(2, 'Real world', 'Real World Projects'),\r\n"
    			+ "	(3, 'Design', 'Design Projects'),\r\n"
    			+ "	(4, 'Volunteering', 'Volunteering Projects');\r\n"
    			+ "/*!40000 ALTER TABLE `projecttype` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.projresource\r\n"
    			+ "DROP TABLE IF EXISTS `projresource`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `projresource` (\r\n"
    			+ "  `ProjectID` int NOT NULL,\r\n"
    			+ "  `ResourceID` int NOT NULL,\r\n"
    			+ "  `FromDate` date DEFAULT NULL,\r\n"
    			+ "  `ToDate` date DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ProjectID`,`ResourceID`),\r\n"
    			+ "  KEY `ResourceID` (`ResourceID`),\r\n"
    			+ "  CONSTRAINT `projresource_ibfk_1` FOREIGN KEY (`ResourceID`) REFERENCES `resource` (`ID`),\r\n"
    			+ "  CONSTRAINT `projresource_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.projresource: ~0 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `projresource` DISABLE KEYS */;\r\n"
    			+ "/*!40000 ALTER TABLE `projresource` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.resource\r\n"
    			+ "DROP TABLE IF EXISTS `resource`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `resource` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `Name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `Des` varchar(100) DEFAULT NULL,\r\n"
    			+ "  `ResourceTypeID` int DEFAULT NULL,\r\n"
    			+ "  `StatusID` int DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`),\r\n"
    			+ "  KEY `ResourceTypeID` (`ResourceTypeID`),\r\n"
    			+ "  KEY `StatusID` (`StatusID`),\r\n"
    			+ "  CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`ResourceTypeID`) REFERENCES `resourcetype` (`ID`),\r\n"
    			+ "  CONSTRAINT `resource_ibfk_2` FOREIGN KEY (`StatusID`) REFERENCES `status` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.resource: ~0 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `resource` DISABLE KEYS */;\r\n"
    			+ "/*!40000 ALTER TABLE `resource` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.resourcetype\r\n"
    			+ "DROP TABLE IF EXISTS `resourcetype`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `resourcetype` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `Name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `Des` varchar(100) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.resourcetype: ~0 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `resourcetype` DISABLE KEYS */;\r\n"
    			+ "/*!40000 ALTER TABLE `resourcetype` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.rule\r\n"
    			+ "DROP TABLE IF EXISTS `rule`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `rule` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `clubID` int NOT NULL,\r\n"
    			+ "  `des` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `StatusID` int DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`),\r\n"
    			+ "  KEY `StatusID` (`StatusID`),\r\n"
    			+ "  KEY `clubID` (`clubID`),\r\n"
    			+ "  CONSTRAINT `rule_ibfk_1` FOREIGN KEY (`StatusID`) REFERENCES `status` (`ID`),\r\n"
    			+ "  CONSTRAINT `rule_ibfk_2` FOREIGN KEY (`clubID`) REFERENCES `club` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.rule: ~0 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `rule` DISABLE KEYS */;\r\n"
    			+ "/*!40000 ALTER TABLE `rule` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.status\r\n"
    			+ "DROP TABLE IF EXISTS `status`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `status` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `StatusTypeID` int DEFAULT NULL,\r\n"
    			+ "  `name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `des` varchar(50) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`),\r\n"
    			+ "  KEY `StatusTypeID` (`StatusTypeID`),\r\n"
    			+ "  CONSTRAINT `status_ibfk_1` FOREIGN KEY (`StatusTypeID`) REFERENCES `statustype` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.status: ~18 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `status` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `status` (`ID`, `StatusTypeID`, `name`, `des`) VALUES\r\n"
    			+ "	(1, 1, 'Freshman', '-'),\r\n"
    			+ "	(2, 1, 'Sophomore', '-'),\r\n"
    			+ "	(3, 1, 'Junior', '-'),\r\n"
    			+ "	(4, 1, 'Senior', '-'),\r\n"
    			+ "	(5, 2, 'Enforced', 'Rule Is Active'),\r\n"
    			+ "	(6, 2, 'Disapproved', 'Rule Is Not Active'),\r\n"
    			+ "	(7, 3, 'Ended', 'Project Has Finished'),\r\n"
    			+ "	(8, 3, 'Ready', 'Ready for Event'),\r\n"
    			+ "	(9, 3, 'Waiting', 'Waiting for Materials'),\r\n"
    			+ "	(10, 4, 'Active', 'Club Is Active'),\r\n"
    			+ "	(11, 4, 'Inactive', 'Club Is Inactive'),\r\n"
    			+ "	(12, 5, 'Active', 'Member Is Active'),\r\n"
    			+ "	(13, 5, 'Onhold', 'Member Is Onhold'),\r\n"
    			+ "	(14, 5, 'Inactive', 'Member Is Inactive'),\r\n"
    			+ "	(15, 6, 'In Stock', 'Resource Available'),\r\n"
    			+ "	(16, 6, 'Waiting', 'Waiting for Shipment'),\r\n"
    			+ "	(17, 6, 'Currently Unavailable', 'Currently Out of Stock'),\r\n"
    			+ "	(18, 6, 'Discontinued', 'Resource Discountinued');\r\n"
    			+ "/*!40000 ALTER TABLE `status` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.statustype\r\n"
    			+ "DROP TABLE IF EXISTS `statustype`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `statustype` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `Name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `Des` varchar(50) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.statustype: ~6 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `statustype` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `statustype` (`ID`, `Name`, `Des`) VALUES\r\n"
    			+ "	(1, 'Student Status', 'Student Class Standing '),\r\n"
    			+ "	(2, 'Rule Status', 'Current Status of a Rule'),\r\n"
    			+ "	(3, 'Project Status', 'Current Status of Project'),\r\n"
    			+ "	(4, 'Club Status', 'Current Club Status'),\r\n"
    			+ "	(5, 'Club Member Status', 'Current Status of Club Member'),\r\n"
    			+ "	(6, 'Resource Status', 'Current Status of a Resource');\r\n"
    			+ "/*!40000 ALTER TABLE `statustype` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.student\r\n"
    			+ "DROP TABLE IF EXISTS `student`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `student` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `Fname` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `Lname` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `phone` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `StatusID` int DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`),\r\n"
    			+ "  KEY `StatusID` (`StatusID`),\r\n"
    			+ "  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`StatusID`) REFERENCES `status` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.student: ~9 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `student` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `student` (`ID`, `Fname`, `Lname`, `phone`, `StatusID`) VALUES\r\n"
    			+ "	(1, 'Mohammed', 'Ali', '512345678', 1),\r\n"
    			+ "	(2, 'Abdullah', 'Khaled', '587654321', 2),\r\n"
    			+ "	(3, 'Nasser', 'Habib', '521548521', 3),\r\n"
    			+ "	(4, 'Ali', 'Saleh', '554875215', 4),\r\n"
    			+ "	(5, 'Saleh', 'Ali', '514345678', 4),\r\n"
    			+ "	(6, 'Kareem', 'Khaled', '587654321', 3),\r\n"
    			+ "	(7, 'Mohsen', 'Habib', '52155555', 3),\r\n"
    			+ "	(8, 'Shaker', 'Saleh', '554875515', 4),\r\n"
    			+ "	(2017, 'Ali', 'Al-Ahmadi', '05599553486', 1);\r\n"
    			+ "/*!40000 ALTER TABLE `student` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.user\r\n"
    			+ "DROP TABLE IF EXISTS `user`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `user` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `UserTypeId` int DEFAULT NULL,\r\n"
    			+ "  `Password` varchar(50) DEFAULT NULL,\r\n"
    			+ "  `status` varchar(30) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.user: ~4 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `user` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `user` (`ID`, `UserTypeId`, `Password`, `status`) VALUES\r\n"
    			+ "	(1, 1, '1', 'ACTIVE'),\r\n"
    			+ "	(2, 2, '2', 'ACTIVE'),\r\n"
    			+ "	(3, 3, '3', 'ACTIVE'),\r\n"
    			+ "	(4, 4, '4', 'ACTIVE');\r\n"
    			+ "/*!40000 ALTER TABLE `user` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.usertype\r\n"
    			+ "DROP TABLE IF EXISTS `usertype`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `usertype` (\r\n"
    			+ "  `ID` int NOT NULL,\r\n"
    			+ "  `Name` varchar(50) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.usertype: ~4 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `usertype` (`ID`, `Name`) VALUES\r\n"
    			+ "	(1, 'SystemAdmin'),\r\n"
    			+ "	(2, 'ClubAdmin'),\r\n"
    			+ "	(3, 'ClubMember'),\r\n"
    			+ "	(4, 'Guest');\r\n"
    			+ "/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "-- Dumping structure for table kcs_test_v2.workson\r\n"
    			+ "DROP TABLE IF EXISTS `workson`;\r\n"
    			+ "CREATE TABLE IF NOT EXISTS `workson` (\r\n"
    			+ "  `StudentId` int NOT NULL,\r\n"
    			+ "  `projectID` int NOT NULL,\r\n"
    			+ "  `FromDate` date NOT NULL,\r\n"
    			+ "  `ToDate` date DEFAULT NULL,\r\n"
    			+ "  `Role` varchar(50) DEFAULT NULL,\r\n"
    			+ "  PRIMARY KEY (`StudentId`,`projectID`,`FromDate`),\r\n"
    			+ "  KEY `projectID` (`projectID`),\r\n"
    			+ "  CONSTRAINT `workson_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `project` (`ID`),\r\n"
    			+ "  CONSTRAINT `workson_ibfk_2` FOREIGN KEY (`StudentId`) REFERENCES `student` (`ID`)\r\n"
    			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n"
    			+ "\r\n"
    			+ "-- Dumping data for table kcs_test_v2.workson: ~0 rows (approximately)\r\n"
    			+ "/*!40000 ALTER TABLE `workson` DISABLE KEYS */;\r\n"
    			+ "INSERT IGNORE INTO `workson` (`StudentId`, `projectID`, `FromDate`, `ToDate`, `Role`) VALUES\r\n"
    			+ "	(1, 4, '2020-12-01', '2020-12-18', 'Member');\r\n"
    			+ "/*!40000 ALTER TABLE `workson` ENABLE KEYS */;\r\n"
    			+ "\r\n"
    			+ "/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;\r\n"
    			+ "/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;\r\n"
    			+ "/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;\r\n"
    			+ "";
    	Statement stmnt = c.createStatement();
    	stmnt.execute(SQL);
    }
}