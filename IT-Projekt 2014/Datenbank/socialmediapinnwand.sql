-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Mai 2014 um 21:47
-- Server Version: 5.6.16
-- PHP-Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `socialmediapinnwand`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abonnement`
--

CREATE TABLE IF NOT EXISTS `abonnement` (
  `abonnementID` int(11) NOT NULL AUTO_INCREMENT,
  `pinnwandID` int(11) NOT NULL,
  `nutzerID` int(11) NOT NULL,
  PRIMARY KEY (`abonnementID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `abonnement`
--

INSERT INTO `abonnement` (`abonnementID`, `pinnwandID`, `nutzerID`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `beitrag`
--

CREATE TABLE IF NOT EXISTS `beitrag` (
  `beitragID` int(11) NOT NULL AUTO_INCREMENT,
  `text` longtext COLLATE utf8_swedish_ci NOT NULL,
  `erstellungszeitpunkt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `likeID` int(11) NOT NULL,
  `kommentarID` int(11) NOT NULL,
  PRIMARY KEY (`beitragID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `beitrag`
--

INSERT INTO `beitrag` (`beitragID`, `text`, `erstellungszeitpunkt`, `likeID`, `kommentarID`) VALUES
(1, 'Beitrag 1 (Peter Lustig)', '2014-05-08 21:21:04', 1, 1),
(2, 'Beitrag 2 (Martin Schwab)', '2014-05-08 21:42:58', 1, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kommentar`
--

CREATE TABLE IF NOT EXISTS `kommentar` (
  `kommentarID` int(11) NOT NULL AUTO_INCREMENT,
  `text` longtext COLLATE utf8_swedish_ci NOT NULL,
  `erstellungszeitpunkt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nutzerID` int(11) NOT NULL,
  PRIMARY KEY (`kommentarID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `kommentar`
--

INSERT INTO `kommentar` (`kommentarID`, `text`, `erstellungszeitpunkt`, `nutzerID`) VALUES
(1, 'Kommentar 1 (Peter Lustig)', '2014-05-08 21:22:10', 1),
(2, 'Kommentar 2 (Martin Schwab)', '2014-05-08 21:44:26', 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `like`
--

CREATE TABLE IF NOT EXISTS `like` (
  `likeID` int(11) NOT NULL AUTO_INCREMENT,
  `nutzerID` int(11) NOT NULL,
  `erstellungszeitpunkt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`likeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `like`
--

INSERT INTO `like` (`likeID`, `nutzerID`, `erstellungszeitpunkt`) VALUES
(1, 1, '2014-05-08 21:22:31'),
(2, 2, '2014-05-08 21:46:14');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `nutzer`
--

CREATE TABLE IF NOT EXISTS `nutzer` (
  `nutzerID` int(11) NOT NULL AUTO_INCREMENT,
  `nachname` text COLLATE utf8_swedish_ci NOT NULL,
  `vorname` text COLLATE utf8_swedish_ci NOT NULL,
  `nickname` text COLLATE utf8_swedish_ci NOT NULL,
  `erstellungszeitpunkt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(32) COLLATE utf8_swedish_ci NOT NULL,
  `abonnementID` int(11) NOT NULL,
  `pinnwandID` int(11) NOT NULL,
  PRIMARY KEY (`nutzerID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `nutzer`
--

INSERT INTO `nutzer` (`nutzerID`, `nachname`, `vorname`, `nickname`, `erstellungszeitpunkt`, `password`, `abonnementID`, `pinnwandID`) VALUES
(1, 'Lustig', 'Peter', 'lustigp', '2014-04-17 13:39:36', '', 1, 1),
(2, 'Schwab', 'Martin', 'Martini', '2014-05-08 21:13:55', '1234', 1, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pinnwand`
--

CREATE TABLE IF NOT EXISTS `pinnwand` (
  `pinnwandID` int(11) NOT NULL AUTO_INCREMENT,
  `beitragID` int(11) NOT NULL,
  PRIMARY KEY (`pinnwandID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `pinnwand`
--

INSERT INTO `pinnwand` (`pinnwandID`, `beitragID`) VALUES
(1, 1),
(2, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
