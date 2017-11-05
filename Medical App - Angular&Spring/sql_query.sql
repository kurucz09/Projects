-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 01, 2017 at 11:12 PM
-- Server version: 5.6.35-cll-lve
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dezbater_medical`
--

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE `consultation` (
  `id` int(6) UNSIGNED ZEROFILL NOT NULL,
  `startDate` varchar(30) NOT NULL,
  `endDate` varchar(30) NOT NULL,
  `patient_id` varchar(99) NOT NULL,
  `details` varchar(1000) NOT NULL,
  `checked` varchar(5) NOT NULL,
  `alert` varchar(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`id`, `startDate`, `endDate`, `patient_id`, `details`, `checked`, `alert`) VALUES
(000027, '2017-05-18T12:00:00', '2017-05-18T13:00:00', '1960212125830', 'raceala am dat medicamente', 'false', 'false'),
(000025, '2017-05-18T13:30:00', '2017-05-18T15:00:00', 'emptySel23', 'Raceala simpla, sa ia paracetamol', 'false', 'false'),
(000024, '2017-05-15T10:30:00', '2017-05-15T14:00:00', 'emptySel23', 'He is fine just fine', 'false', 'false'),
(000028, '2017-05-18T15:30:00', '2017-05-18T16:30:00', '2920125125879', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet.', 'false', 'false'),
(000029, '2017-05-25T11:30:00', '2017-05-25T13:30:00', '1950824125798', 'Are dureri de cap din cauza oboselii. Prea multa munca la proiecte noapte. Se prescrie 3 zile de somn', 'true', 'false'),
(000030, '2017-05-26T13:30:00', '2017-05-26T15:00:00', '1950824125798', 'Created - ', 'false', 'false'),
(000031, '2017-05-26T10:00:00', '2017-05-26T11:30:00', '1940425124589', 'Created - ', 'false', 'false'),
(000032, '2017-05-24T15:00:00', '2017-05-24T17:00:00', '1850912458777', 'Created - ', 'true', 'false'),
(000033, '2017-05-26T16:00:00', '2017-05-26T17:00:00', '2791129125835', 'Created - ', 'true', 'false'),
(000034, '2017-05-24T10:00:00', '2017-05-24T11:30:00', '1810612125888', 'Created - ', 'true', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_consultation`
--

CREATE TABLE `doctor_consultation` (
  `id` int(5) UNSIGNED ZEROFILL NOT NULL,
  `doctorID` varchar(99) NOT NULL,
  `consultationID` int(6) UNSIGNED ZEROFILL NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_consultation`
--

INSERT INTO `doctor_consultation` (`id`, `doctorID`, `consultationID`) VALUES
(00014, 'doc@doc.ro', 000030),
(00013, 'doc@doc.ro', 000029),
(00012, 'doc@doc.ro', 000028),
(00011, 'doc@doc.ro', 000027),
(00009, 'doc@doc.ro', 000025),
(00008, 'doc@doc.ro', 000024),
(00015, 'doc@doc.ro', 000031),
(00016, 'doc@doc.ro', 000032),
(00017, 'doc@doc.ro', 000033),
(00018, 'doc@doc.ro', 000034);

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `PNC` varchar(13) NOT NULL,
  `IDCard` varchar(8) NOT NULL,
  `Name` varchar(99) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Address` varchar(99) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`PNC`, `IDCard`, `Name`, `DateOfBirth`, `Address`) VALUES
