-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 21, 2019 at 09:29 AM
-- Server version: 8.0.13-4
-- PHP Version: 7.2.17-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u9PpjiOIaV`
--

-- --------------------------------------------------------

--
-- Table structure for table `Inserito`
--

CREATE TABLE `Inserito` (
  `NomeM` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `NomeP` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Menù`
--

CREATE TABLE `Menù` (
  `NomeM` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Giorno` date NOT NULL,
  `Etichetta` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Menù`
--

INSERT INTO `Menù` (`NomeM`, `Giorno`, `Etichetta`) VALUES
('Principale', '2018-12-20', 'carne'),
('Secondario', '2018-10-23', 'pesce');

-- --------------------------------------------------------

--
-- Table structure for table `Ordinano`
--

CREATE TABLE `Ordinano` (
  `IdStudente` varchar(5) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Nome` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Piatti`
--

CREATE TABLE `Piatti` (
  `NomeP` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Numerocalorie` int(5) NOT NULL,
  `Tipologia` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Piatti`
--

INSERT INTO `Piatti` (`NomeP`, `Numerocalorie`, `Tipologia`) VALUES
('Costine', 250, 'Carne'),
('Merluzzo', 200, 'Pesce');

-- --------------------------------------------------------

--
-- Table structure for table `Studente`
--

CREATE TABLE `Studente` (
  `Nome` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Datanascita` date NOT NULL,
  `numerocel` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ClasseSezione` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Indirizzo` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Città` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Cognome` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IdStudente` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Studente`
--

INSERT INTO `Studente` (`Nome`, `Datanascita`, `numerocel`, `ClasseSezione`, `Indirizzo`, `Città`, `Cognome`, `IdStudente`) VALUES
('Martina', '2018-06-18', '3882047173', '5F', 'via Parma', 'Sassuolo', 'Suso', '30251'),
('Mario', '2018-08-14', '4932850173', '4G', 'via Torino', 'Sassuolo', 'Rossi', '48204');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Inserito`
--
ALTER TABLE `Inserito`
  ADD PRIMARY KEY (`NomeM`,`NomeP`),
  ADD KEY `NomeP` (`NomeP`);

--
-- Indexes for table `Menù`
--
ALTER TABLE `Menù`
  ADD PRIMARY KEY (`NomeM`);

--
-- Indexes for table `Ordinano`
--
ALTER TABLE `Ordinano`
  ADD PRIMARY KEY (`IdStudente`,`Nome`),
  ADD KEY `Nome` (`Nome`);

--
-- Indexes for table `Piatti`
--
ALTER TABLE `Piatti`
  ADD PRIMARY KEY (`NomeP`);

--
-- Indexes for table `Studente`
--
ALTER TABLE `Studente`
  ADD PRIMARY KEY (`IdStudente`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Inserito`
--
ALTER TABLE `Inserito`
  ADD CONSTRAINT `Inserito_ibfk_1` FOREIGN KEY (`NomeM`) REFERENCES `Menù` (`nomem`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Inserito_ibfk_2` FOREIGN KEY (`NomeP`) REFERENCES `Piatti` (`nomep`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `Ordinano`
--
ALTER TABLE `Ordinano`
  ADD CONSTRAINT `Ordinano_ibfk_1` FOREIGN KEY (`IdStudente`) REFERENCES `Studente` (`idstudente`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Ordinano_ibfk_2` FOREIGN KEY (`Nome`) REFERENCES `Piatti` (`NomeP`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
