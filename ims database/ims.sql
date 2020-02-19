-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 14, 2020 at 05:10 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ims`
--

-- --------------------------------------------------------

--
-- Table structure for table `lms`
--

CREATE TABLE `lms` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `hash` text NOT NULL,
  `priority` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lms`
--

INSERT INTO `lms` (`id`, `username`, `password`, `hash`, `priority`) VALUES
(2, 'admin', '123456', '', 'admin'),
(3, '4902430620680', '4902430620680', '', 'local');

-- --------------------------------------------------------

--
-- Table structure for table `product_id_list`
--

CREATE TABLE `product_id_list` (
  `id` bigint(99) NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `model` varchar(30) NOT NULL,
  `insert_year` varchar(10) NOT NULL,
  `date` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_id_list`
--

INSERT INTO `product_id_list` (`id`, `product_id`, `model`, `insert_year`, `date`) VALUES
(1, '123', '6s', '2015', ''),
(2, '121', '6s', '2015', ''),
(3, '4902430620680', '5s', '2015', ''),
(4, '4800888143402', '5s', '2015', ''),
(5, '355434048998191', '6s', '2015', ''),
(6, '115', '5s', '2015', ''),
(7, '12345', '6s', '2015', ''),
(11, '15001', '5s', '2015', '2015-10-28'),
(16, '15002', '5s', '2015', '2015-10-28'),
(12, '15003', '5s', '2015', '2015-10-28'),
(13, '15004', '5s', '2015', '2015-10-28'),
(14, '15005', '5s', '2015', '2015-10-28'),
(15, '15006', '5s', '2015', '2015-10-28'),
(17, '15010', '5s', '2015', '2015-10-28'),
(18, '15007', '5s', '2015', '2015-10-28'),
(19, '15008', '5s', '2015', '2015-10-28'),
(20, '15009', '5s', '2015', '2015-10-28'),
(21, '15001', '6s', '2015', '2015-10-30'),
(22, '15002', '6s', '2015', '2015-10-30'),
(23, '4455781245345', '5s', '2016', ''),
(24, '111', '5s', '2015', '2015-11-03'),
(25, '112', '5s', '2015', '2015-11-03'),
(26, '113', '5s', '2015', '2015-11-03'),
(27, '15011', '5s', '2015', '2015-11-04'),
(28, '15012', '5s', '2015', '2015-11-04'),
(29, '15013', '5s', '2015', '2015-11-04'),
(30, '15014', '5s', '2015', '2015-11-04'),
(31, '15015', '5s', '2015', '2015-11-04'),
(32, '15016', '5s', '2015', '2015-11-04'),
(33, '15017', '5s', '2015', '2015-11-04'),
(34, '15018', '5s', '2015', '2015-11-04'),
(35, '15019', '5s', '2015', '2015-11-04'),
(36, '15020', '5s', '2015', '2015-11-04'),
(37, '15021', '5s', '2015', '2015-11-04'),
(38, '15022', '5s', '2015', '2015-11-04'),
(39, '15023', '5s', '2015', '2015-11-04'),
(40, '15024', '5s', '2015', '2015-11-04'),
(41, '15025', '5s', '2015', '2015-11-04'),
(42, '15026', '5s', '2015', '2015-11-04'),
(43, '15027', '5s', '2015', '2015-11-04'),
(44, '15028', '5s', '2015', '2015-11-04'),
(45, '15029', '5s', '2015', '2015-11-04'),
(46, '15030', '5s', '2015', '2015-11-04'),
(47, '3301', '5s', '2015', '2015-11-06'),
(48, 'oil001', 'Oil', '2015', '2015-11-13'),
(49, 'oil002', 'Oil', '2015', '2015-11-13'),
(50, 'oil003', 'Oil', '2015', '2015-11-13'),
(51, 'oil004', 'Oil', '2015', '2015-11-13'),
(52, 'oil005', 'Oil', '2015', '2015-11-13'),
(53, '325', 'Oil', '2015', '2015-11-17'),
(54, '326', 'Oil', '2015', '2015-11-17'),
(55, '327', 'Oil', '2015', '2015-11-17'),
(56, '328', 'Oil', '2015', '2015-11-17'),
(57, '329', 'Oil', '2015', '2015-11-17'),
(58, '330', 'Oil', '2015', '2015-11-17'),
(59, '331', 'Oil', '2015', '2015-11-17'),
(60, '332', 'Oil', '2015', '2015-11-17'),
(61, '333', 'Oil', '2015', '2015-11-17'),
(62, '334', 'Oil', '2015', '2015-11-17'),
(63, '335', 'Oil', '2015', '2015-11-17'),
(64, '350', 'Oil', '2015', '2015-11-17'),
(65, '351', 'Oil', '2015', '2015-11-17'),
(66, '360', 'Oil', '2015', '2015-11-18'),
(67, '361', 'Oil', '2015', '2015-11-18'),
(68, '362', 'Oil', '2015', '2015-11-18'),
(69, '3350', 'Dice', '2015', '2015-11-22'),
(70, '3351', 'Dice', '2015', '2015-11-22'),
(74, '1001', 'Oil', '2015', '2015-11-25'),
(73, '1000', 'Oil', '2015', '2015-11-25'),
(75, '701', '5', '2016', '2016-04-13'),
(76, '702', '5', '2016', '2016-04-13'),
(77, '703', '5', '2016', '2016-04-13'),
(78, '704', '5', '2016', '2016-04-13'),
(79, '705', '5', '2016', '2016-04-13'),
(80, '706', '5', '2016', '2016-04-13'),
(81, 'A101', '6s', '2016', '2016-04-13'),
(82, 'A102', '6s', '2016', '2016-04-13'),
(83, 'O101', 'Oil', '2016', '2016-04-13'),
(84, 'O102', 'Oil', '2016', '2016-04-13'),
(85, 'A103', '5', '2016', '2016-04-13'),
(86, 'D101', 'Dice', '2016', '2016-04-13'),
(87, 'D102', 'Dice', '2016', '2016-04-13'),
(88, 'D105', 'Dice', '2016', '2016-04-13'),
(89, 'D106', 'Dice', '2016', '2016-04-13'),
(90, 'D110', 'Dice', '2016', '2016-04-13'),
(91, 'D111', 'Dice', '2016', '2016-04-13'),
(92, 'O110', 'Oil', '2016', '2016-04-13'),
(93, 'O111', 'Oil', '2016', '2016-04-13');

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2015`
--

CREATE TABLE `product_info_2015` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `minus` float NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `delivered_name` varchar(50) NOT NULL,
  `delivered_cardNo` varchar(60) NOT NULL,
  `delivered_description` text NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_info_2015`
--

INSERT INTO `product_info_2015` (`id`, `year_arrival`, `month_arrival`, `date_arrival`, `time_arrival`, `year_in`, `month_in`, `date_in`, `time_in`, `year_out`, `month_out`, `date_out`, `time_out`, `name`, `model`, `product_id`, `minus`, `price`, `current_status`, `status_arrival`, `status_in`, `status_out`, `delivered_name`, `delivered_cardNo`, `delivered_description`, `user`) VALUES
(1, '2015', 'August', '2015-08-01', '10:31 PM', '2015', 'October', '2015-10-09', '11:20 AM', '2015', 'October', '2015-10-16', '08:05 PM', 'Gillete', '5s', '4902430620680', 0, 60, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', ''),
(2, '2015', 'August', '2015-08-03', '10:33 PM', '2015', 'October', '2015-10-09', '09:39 AM', '2015', 'October', '2015-10-16', '09:17 PM', 'Rexona', '5s', '4800888143402', 0, 50, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', ''),
(3, '2015', 'September', '2015-09-01', '10:36 PM', '2015', 'October', '2015-10-07', '11:59 PM', '2015', 'October', '2015-10-16', '10:05:02 PM', 'ZTE IMEI', '6s', '355434048998191', 0, 1500, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', ''),
(4, '2015', 'August', '2015-08-10', '01:14 AM', '2015', 'September', '2015-09-17', '09:09 PM', '2015', 'October', '2015-10-16', '10:04:47 PM', 'Book1', '6s', '123', 0, 30, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', ''),
(9, '2015', 'September', '2015-09-01', '12:11 PM', '2015', 'September', '2015-09-17', '09:09 PM', '2015', 'October', '2015-10-16', '10:05:02 PM', 'Altec', '6s', '12345', 0, 4800, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', ''),
(11, '2015', 'August', '2015-08-05', '12:16 PM', '2015', 'September', '2015-09-17', '09:09 PM', '2015', 'October', '2015-10-16', '10:04:37 PM', 'samsung', '6s', '121', 0, 3000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', ''),
(12, '2015', 'August', '2015-08-09', '12:39 PM', '2015', 'September', '2015-09-17', '09:17 PM', '2015', 'September', '2015-09-17', '09:17 PM', 'eyeglass', '5s', '115', 0, 0, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', ''),
(13, '2015', 'October', '2015-10-28', '10:09:36 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'October', '2015-10-28', '11:01:06 PM', '', '5s', '15001', 0, 0, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(19, '2015', 'October', '2015-10-28', '10:31:12 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'October', '2015-10-28', '11:01:06 PM', 'iPhone', '5s', '15002', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(15, '2015', 'October', '2015-10-28', '10:23:29 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'October', '2015-10-28', '11:01:06 PM', 'iPhone', '5s', '15003', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(16, '2015', 'October', '2015-10-28', '10:23:29 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'October', '2015-10-28', '11:02:52 PM', 'iPhone', '5s', '15004', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(17, '2015', 'October', '2015-10-28', '10:23:29 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'October', '2015-10-28', '11:07:34 PM', 'iPhone', '5s', '15005', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(18, '2015', 'October', '2015-10-28', '10:23:29 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'October', '2015-10-28', '11:13:42 PM', 'iPhone', '5s', '15006', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(20, '2015', 'October', '2015-10-28', '10:31:12 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'November', '2015-11-04', '12:23:39 PM', 'iPhone', '5s', '15010', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'KMN', '65496', '', '1'),
(21, '2015', 'October', '2015-10-28', '10:31:12 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'October', '2015-10-28', '11:13:42 PM', 'iPhone', '5s', '15007', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(22, '2015', 'October', '2015-10-28', '10:31:12 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'October', '2015-10-28', '11:14:33 PM', 'iPhone', '5s', '15008', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(23, '2015', 'October', '2015-10-28', '10:31:12 PM', '2015', 'October', '2015-10-28', '10:45:17 PM', '2015', 'November', '2015-11-04', '12:23:39 PM', 'iPhone', '5s', '15009', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'KMN', '65496', '', '1'),
(24, '2015', 'October', '2015-10-30', '02:51:53 PM', '2015', 'November', '2015-11-06', '01:54:06 PM', '2015', 'November', '2015-11-06', '01:54:33 PM', 'iPhone', '6s', '15001', 0, 52000, 'Product out', 'On arrival', 'Product in', 'Product out', 'MXA', '365897', '', '1'),
(25, '2015', 'October', '2015-10-30', '02:51:53 PM', '2015', 'November', '2015-11-06', '01:54:06 PM', '2015', 'November', '2015-11-06', '01:54:33 PM', 'iPhone', '6s', '15002', 0, 52000, 'Product out', 'On arrival', 'Product in', 'Product out', 'MXA', '365897', '', '1'),
(26, '2015', 'November', '2015-11-03', '11:15:40 PM', '2015', 'November', '2015-11-04', '12:13:20 PM', '2015', 'November', '2015-11-04', '12:14:29 PM', 'iPhone', '5s', '111', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXI GROUP', '123456', '', '1'),
(27, '2015', 'November', '2015-11-03', '11:15:40 PM', '2015', 'November', '2015-11-04', '12:13:20 PM', '2015', 'November', '2015-11-04', '12:23:39 PM', 'iPhone', '5s', '112', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXI GROUP', '123456', '', '1'),
(28, '2015', 'November', '2015-11-03', '11:16:58 PM', '2015', 'November', '2015-11-04', '12:13:20 PM', '2015', 'November', '2015-11-04', '12:23:39 PM', 'iPhone', '5s', '113', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXI GROUP', '1234567', '', '1'),
(29, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:50:15 PM', '2015', 'November', '2015-11-04', '12:56:18 PM', 'iPhone', '5s', '15011', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXI GROUP', '1234567', '', '1'),
(30, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:56:44 PM', '2015', 'November', '2015-11-04', '12:56:52 PM', 'iPhone', '5s', '15012', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'DAMN GROUP', '321654', '', '1'),
(31, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:56:58 PM', '2015', 'November', '2015-11-04', '12:56:59 PM', 'iPhone', '5s', '15013', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'DAMN GROUP', '321654', '', '1'),
(32, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:57:02 PM', '2015', 'November', '2015-11-04', '12:57:03 PM', 'iPhone', '5s', '15014', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'DAMN GROUP', '321654', '', '1'),
(33, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:57:28 PM', '2015', 'November', '2015-11-04', '12:57:29 PM', 'iPhone', '5s', '15015', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'DAMN GROUP', '321654', '', '1'),
(34, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:57:33 PM', '2015', 'November', '2015-11-04', '12:57:34 PM', 'iPhone', '5s', '15016', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'FITA GROUP', '2587', '', '1'),
(35, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:57:37 PM', '2015', 'November', '2015-11-04', '12:57:37 PM', 'iPhone', '5s', '15017', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'FITA GROUP', '2587', '', '1'),
(36, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:57:43 PM', '2015', 'November', '2015-11-04', '12:57:44 PM', 'iPhone', '5s', '15018', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'FITA GROUP', '2587', '', '1'),
(37, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:57:47 PM', '2015', 'November', '2015-11-04', '12:57:48 PM', 'iPhone', '5s', '15019', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'KMN', '65496', '', '1'),
(38, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-04', '12:57:52 PM', '2015', 'November', '2015-11-04', '12:57:53 PM', 'iPhone', '5s', '15020', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', 'KMN', '65496', '', '1'),
(39, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:53:18 PM', '2015', 'November', '2015-11-06', '01:53:19 PM', 'iPhone', '5s', '15021', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(40, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '2015', 'November', '2015-11-06', '01:57:19 PM', 'iPhone', '5s', '15022', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(41, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '2015', 'November', '2015-11-06', '01:57:19 PM', 'iPhone', '5s', '15023', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(42, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '2015', 'November', '2015-11-06', '01:57:19 PM', 'iPhone', '5s', '15024', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(43, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '', '', '', '', 'iPhone', '5s', '15025', 0, 35000, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(44, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '', '', '', '', 'iPhone', '5s', '15026', 0, 35000, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(45, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '', '', '', '', 'iPhone', '5s', '15027', 0, 35000, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(46, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '', '', '', '', 'iPhone', '5s', '15028', 0, 35000, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(47, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '', '', '', '', 'iPhone', '5s', '15029', 0, 35000, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(48, '2015', 'November', '2015-11-04', '12:48:42 PM', '2015', 'November', '2015-11-06', '01:57:06 PM', '', '', '', '', 'iPhone', '5s', '15030', 0, 35000, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(49, '2015', 'November', '2015-11-06', '03:41:27 PM', '2015', 'November', '2015-11-06', '03:42:03 PM', '2015', 'November', '2015-11-06', '03:42:12 PM', 'iPhone', '5s', '3301', 0, 35000, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(50, '2015', 'November', '2015-11-13', '12:30:21 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', 'oil001', 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(51, '2015', 'November', '2015-11-13', '12:30:21 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', 'oil002', 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(52, '2015', 'November', '2015-11-13', '12:30:21 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', 'oil003', 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(53, '2015', 'November', '2015-11-13', '01:00:37 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', 'oil004', 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(54, '2015', 'November', '2015-11-13', '01:08:49 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', 'oil005', 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(55, '2015', 'November', '2015-11-17', '09:11:26 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '325', 0, 100, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(56, '2015', 'November', '2015-11-17', '09:11:26 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '326', 0, 50, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(57, '2015', 'November', '2015-11-17', '09:15:14 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '327', 0, 100, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(58, '2015', 'November', '2015-11-17', '09:17:06 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '328', 0, 50, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(59, '2015', 'November', '2015-11-17', '09:18:01 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '329', 0, 50, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(60, '2015', 'November', '2015-11-17', '09:18:01 PM', '2015', 'November', '2015-11-24', '09:14:01 PM', '2015', 'November', '2015-11-27', '01:10:23 PM', 'Rupchanda', 'Oil', '330', 490.5, 50, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXO', '12345', '', '1'),
(61, '2015', 'November', '2015-11-17', '09:19:44 PM', '2015', 'November', '2015-11-27', '07:10:31 PM', '2015', 'November', '2015-11-27', '08:34:26 PM', 'Rupchanda', 'Oil', '331', 500, 50, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXO', '12345', '', '1'),
(62, '2015', 'November', '2015-11-17', '09:19:44 PM', '2015', 'November', '2015-11-27', '01:18:20 PM', '2015', 'November', '2015-11-27', '01:18:36 PM', 'Rupchanda', 'Oil', '332', 450.5, 100, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXO', '12345', '', '1'),
(63, '2015', 'November', '2015-11-17', '09:19:44 PM', '2015', 'November', '2015-11-27', '07:10:40 PM', '2015', 'November', '2015-11-27', '07:10:45 PM', 'Rupchanda', 'Oil', '333', 50.5, 100, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXO', '12345', '', '1'),
(64, '2015', 'November', '2015-11-17', '09:19:44 PM', '2015', 'November', '2015-11-27', '07:11:23 PM', '2015', 'November', '2015-11-27', '07:23:23 PM', 'Rupchanda', 'Oil', '334', 200.5, 100, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXO', '12345', '', '1'),
(65, '2015', 'November', '2015-11-17', '09:19:44 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '335', 0, 50, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(66, '2015', 'November', '2015-11-17', '10:14:45 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '350', 0, 100, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(67, '2015', 'November', '2015-11-17', '10:14:45 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '351', 0, 50, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(68, '2015', 'November', '2015-11-18', '11:49:01 AM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '360', 0, 100, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(69, '2015', 'November', '2015-11-18', '11:49:02 AM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '361', 0, 100, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(70, '2015', 'November', '2015-11-18', '11:49:02 AM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '362', 0, 50, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(71, '2015', 'November', '2015-11-22', '09:13:24 PM', '', '', '', '', '', '', '', '', 'Ludo', 'Dice', '3350', 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(72, '2015', 'November', '2015-11-22', '09:13:24 PM', '', '', '', '', '', '', '', '', 'Ludo', 'Dice', '3351', 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(76, '2015', 'November', '2015-11-25', '01:44:34 PM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', '1001', 0, 0, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(75, '2015', 'November', '2015-11-25', '01:44:34 PM', '2015', 'November', '2015-11-25', '04:56:27 PM', '2015', 'November', '2015-11-27', '10:59:05 AM', 'Rupchanda', 'Oil', '1000', 800, 0, 'Product out', 'On arrival', 'Product in', 'Product out', 'AXO', '12345', 'Company', '1');

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2016`
--

CREATE TABLE `product_info_2016` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `size` int(11) NOT NULL,
  `minus` float NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `delivered_name` varchar(50) NOT NULL,
  `delivered_cardNo` varchar(60) NOT NULL,
  `delivered_description` text NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_info_2016`
--

INSERT INTO `product_info_2016` (`id`, `year_arrival`, `month_arrival`, `date_arrival`, `time_arrival`, `year_in`, `month_in`, `date_in`, `time_in`, `year_out`, `month_out`, `date_out`, `time_out`, `name`, `model`, `product_id`, `size`, `minus`, `price`, `current_status`, `status_arrival`, `status_in`, `status_out`, `delivered_name`, `delivered_cardNo`, `delivered_description`, `user`) VALUES
(1, '2016', 'April', '2016-04-13', '08:58:28 AM', '2016', 'April', '2016-04-13', '09:33:19 AM', '2016', 'April', '2016-04-13', '09:34:11 AM', 'iphone', '5', '701', 0, 0, 6500, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(2, '2016', 'April', '2016-04-13', '08:58:28 AM', '2016', 'April', '2016-04-13', '09:33:19 AM', '2016', 'April', '2016-04-13', '09:34:11 AM', 'iphone', '5', '702', 0, 0, 6500, 'Product out', 'On arrival', 'Product in', 'Product out', '', '', '', '1'),
(3, '2016', 'April', '2016-04-13', '08:59:47 AM', '2016', 'April', '2016-04-13', '09:33:19 AM', '', '', '', '', 'iphone', '5', '703', 0, 0, 6500, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(4, '2016', 'April', '2016-04-13', '08:59:47 AM', '2016', 'April', '2016-04-13', '09:33:19 AM', '', '', '', '', 'iphone', '5', '704', 0, 0, 6500, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(5, '2016', 'April', '2016-04-13', '09:02:02 AM', '2016', 'April', '2016-04-13', '09:33:19 AM', '', '', '', '', 'iphone', '5', '705', 0, 0, 6500, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(6, '2016', 'April', '2016-04-13', '09:02:02 AM', '2016', 'April', '2016-04-13', '09:33:19 AM', '', '', '', '', 'iphone', '5', '706', 0, 0, 6500, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(7, '2016', 'April', '2016-04-13', '09:08:48 AM', '2016', 'April', '2016-04-13', '09:09:35 AM', '2016', 'April', '2016-04-13', '09:14:25 AM', 'iPhone', '6s', 'A101', 0, 0, 52000, 'Product out', 'On arrival', 'Product in', 'Product out', 'Inter corp', '321456', 'sdfas', '1'),
(8, '2016', 'April', '2016-04-13', '09:08:48 AM', '2016', 'April', '2016-04-13', '09:19:37 AM', '2016', 'April', '2016-04-13', '09:19:52 AM', 'iPhone', '6s', 'A102', 0, 0, 52000, 'Product out', 'On arrival', 'Product in', 'Product out', 'Inter Corp', '321456', '', '1'),
(9, '2016', 'April', '2016-04-13', '09:15:38 AM', '2016', 'April', '2016-04-13', '09:15:49 AM', '2016', 'April', '2016-04-13', '09:19:27 AM', 'Rupchanda', 'Oil', 'O101', 1000, 1000, 100, 'Product out', 'On arrival', 'Product in', 'Product out', 'Inter Corp', '321456', '', '1'),
(10, '2016', 'April', '2016-04-13', '09:15:38 AM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', 'O102', 1000, 0, 0, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(11, '2016', 'April', '2016-04-13', '09:21:10 AM', '2016', 'April', '2016-04-13', '09:33:19 AM', '', '', '', '', 'iphone', '5', 'A103', 0, 0, 65000, 'Product in', 'On arrival', 'Product in', '', '', '', '', '1'),
(12, '2016', 'April', '2016-04-13', '09:22:26 AM', '', '', '', '', '', '', '', '', 'Ludo', 'Dice', 'D101', 0, 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(13, '2016', 'April', '2016-04-13', '09:22:26 AM', '', '', '', '', '', '', '', '', 'Ludo', 'Dice', 'D102', 0, 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(14, '2016', 'April', '2016-04-13', '09:24:32 AM', '', '', '', '', '', '', '', '', 'Ludo', 'Dice', 'D105', 0, 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(15, '2016', 'April', '2016-04-13', '09:24:32 AM', '', '', '', '', '', '', '', '', 'Ludo', 'Dice', 'D106', 0, 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(16, '2016', 'April', '2016-04-13', '09:26:23 AM', '', '', '', '', '', '', '', '', 'Ludo', 'Dice', 'D110', 0, 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(17, '2016', 'April', '2016-04-13', '09:26:23 AM', '', '', '', '', '', '', '', '', 'Ludo', 'Dice', 'D111', 0, 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(18, '2016', 'April', '2016-04-13', '09:28:03 AM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', 'O110', 1000, 0, 100, 'On arrival', 'On arrival', '', '', '', '', '', '1'),
(19, '2016', 'April', '2016-04-13', '09:28:03 AM', '', '', '', '', '', '', '', '', 'Rupchanda', 'Oil', 'O111', 2000, 0, 200, 'On arrival', 'On arrival', '', '', '', '', '', '1');

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2017`
--

CREATE TABLE `product_info_2017` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2018`
--

CREATE TABLE `product_info_2018` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2019`
--

CREATE TABLE `product_info_2019` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2020`
--

CREATE TABLE `product_info_2020` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_info_2020`
--

INSERT INTO `product_info_2020` (`id`, `year_arrival`, `month_arrival`, `date_arrival`, `time_arrival`, `year_in`, `month_in`, `date_in`, `time_in`, `year_out`, `month_out`, `date_out`, `time_out`, `name`, `model`, `product_id`, `order_no`, `price`, `current_status`, `status_arrival`, `status_in`, `status_out`, `user`) VALUES
(1, '2020', 'January', '2020-01-15', '10:30 PM', '2020', 'January', '2020-01-20', '9:00 PM', '2020', 'January', '2020-01-25', '6:00 PM', 'iphone', '6s', '4902430620670', '1', 50000, 'Product out', 'On arrival', 'Product in', 'Product out', '');

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2021`
--

CREATE TABLE `product_info_2021` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2022`
--

CREATE TABLE `product_info_2022` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2023`
--

CREATE TABLE `product_info_2023` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2024`
--

CREATE TABLE `product_info_2024` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_info_2025`
--

CREATE TABLE `product_info_2025` (
  `id` bigint(99) NOT NULL,
  `year_arrival` varchar(5) NOT NULL,
  `month_arrival` varchar(10) NOT NULL,
  `date_arrival` varchar(20) NOT NULL,
  `time_arrival` varchar(20) NOT NULL,
  `year_in` varchar(5) NOT NULL,
  `month_in` varchar(10) NOT NULL,
  `date_in` varchar(20) NOT NULL,
  `time_in` varchar(20) NOT NULL,
  `year_out` varchar(5) NOT NULL,
  `month_out` varchar(10) NOT NULL,
  `date_out` varchar(20) NOT NULL,
  `time_out` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `model` text NOT NULL,
  `product_id` text NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `current_status` varchar(20) NOT NULL,
  `status_arrival` varchar(20) NOT NULL,
  `status_in` varchar(20) NOT NULL,
  `status_out` varchar(20) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_model`
--

CREATE TABLE `product_model` (
  `id` int(11) NOT NULL,
  `date` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL,
  `insert_year` varchar(5) NOT NULL,
  `model` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `price` float NOT NULL,
  `remark` text NOT NULL,
  `quantity` int(20) NOT NULL,
  `total_in` int(20) NOT NULL,
  `total_out` int(20) NOT NULL,
  `partially_out` int(11) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_model`
--

INSERT INTO `product_model` (`id`, `date`, `time`, `insert_year`, `model`, `type`, `name`, `description`, `price`, `remark`, `quantity`, `total_in`, `total_out`, `partially_out`, `user`) VALUES
(2, '', '', '2015', '6s', '', 'iPhone', '', 52000, '1pcs', 6, 6, 6, 0, ''),
(3, '', '', '2015', '6+', '', '', '', 0, '', 0, 0, 0, 0, ''),
(6, '2015-10-08', '11:11 PM', '2015', 'model115122', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(7, '2015-10-08', '11:18 PM', '2015', '555', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(8, '2015-10-08', '11:20 PM', '2015', 'abc', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(9, '2015-10-08', '11:23 PM', '2015', 'loop', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(10, '2015-10-08', '11:34 PM', '2015', '123', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(11, '2015-10-08', '11:43 PM', '2015', 'jkhj', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(12, '2015-10-08', '11:44 PM', '2015', 'tyty', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(13, '2015-10-08', '11:44 PM', '2015', 'kucha', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(14, '2015-10-08', '11:55 PM', '2015', 'kl.kl', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(15, '2015-10-09', '10:22 AM', '2015', 'dsfsdfdsfdsfsdsdhgf86wefuy', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(16, '2015-10-09', '10:44 AM', '2015', 'gfdsfadsfdf\'sd\'f\'sdf\'s\'df;sdf', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(17, '2015-10-09', '11:02 AM', '2015', 'gyjtngtyj\'drf\'tyr\'th\'\'\'', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(18, '2015-10-24', '01:23:26 PM', '2015', 'Binary', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(19, '2015-11-03', '11:14:05 PM', '2015', 'roll', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(20, '2015-11-03', '11:15:40 PM', '2015', '5s', '', 'iPhone', '', 35000, '1pcs', 37, 37, 31, 0, '1'),
(21, '2015-11-04', '05:18:17 PM', '2015', 'cofin', '', '', '', 0, '', 0, 0, 0, 0, '1'),
(23, '2015-11-10', '06:22:59 PM', '2015', 'Dice', 'countable', 'Ludo', 'just play', 200, '1pcs', 2, 0, 0, 0, '1'),
(24, '2015-11-21', '12:21:16 AM', '2015', 'test', 'countable', '', '', 0, '', 0, 0, 0, 0, '1'),
(25, '2016-04-13', '09:02:02 AM', '2016', '5', '', 'iphone', 'Apple', 65000, '1pcs', 3, 3, 2, 0, '1'),
(26, '2016-04-13', '09:08:48 AM', '2016', '6s', '', 'iPhone', 'Apple', 52000, '1pcs', 2, 2, 2, 0, '1'),
(27, '2016-04-13', '09:15:38 AM', '2016', 'Oil', 'uncountable', 'Rupchanda', 'Soyabean oil, Litre', 200, '1pcs', 4, 1, 1, 1, '1'),
(28, '2016-04-13', '09:26:23 AM', '2016', 'Dice', 'countable', 'Ludo', 'just play', 200, '5pcs', 2, 0, 0, 0, '1');

-- --------------------------------------------------------

--
-- Table structure for table `product_remark`
--

CREATE TABLE `product_remark` (
  `id` int(11) NOT NULL,
  `date` varchar(12) NOT NULL,
  `time` varchar(13) NOT NULL,
  `remark` varchar(50) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_remark`
--

INSERT INTO `product_remark` (`id`, `date`, `time`, `remark`, `user`) VALUES
(1, '2015-10-09', '12:59 PM', '5pcs', '1'),
(2, '2015-10-09', '12:59 PM', 'bundle', '1'),
(3, '2015-10-24', '01:22:09 PM', '1pcs', '1');

-- --------------------------------------------------------

--
-- Table structure for table `recipients`
--

CREATE TABLE `recipients` (
  `id` bigint(99) NOT NULL,
  `date` varchar(11) NOT NULL,
  `recipient_name` varchar(50) NOT NULL,
  `recipient_cardNo` varchar(50) NOT NULL,
  `recipient_description` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recipients`
--

INSERT INTO `recipients` (`id`, `date`, `recipient_name`, `recipient_cardNo`, `recipient_description`) VALUES
(1, '2015-11-25', 'AXO', '12345', ''),
(2, '2016-04-13', 'Inter corp', '321456', 'sdfas');

-- --------------------------------------------------------

--
-- Table structure for table `uncountable_minus`
--

CREATE TABLE `uncountable_minus` (
  `id` bigint(99) NOT NULL,
  `date` varchar(11) NOT NULL,
  `time` varchar(20) NOT NULL,
  `model` varchar(50) NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `minus` float NOT NULL,
  `cost` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uncountable_minus`
--

INSERT INTO `uncountable_minus` (`id`, `date`, `time`, `model`, `product_id`, `minus`, `cost`) VALUES
(1, '2015-11-25', '05:22:49 PM', 'Oil', '331', 300, 30),
(2, '2015-11-27', '10:58:52 AM', 'Oil', '1000', 400, 0),
(3, '2015-11-27', '10:59:05 AM', 'Oil', '1000', 400, 0),
(4, '2015-11-27', '11:04:33 AM', 'Oil', '331', 150, 15),
(5, '2015-11-27', '11:19:51 AM', 'Oil', '330', 450, 45),
(6, '2015-11-27', '12:29:12 PM', 'Oil', '330', 30, 3),
(7, '2015-11-27', '01:10:23 PM', 'Oil', '330', 10.5, 1.05),
(8, '2015-11-27', '01:18:36 PM', 'Oil', '332', 450.5, 45.05),
(9, '2015-11-27', '07:09:26 PM', 'Oil', '331', 50.5, 5.05),
(10, '2015-11-27', '07:10:45 PM', 'Oil', '333', 50.5, 5.05),
(11, '2015-11-27', '07:12:41 PM', 'Oil', '334', 100, 10),
(12, '2015-11-27', '07:23:23 PM', 'Oil', '334', 100.5, 10.05),
(13, '2015-11-27', '08:17:46 PM', 'Oil', '331', 50.5, 0),
(14, '2015-11-27', '08:20:59 PM', 'Oil', '331', 100.5, 10.05),
(15, '2015-11-27', '08:24:33 PM', 'Oil', '331', 50, 5),
(16, '2015-11-27', '08:33:21 PM', 'Oil', '331', 90, 9),
(17, '2015-11-27', '08:34:26 PM', 'Oil', '331', 8.5, 0.85),
(18, '2016-04-13', '09:19:27 AM', 'Oil', 'O101', 500, 50);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `hash` text NOT NULL,
  `priority` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `hash`, `priority`) VALUES
(1, '1', '1', '', 'admin'),
(2, 'admin', '123456', '', 'local'),
(3, '4902430620680', '4902430620680', '', 'local');

-- --------------------------------------------------------

--
-- Table structure for table `year`
--

CREATE TABLE `year` (
  `id` int(11) NOT NULL,
  `year` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `year`
--

INSERT INTO `year` (`id`, `year`) VALUES
(5, 2015),
(7, 2016),
(19, 2020),
(18, 2019),
(17, 2017),
(16, 2016);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `lms`
--
ALTER TABLE `lms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_id_list`
--
ALTER TABLE `product_id_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2015`
--
ALTER TABLE `product_info_2015`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2016`
--
ALTER TABLE `product_info_2016`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2017`
--
ALTER TABLE `product_info_2017`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2018`
--
ALTER TABLE `product_info_2018`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2019`
--
ALTER TABLE `product_info_2019`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2020`
--
ALTER TABLE `product_info_2020`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2021`
--
ALTER TABLE `product_info_2021`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2022`
--
ALTER TABLE `product_info_2022`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2023`
--
ALTER TABLE `product_info_2023`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2024`
--
ALTER TABLE `product_info_2024`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_info_2025`
--
ALTER TABLE `product_info_2025`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_model`
--
ALTER TABLE `product_model`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_remark`
--
ALTER TABLE `product_remark`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recipients`
--
ALTER TABLE `recipients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uncountable_minus`
--
ALTER TABLE `uncountable_minus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `year`
--
ALTER TABLE `year`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `lms`
--
ALTER TABLE `lms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product_id_list`
--
ALTER TABLE `product_id_list`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT for table `product_info_2015`
--
ALTER TABLE `product_info_2015`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT for table `product_info_2016`
--
ALTER TABLE `product_info_2016`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `product_info_2017`
--
ALTER TABLE `product_info_2017`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_info_2018`
--
ALTER TABLE `product_info_2018`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_info_2019`
--
ALTER TABLE `product_info_2019`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_info_2020`
--
ALTER TABLE `product_info_2020`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product_info_2021`
--
ALTER TABLE `product_info_2021`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_info_2022`
--
ALTER TABLE `product_info_2022`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_info_2023`
--
ALTER TABLE `product_info_2023`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_info_2024`
--
ALTER TABLE `product_info_2024`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_info_2025`
--
ALTER TABLE `product_info_2025`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_model`
--
ALTER TABLE `product_model`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `product_remark`
--
ALTER TABLE `product_remark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `recipients`
--
ALTER TABLE `recipients`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `uncountable_minus`
--
ALTER TABLE `uncountable_minus`
  MODIFY `id` bigint(99) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `year`
--
ALTER TABLE `year`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
