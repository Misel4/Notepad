-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Φιλοξενητής: 127.0.0.1
-- Χρόνος δημιουργίας: 04 Μάη 2018 στις 16:12:18
-- Έκδοση διακομιστή: 10.1.31-MariaDB
-- Έκδοση PHP: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `notebook`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `note`
--

CREATE TABLE `note` (
  `AA` int(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `SURNAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `EMAIL` varchar(20) NOT NULL,
  `NOTE` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Άδειασμα δεδομένων του πίνακα `note`
--

INSERT INTO `note` (`AA`, `NAME`, `SURNAME`, `PASSWORD`, `EMAIL`, `NOTE`) VALUES
(1, 'test', 'aaa', '69878888', 'εμαιλ', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),
(5, 'dim', 'dimpapa', '123', 'yahoo', 'aaa'),
(6, 'aaa', 'aaa', 'aaa', 'aaa', 'aaa'),
(7, 'dimitris', 'papa', '1234', 'papa@yahoo.gr', '');

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`AA`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `note`
--
ALTER TABLE `note`
  MODIFY `AA` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
