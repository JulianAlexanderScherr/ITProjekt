-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 12. Mai 2014 um 15:48
-- Server Version: 5.6.16
-- PHP-Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `socialmediapinnwand2`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abonnement`
--

CREATE TABLE IF NOT EXISTS `abonnement` (
  `abonnementID` int(11) NOT NULL,
  `pinnwandID` int(11) NOT NULL,
  `nutzerID` int(11) NOT NULL,
  PRIMARY KEY (`abonnementID`),
  KEY `nutzerID` (`nutzerID`),
  KEY `pinnwandID` (`pinnwandID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Daten für Tabelle `abonnement`
--

INSERT INTO `abonnement` (`abonnementID`, `pinnwandID`, `nutzerID`) VALUES
(1, 2, 1),
(2, 3, 1),
(3, 1, 3),
(4, 1, 4),
(5, 3, 2),
(6, 3, 4),
(7, 4, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `beitrag`
--

CREATE TABLE IF NOT EXISTS `beitrag` (
  `beitragID` int(11) NOT NULL,
  `nutzerID` int(11) DEFAULT NULL,
  `text` longtext COLLATE utf8_swedish_ci NOT NULL,
  `erstellungszeitpunkt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`beitragID`),
  KEY `nutzerID` (`nutzerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Daten für Tabelle `beitrag`
--

INSERT INTO `beitrag` (`beitragID`, `nutzerID`, `text`, `erstellungszeitpunkt`) VALUES
(1, 1, 'schönes wetter heute (Peter Lustig)', '2014-05-08 19:21:04'),
(2, 2, 'mir geht es gut (Martin Schwab)', '2014-05-08 19:42:58'),
(3, 2, 'ich habe heute gute Laune (Martin Schwab)', '2014-05-10 10:00:48'),
(4, 3, 'ich bin gestresst (Marian Schrempf)', '2014-05-10 11:49:22'),
(5, 4, 'mir ist langweilig (Julian Scherr)', '2014-05-10 11:50:15');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kommentar`
--

CREATE TABLE IF NOT EXISTS `kommentar` (
  `kommentarID` int(11) NOT NULL,
  `beitragID` int(11) NOT NULL,
  `nutzerID` int(11) NOT NULL,
  `text` longtext COLLATE utf8_swedish_ci NOT NULL,
  `erstellungszeitpunkt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`kommentarID`),
  KEY `nutzerID` (`nutzerID`),
  KEY `beitragID` (`beitragID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Daten für Tabelle `kommentar`
--

INSERT INTO `kommentar` (`kommentarID`, `beitragID`, `nutzerID`, `text`, `erstellungszeitpunkt`) VALUES
(1, 3, 1, 'das freut mich', '2014-05-08 19:22:10'),
(2, 1, 2, 'morgen soll es schlechter werden', '2014-05-08 19:44:26'),
(3, 1, 3, 'das finde ich auch', '2014-05-10 12:02:03'),
(4, 2, 4, 'mir auch', '2014-05-10 12:02:03'),
(5, 4, 1, 'wieso denn das?', '2014-05-10 12:05:03');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `like`
--

CREATE TABLE IF NOT EXISTS `like` (
  `likeID` int(11) NOT NULL,
  `beitragID` int(11) NOT NULL,
  `nutzerID` int(11) NOT NULL,
  `erstellungszeitpunkt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`likeID`),
  KEY `nutzerID` (`nutzerID`),
  KEY `beitragID` (`beitragID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Daten für Tabelle `like`
--

INSERT INTO `like` (`likeID`, `beitragID`, `nutzerID`, `erstellungszeitpunkt`) VALUES
(1, 1, 2, '2014-05-08 19:22:31'),
(2, 4, 2, '2014-05-08 19:46:14'),
(3, 2, 1, '2014-05-10 12:15:20'),
(4, 4, 1, '2014-10-05 12:16:25'),
(5, 5, 3, '2014-10-05 12:17:33'),
(6, 3, 3, '2014-10-05 12:18:50');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `nutzer`
--

CREATE TABLE IF NOT EXISTS `nutzer` (
  `nutzerID` int(11) NOT NULL,
  `nachname` text COLLATE utf8_swedish_ci NOT NULL,
  `vorname` text COLLATE utf8_swedish_ci NOT NULL,
  `nickname` text COLLATE utf8_swedish_ci NOT NULL,
  `erstellungszeitpunkt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `password` varchar(32) COLLATE utf8_swedish_ci NOT NULL,
  `pinnwandID` int(11) DEFAULT NULL,
  PRIMARY KEY (`nutzerID`),
  KEY `pinnwandID` (`pinnwandID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Daten für Tabelle `nutzer`
--

INSERT INTO `nutzer` (`nutzerID`, `nachname`, `vorname`, `nickname`, `erstellungszeitpunkt`, `password`, `pinnwandID`) VALUES
(1, 'Lustig', 'Peter', 'lustigp', '2014-04-17 11:39:36', '', 1),
(2, 'Schwab', 'Martin', 'Martini', '2014-05-08 19:13:55', '1234', 2),
(3, 'Schrempf', 'Marian', 'MarianS', '2014-05-01 13:39:36', '', 3),
(4, 'Scherr', 'Julian', 'scherrju', '2014-03-17 12:39:36', '', 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pinnwand`
--

CREATE TABLE IF NOT EXISTS `pinnwand` (
  `pinnwandID` int(11) NOT NULL,
  `beitragID` int(11) DEFAULT NULL,
  PRIMARY KEY (`pinnwandID`),
  KEY `beitragID` (`beitragID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Daten für Tabelle `pinnwand`
--

INSERT INTO `pinnwand` (`pinnwandID`, `beitragID`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
