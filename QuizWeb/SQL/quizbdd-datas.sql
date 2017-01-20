-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2017 at 09:47 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `quizbdd`
--

-- --------------------------------------------------------

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `mail`, `mdp`, `droits`) VALUES
(1, 'Admin', 'Admin', 'admin@tf8.com', 'adminTF8', 1000),
(2, 'Animateur', '1', 'animateur-1@tf8.com', 'animateurTF8', 1000),
(3, 'Test1', 'Prenom1', 'prenom-test1@gmail.com', 'estNul', 1),
(4, 'Test2', 'PrenomNul', 'PrenomNul-Test2@orange.fr', 'mdpSecurise', 1),
(5, 'NomMieux', 'Luc', 'luc.nommieux@fai.io', 'Original2015', 1),
(6, 'Dell', 'Graham', 'graham-dell@groupe-esigelec.org', 'EsigeDell20202016', 1),
(7, 'Dvd', 'Henry', 'henry.dvd@fai.com', 'faiNul', 1),
(8, 'Adams', 'Famille', 'famille.admas@scary.com', 'AdamsFamily', 1),
(9, 'Docteur', 'Le', 'Le-Docteur@bbc.com', 'Daleksaremean12', 1),
(10, 'Lour', 'Zer', 'Mille@mille.mille', 'mdpMille', 1000);

-- --------------------------------------------------------

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`id`, `libelle`, `etat`, `datedebutquiz`, `noquestioncourant`, `etape`, `datedebutquestion`) VALUES
(1, 'Quiz1', 0, '2017-01-09 13:51:49', 0, 0, '0000-00-00 00:00:00'),
(2, 'Quiz2', 0, '2017-01-09 13:52:31', 0, 0, '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `libelle`) VALUES
(1, 'Comment s\'appelle le president americain?'),
(3, 'Comment s\'appelle Louis la Brocante?'),
(4, 'Comment s\'appelle Michel Drucker?'),
(5, 'Comment s\'appelle le president francais?'),
(6, 'De quelle serie de six films un champion de boxe est-il la vedette ?'),
(7, 'Quelle est la capitale de la Nouvelle-Zelande ?'),
(8, 'Quel pays a pour capitale Katmandou ?'),
(9, 'A quel groupe musical international doit-on la bande originale du film Flash Gordon ?'),
(10, 'A quelle classe animale le scorpion appartient-il ?'),
(11, 'Dans les annees 1980, quel groupe musical a chante le titre Shout ?'),
(12, 'Quelle est la seule valeur a la roulette a porter la couleur verte ?'),
(13, 'Quelle est la plus petite unite de memoire utilisable sur un ordinateur ?'),
(14, 'Qui fut le premier europeen a arriver aux Philippines ?'),
(15, 'Combien de temps dure le mandat du president des Etats-Unis ?'),
(16, 'Quelle est l\'unite de mesure de la force d\'un piment ?'),
(17, 'Quel est la valeur du pH du Coca-Cola ?'),
(18, 'Comment se nomme le signe & ?'),
(19, 'Comment se nomme la peur du nombre 13 ?'),
(20, 'Comment se nomme le plus grand specimen de chouette ?'),
(21, 'A quel element se rapporte la loi de Godwin ?');

-- --------------------------------------------------------

--
-- Dumping data for table `proposition`
--

INSERT INTO `proposition` (`id`, `libelle`, `bonnereponse`, `idquestion`) VALUES
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
(14, 'Francois Hollande', 1, 5),
(15, 'Bob', 0, 5),
(16, 'Marylin Manson', 0, 5),
(17, 'Michel Drucker', 0, 5),
(18, 'Ritchie', 0, 6),
(19, 'Rocky', 1, 6),
(20, 'Rambo', 0, 6),
(21, 'Randy', 0, 6),
(22, 'Dublin', 0, 7),
(23, 'Auckland', 0, 7),
(24, 'Wellington', 1, 7),
(25, 'Sydney', 0, 7),
(26, 'Le Nepal', 1, 8),
(27, 'Le Tibet', 0, 8),
(28, 'La Coree du Sud', 0, 8),
(29, 'Le Pakistan', 0, 8),
(30, 'The Doors', 0, 9),
(31, 'Queen', 1, 9),
(32, 'Led Zeppelin', 0, 9),
(33, 'The Who', 0, 9),
(34, 'Aux Mammiferes', 0, 10),
(35, 'Aux reptiles', 0, 10),
(36, 'Aux arachnides', 1, 10),
(37, 'Aux insectes', 0, 10),
(38, 'U2', 0, 11),
(39, 'Tears For Tears', 1, 11),
(40, 'Queen', 0, 11),
(41, 'The Beatles', 0, 11),
(42, 'Le Treize', 0, 12),
(43, 'Le Mille', 0, 12),
(44, 'Le Diz', 0, 12),
(45, 'Le Zero', 1, 12),
(46, 'Le Bit', 1, 13),
(47, 'Le Byte', 0, 13),
(48, 'Le Mega', 0, 13),
(49, 'Le Giga', 0, 13),
(50, 'Christophe Colomb', 0, 14),
(51, 'Vasco de Gama', 0, 14),
(52, 'Magellan', 1, 14),
(53, 'Christ Cosmique', 0, 14),
(54, '1000 Ans', 0, 15),
(55, '4 Ans', 1, 15),
(56, '5 Ans', 0, 15),
(57, '7 Ans', 0, 15),
(58, 'L\'unité hauy', 0, 16),
(59, 'L\'unité thermoptim', 0, 16),
(60, 'L\'unité scoville', 1, 16),
(61, 'L\'unité cherit', 0, 16),
(62, '2.3', 1, 17),
(63, '4.5', 0, 17),
(64, '5.3', 0, 17),
(65, '7.2', 0, 17),
(66, 'Eperluete', 0, 18),
(67, 'Esperluette', 1, 18),
(68, 'Heperluette', 0, 18),
(69, 'Eperluette', 0, 18),
(70, 'La Katagelophobie', 0, 19),
(71, 'La Carpophobie ', 0, 19),
(72, 'La Triskaidekaphobie', 1, 19),
(73, 'La Sidérodromophobie', 0, 19),
(74, 'Grand Roi', 0, 20),
(75, 'Grand Comte', 0, 20),
(76, 'Grand Prince', 0, 20),
(77, 'Grand Duc', 1, 20),
(78, 'Christianisme', 0, 21),
(79, 'Nazisme', 1, 21),
(80, 'Socialisme', 0, 21),
(81, 'Communisme', 0, 21);


-- --------------------------------------------------------

--
-- Dumping data for table `questionduquiz`
--

INSERT INTO `questionduquiz` (`idquiz`, `idquestion`) VALUES
(1, 1),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(2, 12),
(2, 13),
(2, 14),
(2, 15),
(2, 16),
(2, 17),
(2, 18),
(2, 19),
(2, 20),
(2, 21);

