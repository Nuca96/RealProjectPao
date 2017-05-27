-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 25 Mai 2017 la 15:09
-- Versiune server: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pao`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `comments`
--

CREATE TABLE `comments` (
  `sender` varchar(20) NOT NULL,
  `recever` varchar(20) NOT NULL,
  `comment` varchar(250) NOT NULL,
  `seen` varchar(5) NOT NULL,
  `date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `comments`
--

INSERT INTO `comments` (`sender`, `recever`, `comment`, `seen`, `date`) VALUES
('mpop', 'irri', '', 'yes', ''),
('riri', 'irri', 'vineri la ora 14 serverul va fi oprit', 'yes', '2017-05-25'),
('riri', 'irri', 'cbah adjna ', 'yes', '2017-05-25'),
('marius', 'irri', 'accesul persoanelor neautorizate este strict interzzis', 'yes', '2017-05-25');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `users`
--

CREATE TABLE `users` (
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `description` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `users`
--

INSERT INTO `users` (`firstname`, `lastname`, `username`, `password`, `email`, `description`) VALUES
('Irina', 'Dumea', 'riri', 'TrPdlhruyJ3zqmND5h5rDA==', 'irina@ceva.com', NULL),
('da', 'nu', 'irri', 'DwcRwhNcMslvcfprt10LPA==', 'toteu@ceva.com', NULL),
('Marius', 'Baisan', 'marius', 'wlFoWAbTlsbVXHm2sP3Irg==', 'marius@ceva.com', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
