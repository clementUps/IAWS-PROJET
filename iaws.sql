-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 19 Avril 2015 à 14:12
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
--
-- Base de données :  `iaws`
--


CREATE DATABASE IAWS;

USE IAWS;
-- --------------------------------------------------------

--
-- Structure de la table `film`
--

CREATE TABLE IF NOT EXISTS `film` (
  `ID_FILM` int(11) NOT NULL AUTO_INCREMENT,
  `IMDB_ID` varchar(250) NOT NULL,
  `TITRE` varchar(250) NOT NULL,
  `ANNEE` int(11) NOT NULL,
  PRIMARY KEY (`ID_FILM`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=252 ;

-- --------------------------------------------------------

--
-- Structure de la table `film_salle`
--

CREATE TABLE IF NOT EXISTS `film_salle` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_FILM` int(11) NOT NULL,
  `ID_SALLE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=285 ;

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE IF NOT EXISTS `salle` (
  `ID_SALLE` int(11) NOT NULL AUTO_INCREMENT,
  `VILLE` varchar(250) NOT NULL,
  `NB_SALLES` int(11) NOT NULL,
  PRIMARY KEY (`ID_SALLE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=62 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
