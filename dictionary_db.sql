-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2024 at 06:34 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dictionary_db`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Antonym` (IN `givenWord` TEXT)   begin
SELECT antonym from antonyms join wordsdata on wordsdata.word_id = antonyms.Word_ID WHERE wordsdata.Word = givenWord;
End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Hypernym` (IN `givenWord` TEXT)   begin
SELECT hypernym from hypernyms join wordsdata on wordsdata.word_id = hypernyms.Word_ID WHERE wordsdata.Word = givenWord;
End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Hyponym` (IN `givenWord` TEXT)   begin
SELECT hyponym from hyponyms join wordsdata on wordsdata.word_id = hyponyms.Word_ID WHERE wordsdata.Word = givenWord;
End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Sentence` (IN `givenWord` MEDIUMTEXT)   begin
SELECT use_in_sentence from sentence join wordsdata on wordsdata.word_id = sentence.Word_ID WHERE wordsdata.Word = givenWord;
End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Synonym` (IN `givenWord` TEXT)   begin
SELECT synonym from synonyms join wordsdata on wordsdata.word_id = synonyms.Word_ID WHERE wordsdata.Word = givenWord;
End$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `antonyms`
--

CREATE TABLE `antonyms` (
  `Antonym_ID` int(11) NOT NULL,
  `Word_ID` int(11) DEFAULT NULL,
  `Antonym` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `antonyms`
--

INSERT INTO `antonyms` (`Antonym_ID`, `Word_ID`, `Antonym`) VALUES
(1, 1, 'Sad'),
(2, 1, 'Unhappy'),
(3, 1, 'Depressed'),
(4, 1, 'Miserable'),
(5, 1, 'Gloomy'),
(6, 2, 'Small'),
(7, 2, 'Little'),
(8, 2, 'Tiny'),
(9, 2, 'Minuscule'),
(10, 2, 'Puny'),
(11, 3, 'Slow'),
(12, 3, 'Sluggish'),
(13, 3, 'Leisurely'),
(14, 3, 'Delayed'),
(15, 3, 'Late'),
(16, 4, 'Cold'),
(17, 4, 'Chilly'),
(18, 4, 'Frigid'),
(19, 4, 'Icy'),
(20, 4, 'Freezing'),
(21, 5, 'Dark'),
(22, 5, 'Dim'),
(23, 5, 'Dull'),
(24, 5, 'Gloomy'),
(25, 5, 'Somber'),
(26, 6, 'Sorrow'),
(27, 6, 'Pain'),
(28, 6, 'Sadness'),
(29, 6, 'Grief'),
(30, 6, 'Anguish'),
(31, 7, 'Tiny'),
(32, 7, 'Minuscule'),
(33, 7, 'Small'),
(34, 7, 'Little'),
(35, 7, 'Puny'),
(36, 8, 'Slow'),
(37, 8, 'Sluggish'),
(38, 8, 'Leisurely'),
(39, 8, 'Delayed'),
(40, 8, 'Late'),
(41, 9, 'Dull'),
(42, 9, 'Dim'),
(43, 9, 'Gloomy'),
(44, 9, 'Somber'),
(45, 9, 'Dark');

-- --------------------------------------------------------

--
-- Table structure for table `hypernyms`
--

CREATE TABLE `hypernyms` (
  `Hypernym_ID` int(11) NOT NULL,
  `Word_ID` int(11) DEFAULT NULL,
  `Hypernym` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hypernyms`
--

INSERT INTO `hypernyms` (`Hypernym_ID`, `Word_ID`, `Hypernym`) VALUES
(1, 1, 'Emotion'),
(2, 1, 'Feeling'),
(3, 1, 'Sentiment'),
(4, 1, 'Mood'),
(5, 1, 'Attitude'),
(6, 2, 'Size'),
(7, 2, 'Magnitude'),
(8, 2, 'Dimension'),
(9, 2, 'Extent'),
(10, 2, 'Proportion'),
(11, 3, 'Speed'),
(12, 3, 'Velocity'),
(13, 3, 'Rate'),
(14, 3, 'Pace'),
(15, 3, 'Swiftness'),
(16, 4, 'Temperature'),
(17, 4, 'Heat'),
(18, 4, 'Warmth'),
(19, 4, 'Coolness'),
(20, 4, 'Chill'),
(21, 5, 'Brightness'),
(22, 5, 'Light'),
(23, 5, 'Illumination'),
(24, 5, 'Radiance'),
(25, 5, 'Glow'),
(26, 6, 'Emotion'),
(27, 6, 'Feeling'),
(28, 6, 'Sentiment'),
(29, 6, 'Mood'),
(30, 6, 'Attitude'),
(31, 7, 'Size'),
(32, 7, 'Magnitude'),
(33, 7, 'Dimension'),
(34, 7, 'Extent'),
(35, 7, 'Proportion'),
(36, 8, 'Speed'),
(37, 8, 'Velocity'),
(38, 8, 'Rate'),
(39, 8, 'Pace'),
(40, 8, 'Swiftness'),
(41, 9, 'Brightness'),
(42, 9, 'Light'),
(43, 9, 'Illumination'),
(44, 9, 'Radiance'),
(45, 9, 'Glow');

-- --------------------------------------------------------

--
-- Table structure for table `hyponyms`
--

CREATE TABLE `hyponyms` (
  `Hyponym_ID` int(11) NOT NULL,
  `Word_ID` int(11) DEFAULT NULL,
  `Hyponym` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hyponyms`
--

INSERT INTO `hyponyms` (`Hyponym_ID`, `Word_ID`, `Hyponym`) VALUES
(1, 1, 'Elation'),
(2, 1, 'Euphoria'),
(3, 1, 'Delight'),
(4, 1, 'Cheerfulness'),
(5, 1, 'Jubilation'),
(6, 2, 'Enormity'),
(7, 2, 'Immensity'),
(8, 2, 'Gigantism'),
(9, 2, 'Massiveness'),
(10, 2, 'Hugeness'),
(11, 3, 'Rapidness'),
(12, 3, 'Swiftness'),
(13, 3, 'Speediness'),
(14, 3, 'Briskness'),
(15, 3, 'Fleetness'),
(16, 4, 'Scorching'),
(17, 4, 'Searing'),
(18, 4, 'Blazing'),
(19, 4, 'Boiling'),
(20, 4, 'Sweltering'),
(21, 5, 'Radiance'),
(22, 5, 'Luminance'),
(23, 5, 'Glowing'),
(24, 5, 'Beaming'),
(25, 5, 'Dazzling'),
(26, 6, 'Sorrow'),
(27, 6, 'Grief'),
(28, 6, 'Sadness'),
(29, 6, 'Anguish'),
(30, 6, 'Despair'),
(31, 7, 'Minuteness'),
(32, 7, 'Smallness'),
(33, 7, 'Petiteness'),
(34, 7, 'Tininess'),
(35, 7, 'Puniness'),
(36, 8, 'Slowness'),
(37, 8, 'Sluggishness'),
(38, 8, 'Lethargy'),
(39, 8, 'Lassitude'),
(40, 8, 'Indolence'),
(41, 9, 'Dimness'),
(42, 9, 'Dullness'),
(43, 9, 'Gloominess'),
(44, 9, 'Somberness'),
(45, 9, 'Darkness');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `Login_ID` int(11) NOT NULL,
  `User_ID` int(11) DEFAULT NULL,
  `Login_Time` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`Login_ID`, `User_ID`, `Login_Time`) VALUES
(1, 1, '2024-08-18 14:30:00'),
(2, 2, '2024-08-19 15:45:00'),
(3, 3, '2024-08-20 16:50:00'),
(4, 4, '2024-08-21 17:55:00'),
(5, 5, '2024-08-22 18:59:00'),
(6, 6, '2024-08-19 01:38:15'),
(8, 8, '2024-08-19 01:41:46'),
(9, 6, '2024-08-19 01:44:14'),
(10, 6, '2024-08-19 01:46:20'),
(11, 6, '2024-08-19 01:50:51'),
(12, 9, '2024-08-20 23:58:07'),
(13, 1, '2024-08-21 07:56:39'),
(14, 1, '2024-08-21 08:00:12'),
(15, 1, '2024-08-21 08:58:09'),
(16, 1, '2024-08-21 09:38:52'),
(17, 1, '2024-08-21 10:29:25'),
(18, 10, '2024-08-21 11:24:01');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `User_ID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `E_mail` varchar(100) DEFAULT NULL,
  `Created_At` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`User_ID`, `Name`, `E_mail`, `Created_At`) VALUES
(1, 'Rahul Sharma', 'rahulsharma@gmail.com', '2024-08-19'),
(2, 'Priya Patel', 'priyapatel@gmail.com', '2024-08-19'),
(3, 'Mira Kumari', 'mira@gmail@gmail.com', '2024-08-19'),
(4, 'Vikas Singh', 'vikassingh@gmail.com', '2024-08-19'),
(5, 'Rajesh Gupta', 'rajeshgupta@gmail.com', '2024-08-19'),
(6, 'Krishna Mehra', 'krishna@gmail.com', '2024-08-19'),
(8, 'Neha Patel', 'nehapatel@gmail.com', '2024-08-19'),
(9, 'Priya Sharma', 'priya@gmail.com', '2024-08-20'),
(10, 'qwqw', 'qwe@gmail.com', '2024-08-21');

-- --------------------------------------------------------

--
-- Table structure for table `search_history`
--

CREATE TABLE `search_history` (
  `Search_ID` int(11) NOT NULL,
  `User_ID` int(11) DEFAULT NULL,
  `Search_Query` varchar(255) DEFAULT NULL,
  `search_operation` varchar(100) NOT NULL,
  `Search_Time` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `search_history`
--

INSERT INTO `search_history` (`Search_ID`, `User_ID`, `Search_Query`, `search_operation`, `Search_Time`) VALUES
(1, 1, 'happy', '', '2024-08-18 14:35:00'),
(2, 2, 'big', '', '2024-08-19 15:50:00'),
(3, 3, 'fast', '', '2024-08-20 17:00:00'),
(4, 4, 'hot', '', '2024-08-21 17:10:00'),
(5, 5, 'light', '', '2024-08-22 18:15:00'),
(7, 6, '-------', 'Login success', '2024-08-19 01:44:14'),
(8, 6, '-------', 'Login success', '2024-08-19 01:46:20'),
(9, 6, 'Happy', 'Search', '2024-08-19 01:46:57'),
(10, 6, 'Happy', 'Synonym', '2024-08-19 01:47:06'),
(11, 6, 'Happy', 'Antonym', '2024-08-19 01:47:13'),
(12, 6, 'happy', 'Search', '2024-08-19 01:47:32'),
(13, 6, 'Big', 'Search', '2024-08-19 01:47:46'),
(14, 6, '-------', 'Login success', '2024-08-19 01:50:51'),
(15, 6, 'Hot', 'Search', '2024-08-19 01:51:00'),
(16, 6, 'Hot', 'Synonym', '2024-08-19 01:51:04'),
(17, 6, 'Hot', 'Antonym', '2024-08-19 01:51:05'),
(18, 6, 'Hot', 'Hypernym', '2024-08-19 01:51:06'),
(19, 6, 'Hot', 'Hyponym', '2024-08-19 01:51:07'),
(20, 6, 'Hot', 'Use in sentence', '2024-08-19 01:51:08'),
(21, 6, 'Book', 'Word Suggested', '2024-08-19 01:51:23'),
(22, 6, 'Big', 'Reading Word', '2024-08-19 01:51:28'),
(23, 6, 'Fast', 'Reading Word', '2024-08-19 01:51:29'),
(24, 6, 'Happy', 'Reading Word', '2024-08-19 01:51:29'),
(25, 6, 'Hot', 'Reading Word', '2024-08-19 01:51:30'),
(26, 6, 'Huge', 'Reading Word', '2024-08-19 01:51:31'),
(27, 6, 'Huge', 'Added to short note', '2024-08-19 01:51:31'),
(28, 6, 'Joy', 'Reading Word', '2024-08-19 01:51:31'),
(29, 6, 'Joy', 'Added to short note', '2024-08-19 01:51:31'),
(30, 6, 'Light', 'Reading Word', '2024-08-19 01:51:33'),
(31, 6, 'Light', 'Reading ended', '2024-08-19 01:51:33'),
(32, 6, 'qw', 'insertion in short note', '2024-08-19 01:51:42'),
(33, 6, 'qw', 'deletion in short note', '2024-08-19 01:51:45'),
(34, 6, '---', 'reading short note', '2024-08-19 01:51:51'),
(35, 6, 'huge', 'modification in short note', '2024-08-19 01:51:58'),
(36, 6, '---', 'reading short note', '2024-08-19 01:52:02'),
(37, 6, '----', 'Short note printted', '2024-08-19 01:52:08'),
(38, 9, '-------', 'Login success', '2024-08-20 23:58:07'),
(39, 9, 'Hot', 'Search', '2024-08-20 23:58:14'),
(40, 9, 'Hot', 'Synonym', '2024-08-20 23:58:18'),
(41, 9, 'Hot', 'Antonym', '2024-08-20 23:58:19'),
(42, 9, 'Hot', 'Hypernym', '2024-08-20 23:58:22'),
(43, 9, 'Hot', 'Hyponym', '2024-08-20 23:58:23'),
(44, 9, 'Hot', 'Use in sentence', '2024-08-20 23:58:23'),
(45, 9, 'Sad', 'Word Suggested', '2024-08-20 23:58:48'),
(46, 9, 'Big', 'Reading Word', '2024-08-20 23:58:54'),
(47, 9, 'Fast', 'Reading Word', '2024-08-20 23:58:55'),
(48, 9, 'Happy', 'Reading Word', '2024-08-20 23:58:55'),
(49, 9, 'Hot', 'Reading Word', '2024-08-20 23:58:55'),
(50, 9, 'Huge', 'Reading Word', '2024-08-20 23:58:56'),
(51, 9, 'Joy', 'Reading Word', '2024-08-20 23:58:58'),
(52, 9, 'Joy', 'Added to short note', '2024-08-20 23:58:58'),
(53, 9, 'Light', 'Reading Word', '2024-08-20 23:58:59'),
(54, 9, 'Light', 'Added to short note', '2024-08-20 23:58:59'),
(55, 9, 'Quick', 'Reading Word', '2024-08-20 23:59:00'),
(56, 9, 'Quick', 'Reading ended', '2024-08-20 23:59:00'),
(57, 9, 'cd', 'insertion in short note', '2024-08-20 23:59:08'),
(58, 9, 'cd', 'deletion in short note', '2024-08-20 23:59:12'),
(59, 9, 'cd - not avilable', 'modification in short note', '2024-08-20 23:59:18'),
(60, 9, 'old and new words were same', 'modification in short note', '2024-08-20 23:59:33'),
(61, 9, '---', 'reading short note', '2024-08-20 23:59:35'),
(62, 9, '----', 'Short note printted', '2024-08-20 23:59:38'),
(63, 1, '-------', 'Login success', '2024-08-21 07:56:39'),
(64, 1, 'happy', 'Search', '2024-08-21 07:56:45'),
(65, 1, 'happy', 'Synonym', '2024-08-21 07:56:47'),
(66, 1, 'happy', 'Antonym', '2024-08-21 07:56:49'),
(67, 1, 'happy', 'Hypernym', '2024-08-21 07:56:50'),
(68, 1, '-------', 'Login success', '2024-08-21 08:00:12'),
(69, 1, 'happy', 'Search', '2024-08-21 08:00:15'),
(70, 1, 'happy', 'Synonym', '2024-08-21 08:00:18'),
(71, 1, '-------', 'Login success', '2024-08-21 08:58:09'),
(72, 1, 'big', 'Search', '2024-08-21 08:58:16'),
(73, 1, 'big', 'Synonym', '2024-08-21 08:58:19'),
(74, 1, 'big', 'Antonym', '2024-08-21 08:58:22'),
(75, 1, 'big', 'Hypernym', '2024-08-21 08:58:27'),
(76, 1, 'big', 'Hyponym', '2024-08-21 08:58:47'),
(77, 1, 'big', 'Use in sentence', '2024-08-21 08:58:51'),
(78, 1, '-------', 'Login success', '2024-08-21 09:38:52'),
(79, 1, 'Joyful', 'Search', '2024-08-21 09:38:57'),
(80, 1, '-------', 'Login success', '2024-08-21 10:29:25'),
(81, 1, 'Big', 'Reading Word', '2024-08-21 10:29:28'),
(82, 1, 'Big', 'Added to short note', '2024-08-21 10:29:28'),
(83, 1, 'Fast', 'Reading Word', '2024-08-21 10:29:29'),
(84, 1, 'Fast', 'Added to short note', '2024-08-21 10:29:29'),
(85, 1, 'Happy', 'Reading Word', '2024-08-21 10:29:30'),
(86, 1, 'Happy', 'Added to short note', '2024-08-21 10:29:30'),
(87, 1, 'Hot', 'Reading Word', '2024-08-21 10:29:31'),
(88, 1, 'Hot', 'Reading ended', '2024-08-21 10:29:31'),
(89, 1, '----', 'Short note printted', '2024-08-21 10:29:33'),
(90, 10, '-------', 'Login success', '2024-08-21 11:24:01'),
(91, 10, 'rat', 'Search', '2024-08-21 11:24:11'),
(92, 10, 'sad', 'Search', '2024-08-21 11:24:19'),
(93, 10, 'cool', 'Search', '2024-08-21 11:24:26'),
(94, 10, 'hot', 'Search', '2024-08-21 11:24:32'),
(95, 10, 'hot', 'Use in sentence', '2024-08-21 11:24:50');

-- --------------------------------------------------------

--
-- Table structure for table `sentence`
--

CREATE TABLE `sentence` (
  `Sentence_ID` int(11) NOT NULL,
  `Word_ID` int(11) DEFAULT NULL,
  `use_in_Sentence` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sentence`
--

INSERT INTO `sentence` (`Sentence_ID`, `Word_ID`, `use_in_Sentence`) VALUES
(1, 1, 'The happy couple smiled at each other.'),
(2, 1, 'She felt happy when she received the news.'),
(3, 1, 'The happy memory stayed with him forever.'),
(4, 1, 'The children were happy to be on summer break.'),
(5, 1, 'The happy dog wagged its tail.'),
(6, 2, 'The big house had many rooms.'),
(7, 2, 'He was a big fan of the rock band.'),
(8, 2, 'The big test was coming up soon.'),
(9, 2, 'The big brown bear climbed up the mountain.'),
(10, 2, 'The big city was full of excitement.'),
(11, 3, 'The fast car sped down the highway.'),
(12, 3, 'She was a fast learner and picked up the skill quickly.'),
(13, 3, 'The fast food restaurant served burgers and fries.'),
(14, 3, 'The fast train took us to our destination in no time.'),
(15, 3, 'The fast runner sprinted across the finish line.'),
(16, 4, 'The hot sun beat down on us.'),
(17, 4, 'The hot chocolate warmed my hands.'),
(18, 4, 'The hot sauce added flavor to the dish.'),
(19, 4, 'The hot springs were a perfect place to relax.'),
(20, 4, 'The hot summer day was perfect for swimming.'),
(21, 5, 'The light bulb turned on.'),
(22, 5, 'The light from the window illuminated the room.'),
(23, 5, 'The light breeze felt good on my skin.'),
(24, 5, 'The light color of the walls made the room look bigger.'),
(25, 5, 'The light at the end of the tunnel gave us hope.'),
(26, 6, 'The joy of learning something new was exciting.'),
(27, 6, 'The joy on her face was contagious.'),
(28, 6, 'The joy of helping others was rewarding.'),
(29, 6, 'The joy of spending time with loved ones was priceless.'),
(30, 6, 'The joy of achieving a goal was satisfying.'),
(31, 7, 'The huge elephant roamed the savannah.'),
(32, 7, 'The huge mountain towered above us.'),
(33, 7, 'The huge crowd cheered at the concert.'),
(34, 7, 'The huge storm caused a lot of damage.'),
(35, 7, 'The huge success of the company was impressive.'),
(36, 8, 'The quick fix solved the problem.'),
(37, 8, 'The quick learner picked up the skill fast.'),
(38, 8, 'The quick run through the park was refreshing.'),
(39, 8, 'The quick response to the emergency saved lives.'),
(40, 8, 'The quick thinking saved the day.'),
(41, 9, 'The radiant smile lit up the room.'),
(42, 9, 'The radiant colors of the sunset were breathtaking.'),
(43, 9, 'The radiant glow of the candles created a warm ambiance.'),
(44, 9, 'The radiant energy of the crowd was infectious.'),
(45, 9, 'The radiant beauty of nature inspired me.');

-- --------------------------------------------------------

--
-- Table structure for table `suggested_words`
--

CREATE TABLE `suggested_words` (
  `Id` int(11) NOT NULL,
  `User_Id` int(11) DEFAULT NULL,
  `Word` text DEFAULT NULL,
  `Meaning` text DEFAULT NULL,
  `Pronounciation` varchar(200) DEFAULT NULL,
  `Status` varchar(50) NOT NULL DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suggested_words`
--

INSERT INTO `suggested_words` (`Id`, `User_Id`, `Word`, `Meaning`, `Pronounciation`, `Status`) VALUES
(1, 1, 'joyful', 'feeling or showing great pleasure', '/dʒɔɪfʊl/', 'pending'),
(2, 2, 'enormous', 'extremely large or immense', '/ɪnɔrməs/', 'pending'),
(3, 3, 'swift', 'moving or able to move quickly', '/swɪft/', 'pending'),
(4, 4, 'blazing', 'burning fiercely or intensely', '/bleɪzɪŋ/', 'approved'),
(5, 5, 'glowing', 'emitting light, typically in the form of rays or a steady flame', '/ɡloʊɪŋ/', 'pending'),
(6, 6, 'Book', 'xyz', 'qwe', 'Pending'),
(7, 9, 'Sad', 'Not HAppy', '--', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `synonyms`
--

CREATE TABLE `synonyms` (
  `Synonym_ID` int(11) NOT NULL,
  `Word_ID` int(11) DEFAULT NULL,
  `Synonym` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `synonyms`
--

INSERT INTO `synonyms` (`Synonym_ID`, `Word_ID`, `Synonym`) VALUES
(1, 1, 'Joyful'),
(2, 1, 'Cheerful'),
(3, 1, 'Jubilant'),
(4, 1, 'Elated'),
(5, 1, 'Euphoric'),
(6, 2, 'Large'),
(7, 2, 'Enormous'),
(8, 2, 'Gigantic'),
(9, 2, 'Massive'),
(10, 2, 'Huge'),
(11, 3, 'Quick'),
(12, 3, 'Rapid'),
(13, 3, 'Swift'),
(14, 3, 'Speedy'),
(15, 3, 'Brisk'),
(16, 4, 'Scorching'),
(17, 4, 'Sizzling'),
(18, 4, 'Blazing'),
(19, 4, 'Sweltering'),
(20, 4, 'Boiling'),
(21, 5, 'Bright'),
(22, 5, 'Radiant'),
(23, 5, 'Luminous'),
(24, 5, 'Glowing'),
(25, 5, 'Dazzling'),
(26, 6, 'Happiness'),
(27, 6, 'Delight'),
(28, 6, 'Cheerfulness'),
(29, 6, 'Jubilation'),
(30, 6, 'Elation'),
(31, 7, 'Enormous'),
(32, 7, 'Gigantic'),
(33, 7, 'Massive'),
(34, 7, 'Immense'),
(35, 7, 'Colossal'),
(36, 8, 'Fast'),
(37, 8, 'Rapid'),
(38, 8, 'Swift'),
(39, 8, 'Prompt'),
(40, 8, 'Brisk'),
(41, 9, 'Glowing'),
(42, 9, 'Luminous'),
(43, 9, 'Bright'),
(44, 9, 'Dazzling'),
(45, 9, 'Resplendent');

-- --------------------------------------------------------

--
-- Table structure for table `user_auth`
--

CREATE TABLE `user_auth` (
  `User_Auth_ID` int(11) NOT NULL,
  `Username` varchar(100) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `User_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_auth`
--

INSERT INTO `user_auth` (`User_Auth_ID`, `Username`, `Password`, `User_ID`) VALUES
(1, 'rahul_1', '123123', 1),
(2, 'priya_2', '456456', 2),
(3, 'mira_3', '789789', 3),
(4, 'vikas_4', '963963', 4),
(5, 'rajesh_5', '852852', 5),
(6, 'krishnamehra_91', '156156', 6),
(7, 'Nehapatel_66', '146146', 8),
(8, 'Priyasharma_6', '564564', 9),
(9, 'Qwqw_17', '1111', 10);

-- --------------------------------------------------------

--
-- Table structure for table `wordsdata`
--

CREATE TABLE `wordsdata` (
  `Word_Id` int(11) NOT NULL,
  `Word` text DEFAULT NULL,
  `Pronounciation` text DEFAULT NULL,
  `Meaning` text DEFAULT NULL,
  `Part_Of_Speech` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wordsdata`
--

INSERT INTO `wordsdata` (`Word_Id`, `Word`, `Pronounciation`, `Meaning`, `Part_Of_Speech`) VALUES
(1, 'Happy', '/hæpi/', 'Feeling or showing pleasure or contentment', 'Adjective'),
(2, 'Big', '/bɪɡ/', 'Of a large size', 'Adjective'),
(3, 'Fast', '/fæst/', 'Moving or able to move quickly', 'Adjective'),
(4, 'Hot', '/hɒt/', 'Having a high temperature', 'Adjective'),
(5, 'Light', '/laɪt/', 'Not heavy or serious', 'Adjective'),
(6, 'Joy', '/dʒɔɪ/', 'A feeling of great happiness', 'Noun'),
(7, 'Huge', '/huːdʒ/', 'Extremely large', 'Adjective'),
(8, 'Quick', '/kwɪk/', 'Moving or able to move quickly', 'Adjective'),
(9, 'Scorching', '/skɔːrtʃɪŋ/', 'Extremely hot', 'Adjective'),
(10, 'Radiant', '/reɪdiənt/', 'Giving off light or heat', 'Adjective'),
(11, 'joyful', '/ˈdʒɔɪfʊl/', 'feeling or expressing great pleasure or happiness', 'adjective');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `antonyms`
--
ALTER TABLE `antonyms`
  ADD PRIMARY KEY (`Antonym_ID`),
  ADD KEY `Word_ID` (`Word_ID`);

--
-- Indexes for table `hypernyms`
--
ALTER TABLE `hypernyms`
  ADD PRIMARY KEY (`Hypernym_ID`),
  ADD KEY `Word_ID` (`Word_ID`);

--
-- Indexes for table `hyponyms`
--
ALTER TABLE `hyponyms`
  ADD PRIMARY KEY (`Hyponym_ID`),
  ADD KEY `Word_ID` (`Word_ID`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`Login_ID`),
  ADD KEY `User_ID` (`User_ID`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`User_ID`),
  ADD UNIQUE KEY `E_mail` (`E_mail`);

--
-- Indexes for table `search_history`
--
ALTER TABLE `search_history`
  ADD PRIMARY KEY (`Search_ID`);

--
-- Indexes for table `sentence`
--
ALTER TABLE `sentence`
  ADD PRIMARY KEY (`Sentence_ID`),
  ADD KEY `Word_ID` (`Word_ID`);

--
-- Indexes for table `suggested_words`
--
ALTER TABLE `suggested_words`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `synonyms`
--
ALTER TABLE `synonyms`
  ADD PRIMARY KEY (`Synonym_ID`),
  ADD KEY `Word_ID` (`Word_ID`);

--
-- Indexes for table `user_auth`
--
ALTER TABLE `user_auth`
  ADD PRIMARY KEY (`User_Auth_ID`),
  ADD KEY `User_ID` (`User_ID`);

--
-- Indexes for table `wordsdata`
--
ALTER TABLE `wordsdata`
  ADD PRIMARY KEY (`Word_Id`),
  ADD UNIQUE KEY `Word` (`Word`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `antonyms`
--
ALTER TABLE `antonyms`
  MODIFY `Antonym_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `hypernyms`
--
ALTER TABLE `hypernyms`
  MODIFY `Hypernym_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `hyponyms`
--
ALTER TABLE `hyponyms`
  MODIFY `Hyponym_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `Login_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `User_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `search_history`
--
ALTER TABLE `search_history`
  MODIFY `Search_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT for table `sentence`
--
ALTER TABLE `sentence`
  MODIFY `Sentence_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `suggested_words`
--
ALTER TABLE `suggested_words`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `synonyms`
--
ALTER TABLE `synonyms`
  MODIFY `Synonym_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `user_auth`
--
ALTER TABLE `user_auth`
  MODIFY `User_Auth_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `wordsdata`
--
ALTER TABLE `wordsdata`
  MODIFY `Word_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `antonyms`
--
ALTER TABLE `antonyms`
  ADD CONSTRAINT `antonyms_ibfk_1` FOREIGN KEY (`Word_ID`) REFERENCES `wordsdata` (`Word_Id`);

--
-- Constraints for table `hypernyms`
--
ALTER TABLE `hypernyms`
  ADD CONSTRAINT `hypernyms_ibfk_1` FOREIGN KEY (`Word_ID`) REFERENCES `wordsdata` (`Word_Id`);

--
-- Constraints for table `hyponyms`
--
ALTER TABLE `hyponyms`
  ADD CONSTRAINT `hyponyms_ibfk_1` FOREIGN KEY (`Word_ID`) REFERENCES `wordsdata` (`Word_Id`);

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `register` (`User_ID`);

--
-- Constraints for table `search_history`
--
ALTER TABLE `search_history`
  ADD CONSTRAINT `search_history_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `register` (`User_ID`);

--
-- Constraints for table `sentence`
--
ALTER TABLE `sentence`
  ADD CONSTRAINT `sentence_ibfk_1` FOREIGN KEY (`Word_ID`) REFERENCES `wordsdata` (`Word_Id`);

--
-- Constraints for table `synonyms`
--
ALTER TABLE `synonyms`
  ADD CONSTRAINT `synonyms_ibfk_1` FOREIGN KEY (`Word_ID`) REFERENCES `wordsdata` (`Word_Id`);

--
-- Constraints for table `user_auth`
--
ALTER TABLE `user_auth`
  ADD CONSTRAINT `user_auth_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `register` (`User_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
