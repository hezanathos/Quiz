-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 09 Janvier 2017 �  14:58
-- Version du serveur :  5.7.11
-- Version de PHP :  7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `quizbdd`
--

--
-- Contenu de la table `choisir`
--



--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `mail`, `mdp`, `droits`) VALUES
(1, 'Admin', 'Admin', 'admin@tf8.com', 'adminTF8', 1000),
(2, 'Admin', 'Admin', 'admin@tf8.com', 'adminTF8', 1000),
(3, 'Animateur', '1', 'animateur-1@tf8.com', 'animateurTF8', 1000),
(4, 'Test1', 'Prenom1', 'prenom-test1@gmail.com', 'estNul', 1),
(5, 'Test2', 'PrenomNul', 'PrenomNul-Test2@orange.fr', 'mdpSecurise', 1),
(6, 'NomMieux', 'Luc', 'luc.nommieux@fai.io', 'Original2015', 1),
(7, 'Dell', 'Graham', 'graham-dell@groupe-esigelec.org', 'EsigeDell20202016', 1),
(8, 'Dvd', 'Henry', 'henry.dvd@fai.com', 'faiNul', 1),
(9, 'Adams', 'Famille', 'famille.admas@scary.com', 'AdamsFamily', 1),
(10, 'Docteur', 'Le', 'Le-Docteur@bbc.com', 'Daleksaremean12', 1);

INSERT INTO `question` (`id`, `libelle`) VALUES
(1, 'Comment s\'appelle le président américain?'),
(3, 'Comment s\'appelle Louis la Brocante?'),
(4, 'Comment s\'appelle Michel Drucker?'),
(5, 'Comment s\'appelle le president français?');



INSERT INTO `quiz` (`id`, `libelle`, `etat`, `datedebutquiz`, `noquestioncourant`, `etape`, `datedebutquestion`) VALUES
(1, 'Quiz1', 0, '2017-01-09 13:51:49', 0, 0, '0000-00-00 00:00:00'),
(2, 'Quiz1', 0, '2017-01-09 13:52:31', 0, 0, '0000-00-00 00:00:00');
--
-- Contenu de la table `proposition`
--

INSERT INTO `proposition` (`id`, `libelle`, `bonnereponse`, `idquestion`) VALUES
(1, 'Georges Bush', 0, 1),
(2, 'Georges Bush', 0, 1),
(3, 'Barack Obama', 1, 1),
(4, 'Donald Trump', 0, 1),
(5, 'Bill Clinton', 0, 1),
(6, 'Louis le vide grenier', 0, 3),
(7, 'Louis LaVille', 0, 3),
(8, 'Louis La Brocante', 1, 3),
(9, 'Bob', 0, 3),
(10, 'Jack', 0, 4),
(11, 'Michel Drucker', 1, 4),
(12, 'Marylin Manson', 0, 4),
(13, 'Charles Manson', 0, 4),
(14, 'François Hollande', 1, 5),
(15, 'Bob', 0, 5),
(16, 'Marylin Manson', 0, 5),
(17, 'Michel Drucker', 0, 5);

--
-- Contenu de la table `question`
--


--
-- Contenu de la table `questionduquiz`
--

INSERT INTO `questionduquiz` (`idquiz`, `idquestion`) VALUES
(1, 1),
(1, 3),
(1, 4),
(1, 5);

--
-- Contenu de la table `quiz`
--



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


INSERT INTO `choisir` (`date`, `idpersonne`, `idquiz`, `idproposition`) VALUES
('2017-01-09 13:57:10', 4, 1, 1),
('2017-01-09 13:57:10', 4, 1, 8),
('2017-01-09 13:57:13', 4, 1, 1),
('2017-01-09 13:57:13', 4, 1, 8);
