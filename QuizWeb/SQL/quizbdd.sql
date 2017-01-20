-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 04 Janvier 2017 à 17:18
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `quizbdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `choisir`
--

DROP IF EXISTS `choisir`;
DROP IF EXISTS`personne`;
DROP IF EXISTS `proposition`;
DROP IF EXISTS `question`;
DROP IF EXISTS `questionduquiz`;
DROP IF EXISTS `quiz`;


CREATE TABLE IF NOT EXISTS `choisir` (
  `date` timestamp NOT NULL,
  `idpersonne` int(10) NOT NULL,
  `idquiz` int(10) NOT NULL,
  `idproposition` int(10) NOT NULL,
  PRIMARY KEY (`date`,`idpersonne`,`idquiz`,`idproposition`),
  KEY `fkpers` (`idpersonne`),
  KEY `fkquiz` (`idquiz`),
  KEY `fkpropo` (`idproposition`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE IF NOT EXISTS `personne` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `mdp` varchar(20) NOT NULL,
  `droits` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `proposition`
--

CREATE TABLE IF NOT EXISTS `proposition` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `libelle` text NOT NULL,
  `bonnereponse` int(11) NOT NULL,
  `idquestion` int(10) NOT NULL ,
  PRIMARY KEY (`id`),
  KEY `fkquestion` (`idquestion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `questionduquiz`
--

CREATE TABLE IF NOT EXISTS `questionduquiz` (
  `idquiz` int(10) NOT NULL,
  `idquestion` int(10) NOT NULL,
  PRIMARY KEY (`idquiz`,`idquestion`),
  KEY `idquiz` (`idquiz`),
  KEY `idquestion` (`idquestion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `quiz`
--

CREATE TABLE IF NOT EXISTS `quiz` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `etat` int(1) NOT NULL,
  `datedebutquiz` timestamp NOT NULL,
  `noquestioncourant` int(10) NOT NULL,
  `etape` int(1) NOT NULL,
  `datedebutquestion` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `choisir`
--
ALTER TABLE `choisir`
  ADD CONSTRAINT `fkpropo` FOREIGN KEY (`idproposition`) REFERENCES `proposition` (`id`),
  ADD CONSTRAINT `fkpers` FOREIGN KEY (`idpersonne`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `fkquiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`id`);

--
-- Contraintes pour la table `proposition`
--
ALTER TABLE `proposition`
  ADD CONSTRAINT `fkquestion` FOREIGN KEY (`idquestion`) REFERENCES `question` (`id`);

--
-- Contraintes pour la table `questionduquiz`
--
ALTER TABLE `questionduquiz`
  ADD CONSTRAINT `fk_quiz` FOREIGN KEY (`idquiz`) REFERENCES `quiz` (`id`);
  
  ALTER TABLE `personne` ADD UNIQUE(`mail`);
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
