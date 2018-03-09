-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: dgt
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `contribuent`
--

DROP TABLE IF EXISTS `contribuent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contribuent` (
  `IDCon` int(11) NOT NULL AUTO_INCREMENT,
  `NIF` varchar(9) DEFAULT NULL,
  `Nom` varchar(20) DEFAULT NULL,
  `Domicili` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IDCon`),
  UNIQUE KEY `NIF` (`NIF`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contribuent`
--

LOCK TABLES `contribuent` WRITE;
/*!40000 ALTER TABLE `contribuent` DISABLE KEYS */;
INSERT INTO `contribuent` VALUES (1,'98402833D','Eduardo','C/ Pérez nº 38');
/*!40000 ALTER TABLE `contribuent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historic`
--

DROP TABLE IF EXISTS `historic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historic` (
  `IDH` int(11) NOT NULL AUTO_INCREMENT,
  `IDC` int(11) NOT NULL,
  `IDV` int(11) NOT NULL,
  `dataalta` date DEFAULT NULL,
  `databaixa` date DEFAULT NULL,
  PRIMARY KEY (`IDH`),
  UNIQUE KEY `IDC` (`IDC`),
  UNIQUE KEY `IDV` (`IDV`),
  CONSTRAINT `historic_ibfk_1` FOREIGN KEY (`IDC`) REFERENCES `contribuent` (`IDCon`),
  CONSTRAINT `historic_ibfk_2` FOREIGN KEY (`IDV`) REFERENCES `vehicle` (`IDVe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historic`
--

LOCK TABLES `historic` WRITE;
/*!40000 ALTER TABLE `historic` DISABLE KEYS */;
/*!40000 ALTER TABLE `historic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviment`
--

DROP TABLE IF EXISTS `moviment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moviment` (
  `IDMov` int(11) NOT NULL AUTO_INCREMENT,
  `tipusmov` char(1) NOT NULL,
  PRIMARY KEY (`IDMov`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviment`
--

LOCK TABLES `moviment` WRITE;
/*!40000 ALTER TABLE `moviment` DISABLE KEYS */;
INSERT INTO `moviment` VALUES (1,'A'),(2,'B'),(3,'M');
/*!40000 ALTER TABLE `moviment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipusbaixa`
--

DROP TABLE IF EXISTS `tipusbaixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipusbaixa` (
  `IDBaixa` int(11) NOT NULL AUTO_INCREMENT,
  `tipusbaixa` varchar(2) DEFAULT NULL,
  `IDMov` int(11) NOT NULL,
  PRIMARY KEY (`IDBaixa`),
  KEY `IDMov` (`IDMov`),
  CONSTRAINT `tipusbaixa_ibfk_1` FOREIGN KEY (`IDMov`) REFERENCES `moviment` (`IDMov`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipusbaixa`
--

LOCK TABLES `tipusbaixa` WRITE;
/*!40000 ALTER TABLE `tipusbaixa` DISABLE KEYS */;
INSERT INTO `tipusbaixa` VALUES (1,'BD',2),(2,'BT',2);
/*!40000 ALTER TABLE `tipusbaixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `IDVe` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(9) DEFAULT NULL,
  `nbastidor` varchar(17) DEFAULT NULL,
  `nmotor` varchar(20) DEFAULT NULL,
  `dataalta` date DEFAULT NULL,
  `tbaixa` varchar(2) DEFAULT NULL,
  `databaixa` date DEFAULT NULL,
  `IDM` int(11) NOT NULL,
  PRIMARY KEY (`IDVe`),
  UNIQUE KEY `IDM` (`IDM`),
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`IDM`) REFERENCES `moviment` (`IDMov`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-05 20:32:51
