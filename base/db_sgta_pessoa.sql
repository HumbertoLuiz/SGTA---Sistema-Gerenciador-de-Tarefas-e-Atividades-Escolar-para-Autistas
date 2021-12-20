-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_sgta
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `dtype` varchar(31) NOT NULL,
  `id_pessoa` bigint NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `matricula` varchar(255) NOT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `responsavel_id_responsavel` bigint DEFAULT NULL,
  `telefone_id_telefone` bigint DEFAULT NULL,
  `id_turma` bigint DEFAULT NULL,
  PRIMARY KEY (`id_pessoa`),
  UNIQUE KEY `UK_nlwiu48rutiltbnjle59krljo` (`cpf`),
  KEY `FKlnwh01s82c3yle784ra0kbdi8` (`usuario_id`),
  KEY `FKknceugyfve8f0x939xea3uvns` (`responsavel_id_responsavel`),
  KEY `FKho4ggiiy9twn7id5yyo1kwuto` (`telefone_id_telefone`),
  KEY `FKhwh5amhthr3jfmtfwnhu84s95` (`id_turma`),
  CONSTRAINT `FKho4ggiiy9twn7id5yyo1kwuto` FOREIGN KEY (`telefone_id_telefone`) REFERENCES `telefone` (`id_telefone`),
  CONSTRAINT `FKhwh5amhthr3jfmtfwnhu84s95` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`),
  CONSTRAINT `FKknceugyfve8f0x939xea3uvns` FOREIGN KEY (`responsavel_id_responsavel`) REFERENCES `responsavel` (`id_responsavel`),
  CONSTRAINT `FKlnwh01s82c3yle784ra0kbdi8` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES ('aluno',1,'85715665426','Diogo Renato Barros','202112001',NULL,NULL,1,2,1),('aluno',2,'28539863901','Pietro Gael Henry das Neves','202112002',NULL,NULL,2,4,2),('aluno',3,'59209643402','Isabelle Pietra Terezinha Caldeira','202112003',NULL,NULL,3,6,2),('Servidor',4,'15506413521','Sarah Helena Carvalho','101515001',NULL,NULL,NULL,7,NULL),('Servidor',5,'95530289851','Lucia Marlene Corte Real','101521005',NULL,NULL,NULL,8,NULL);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-26 13:10:18
