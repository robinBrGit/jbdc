-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.7.26 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Listage de la structure de la base pour digi-jdbc
DROP DATABASE IF EXISTS `digi-jdbc`;
CREATE DATABASE IF NOT EXISTS `digi-jdbc`/*!40100 DEFAULT CHARACTER SET utf8 */;
USE `digi-jdbc`;

-- Listage de la structure de la table digi-jdbc. adress
DROP TABLE IF EXISTS `adress`;
CREATE TABLE IF NOT EXISTS `adress` (
  `id_adress` int(11) NOT NULL AUTO_INCREMENT,
  `rue` varchar(255) NOT NULL DEFAULT '0',
  `code_postale` int(11) NOT NULL DEFAULT '0',
  `ville` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_adress`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Les données exportées n'étaient pas sélectionnées.
-- Listage de la structure de la table digi-jdbc. contact
DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id_contact` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL DEFAULT '0',
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `id_adress` int(11) NOT NULL,
  PRIMARY KEY (`id_contact`),
  KEY `FK_contact_adress` (`id_adress`),
  CONSTRAINT `FK_contact_adress` FOREIGN KEY (`id_adress`) REFERENCES `adress` (`id_adress`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Les données exportées n'étaient pas sélectionnées.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
