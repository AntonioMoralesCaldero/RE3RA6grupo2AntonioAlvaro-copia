-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gestionservicios
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
  `id` int(11) NOT NULL,
  `activado` bit(1) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `borrado` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `id_familia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9u5d1d60bdwmdfvhsuv1q9020` (`username`),
  KEY `FKrsmpqvdrxypp7r6pnl34buq9v` (`id_familia`),
  CONSTRAINT `FKrsmpqvdrxypp7r6pnl34buq9v` FOREIGN KEY (`id_familia`) REFERENCES `familia_profesional` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (1,_binary '','morales caldero',_binary '\0','antonio','$2a$10$67VUAr0cVhsGoR3VYI6oj.1A2kt861/DN/jVZ6mBiAFNl6Ku5fYsG','ROL_ADMIN','antonio@gmail.com',1),(2,_binary '','Manolo',_binary '\0','Paco','$2a$10$HIDIiMNToDSGO/2yIS5lF.8NtHOGpzc.aOfqSPZCOaSO1beyzCv6K','ROL_ALUMNO','manolo@gmail.com',1),(3,_binary '',NULL,_binary '\0','Mercadona','$2a$10$9727pb7CaxAXKghvIRnFx.y17GRhswppNApqaW5UvEZ5IC65zIEkC','ROL_EMPRESA','mercadona@gmail.com',NULL),(52,_binary '','Milita',_binary '\0','Militar','$2a$10$8Ec./IUCArrw4xwVIn6w0e24kUOupCcLy0l9UEAt4PghvRKRbJTye','ROL_ALUMNO','militar@gmail.com',1),(102,_binary '',NULL,_binary '\0','Aldi','$2a$10$pSAODZYMY0v5U.IT9B79EOzHSuw5wQr2urds.s1R/58JFTyrCEwFe','ROL_EMPRESA','aldi@gmail.com',NULL);
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumno_seq`
--

DROP TABLE IF EXISTS `alumno_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno_seq`
--

LOCK TABLES `alumno_seq` WRITE;
/*!40000 ALTER TABLE `alumno_seq` DISABLE KEYS */;
INSERT INTO `alumno_seq` VALUES (201);
/*!40000 ALTER TABLE `alumno_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id` int(11) NOT NULL,
  `borrado` bit(1) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,_binary '\0','Avenida la Rotonda','mercadona@gmail.com','cansancio.jpeg','Mercadona','367812394'),(2,_binary '\0','Calle Viento N.6','aldi@gmail.com','alegria.jpg','Aldi','737828391');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa_seq`
--

DROP TABLE IF EXISTS `empresa_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa_seq`
--

LOCK TABLES `empresa_seq` WRITE;
/*!40000 ALTER TABLE `empresa_seq` DISABLE KEYS */;
INSERT INTO `empresa_seq` VALUES (101);
/*!40000 ALTER TABLE `empresa_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familia_profesional`
--

DROP TABLE IF EXISTS `familia_profesional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `familia_profesional` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familia_profesional`
--

LOCK TABLES `familia_profesional` WRITE;
/*!40000 ALTER TABLE `familia_profesional` DISABLE KEYS */;
INSERT INTO `familia_profesional` VALUES (1,'Militar'),(2,'Informatico'),(3,'Bombero'),(52,'Arquitecto');
/*!40000 ALTER TABLE `familia_profesional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familia_profesional_seq`
--

DROP TABLE IF EXISTS `familia_profesional_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `familia_profesional_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familia_profesional_seq`
--

LOCK TABLES `familia_profesional_seq` WRITE;
/*!40000 ALTER TABLE `familia_profesional_seq` DISABLE KEYS */;
INSERT INTO `familia_profesional_seq` VALUES (151);
/*!40000 ALTER TABLE `familia_profesional_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informe`
--

DROP TABLE IF EXISTS `informe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informe` (
  `id` int(11) NOT NULL,
  `fechahora` date DEFAULT NULL,
  `informe` varchar(255) DEFAULT NULL,
  `tiempo_servicio` int(11) NOT NULL,
  `id_alumno` int(11) DEFAULT NULL,
  `id_servicio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKryea9825fyh95ak1ynwrkdnn8` (`id_alumno`),
  KEY `FKeogfcu83q21fd4tuta4ly545p` (`id_servicio`),
  CONSTRAINT `FKeogfcu83q21fd4tuta4ly545p` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`),
  CONSTRAINT `FKryea9825fyh95ak1ynwrkdnn8` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informe`
--

LOCK TABLES `informe` WRITE;
/*!40000 ALTER TABLE `informe` DISABLE KEYS */;
/*!40000 ALTER TABLE `informe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informe_seq`
--

DROP TABLE IF EXISTS `informe_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informe_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informe_seq`
--

LOCK TABLES `informe_seq` WRITE;
/*!40000 ALTER TABLE `informe_seq` DISABLE KEYS */;
INSERT INTO `informe_seq` VALUES (1);
/*!40000 ALTER TABLE `informe_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio` (
  `id` int(11) NOT NULL,
  `borrado` bit(1) NOT NULL,
  `comentario_empresa` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `fecha_realizacion` date DEFAULT NULL,
  `realizado` bit(1) DEFAULT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `valoracion_empresa` int(11) DEFAULT NULL,
  `id_alumno` int(11) DEFAULT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `id_familia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlwib54van5m20edy58npxby1q` (`id_alumno`),
  KEY `FKk4xef806e4qkeblxpndoafeh5` (`id_empresa`),
  KEY `FKcam2p5q2j5s868o95m4es71u4` (`id_familia`),
  CONSTRAINT `FKcam2p5q2j5s868o95m4es71u4` FOREIGN KEY (`id_familia`) REFERENCES `familia_profesional` (`id`),
  CONSTRAINT `FKk4xef806e4qkeblxpndoafeh5` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`),
  CONSTRAINT `FKlwib54van5m20edy58npxby1q` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,_binary '\0','la vida es dura','reponer los yogures','2024-01-14',NULL,_binary '\0','Reponer pasillo 7',4,NULL,1,1),(2,_binary '\0','Luces fundidas','Reparar luces','2024-01-14',NULL,_binary '\0','Pasillo 9',2,NULL,1,1);
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio_seq`
--

DROP TABLE IF EXISTS `servicio_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio_seq`
--

LOCK TABLES `servicio_seq` WRITE;
/*!40000 ALTER TABLE `servicio_seq` DISABLE KEYS */;
INSERT INTO `servicio_seq` VALUES (101);
/*!40000 ALTER TABLE `servicio_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-15  0:31:18