('1950824125798', 'kx989942', 'Bogdan Stupariu', '1995-08-24', 'Cluj, Romania'),
('1950824125678', 'kx677567', 'Dan Mehelean', '1995-08-24', 'Cluj, Romania'),
('1940425124589', 'KV124589', 'Daniel Serban', '1990-10-24', 'Bucuresti,Romania'),
('2920125125879', 'KT254777', 'Ioana Studes', '1992-01-25', 'Constanta,Romania'),
('1850912458777', 'RT254578', 'Vlad Victorian', '1985-09-12', 'Tulcea,Romania'),
('1960212125830', 'CJ029128', 'Alex Onitiu', '1996-02-12', 'Cluj, Romania'),
('2900412158666', 'MM231123', 'Daniela Chisu', '1990-04-12', 'Baia Mare,Romania'),
('1750314589654', 'KX015631', 'Mircea Ilea', '1975-03-14', 'Cluj,Romania'),
('2641225144444', 'KX231564', 'Stefania Stefut', '1964-12-25', 'Cluj,Romania'),
('2881111254888', 'CJ231431', 'Diana Zizau', '1988-11-11', 'Cluj,Romania'),
('2760808258488', 'KX231233', 'Petronela Rus', '1976-08-08', 'Cluj,Romania'),
('2561225111258', 'VL446563', 'Ionela Sabau', '1956-12-25', 'Valcea,Romania'),
('2980105125478', 'KX125444', 'Iosefina Farcas', '1998-01-05', 'Cluj, Romania'),
('2780102154856', 'KX125452', 'Daniela Stan', '1978-01-02', 'Cluj, Romania'),
('2850228125455', 'KX144512', 'Victoria Rusu', '1985-02-28', 'Cluj, Romania'),
('1740707125888', 'KX785814', 'Dan Morosan', '1974-07-07', 'Cluj, Romania'),
('1810612125888', 'KX455121', 'Mihai Pop', '1981-06-12', 'Cluj, Romania'),
('1820512514814', 'KX145151', 'Calin Muresanu', '1982-05-12', 'Cluj, Romania'),
('2791129125835', 'KX451532', 'Anda Prunea', '1979-11-29', 'Cluj, Romania'),
('1350408125487', 'RU125458', 'Vladimir Putin', '1935-04-08', 'Rusia, URSS');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Username` varchar(99) NOT NULL,
  `Name` varchar(99) NOT NULL,
  `Type` varchar(99) NOT NULL,
  `Password` varchar(99) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Username`, `Name`, `Type`, `Password`) VALUES
('admin@admin.ro', 'admin', 'Administrator', 'admin'),
('ioana@s.ro', 'Ioana Sud', 'Secretary', 'ioana'),
('gicaHagi@gigi.ro', 'Gica Hagi', 'Secretary', '124525'),
('doc@doc.ro', 'Doctor House', 'Doctor', 'doctor'),
('bogdan.st95@yahoo.com', 'testare', 'Administrator', 'testare'),
('test@test', 'testarea12', 'Secretary', '123456'),
('theOne@gg', 'The one Mar', 'Doctor', 'doctor212'),
('daniel@dan.ro', 'Daniel Dan', 'Doctor', 'doctordan'),
('vladimir@vivi.vo', 'Vladimir Vivian', 'Doctor', 'vivivlad'),
('florin.stan@gmail.com', 'Florin Stan', 'Secretary', 'florinstan'),
('vickyvivian934@yahoo.com', 'Victoria Vivian', 'Secretary', 'vicky33'),
('florinafifi@fifi.ro', 'Florina Stefan', 'Doctor', 'fifitheboss'),
('stefanescucostin@gmail.com', 'Stefanescu Costin', 'Secretary', 'steficoco'),
('rosticeria@dautore.ro', 'Rosticeria D\'autore', 'Secretary', 'rosticeria'),
('filip@gmail.com', 'Filip Cocu', 'Secretary', 'cocufilipian'),
('medicalis@contact.ro', 'Medicalis', 'Doctor', 'medicalistheboss');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor_consultation`
--
ALTER TABLE `doctor_consultation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`PNC`),
  ADD UNIQUE KEY `IDCard` (`IDCard`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Username`),
  ADD KEY `ID` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `id` int(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `doctor_consultation`
--
ALTER TABLE `doctor_consultation`
  MODIFY `id` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
