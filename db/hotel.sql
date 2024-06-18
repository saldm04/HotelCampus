-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `camera`
--

DROP TABLE IF EXISTS `camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camera` (
  `numero` int NOT NULL,
  `numeroMaxOspiti` int NOT NULL,
  `quadratura` int NOT NULL,
  `costo` int NOT NULL,
  `tipo` enum('standard','luxury','deluxe','exclusive') NOT NULL,
  `img1` mediumblob,
  `img2` mediumblob,
  `disponibile` tinyint NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `camera_prenotata`
--

DROP TABLE IF EXISTS `camera_prenotata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camera_prenotata` (
  `id` int NOT NULL AUTO_INCREMENT,
  `costo` int NOT NULL,
  `camera` int NOT NULL,
  `prenotazione` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `camera` (`camera`),
  KEY `prenotazione` (`prenotazione`),
  CONSTRAINT `camera_prenotata_ibfk_1` FOREIGN KEY (`camera`) REFERENCES `camera` (`numero`),
  CONSTRAINT `camera_prenotata_ibfk_2` FOREIGN KEY (`prenotazione`) REFERENCES `prenotazioni` (`idPrenotazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prenotazioni`
--

DROP TABLE IF EXISTS `prenotazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenotazioni` (
  `idPrenotazione` int NOT NULL AUTO_INCREMENT,
  `dataPrenotazione` datetime NOT NULL,
  `dataInizio` date NOT NULL,
  `dataFine` date NOT NULL,
  `importo` int NOT NULL,
  `utente` varchar(40) NOT NULL,
  PRIMARY KEY (`idPrenotazione`),
  KEY `utente` (`utente`),
  CONSTRAINT `prenotazioni_ibfk_1` FOREIGN KEY (`utente`) REFERENCES `utente` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `codRecensione` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(300) DEFAULT NULL,
  `voto` enum('1','2','3','4','5') NOT NULL,
  `utente` varchar(40) NOT NULL,
  PRIMARY KEY (`codRecensione`),
  KEY `recensione_ibfk_1` (`utente`),
  CONSTRAINT `recensione_ibfk_1` FOREIGN KEY (`utente`) REFERENCES `utente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servizio`
--

DROP TABLE IF EXISTS `servizio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servizio` (
  `nome` varchar(30) NOT NULL,
  `descrizione` varchar(600) NOT NULL,
  `costo` int NOT NULL,
  `disponibile` tinyint NOT NULL,
  `img1` mediumblob,
  `img2` mediumblob,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servizio_prenotato`
--

DROP TABLE IF EXISTS `servizio_prenotato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servizio_prenotato` (
  `id` int NOT NULL AUTO_INCREMENT,
  `costo` int NOT NULL,
  `servizio` varchar(30) NOT NULL,
  `prenotazione` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `servizio` (`servizio`),
  KEY `prenotazione` (`prenotazione`),
  CONSTRAINT `servizio_prenotato_ibfk_1` FOREIGN KEY (`servizio`) REFERENCES `servizio` (`nome`),
  CONSTRAINT `servizio_prenotato_ibfk_2` FOREIGN KEY (`prenotazione`) REFERENCES `prenotazioni` (`idPrenotazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `email` varchar(40) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `nazionalità` varchar(30) NOT NULL,
  `dataDiNascita` date NOT NULL,
  `password` varchar(512) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-18 15:14:29
