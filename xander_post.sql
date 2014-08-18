-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Авг 18 2014 г., 13:26
-- Версия сервера: 5.5.38-0ubuntu0.14.04.1
-- Версия PHP: 5.5.9-1ubuntu4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `xander_post`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Post`
--

CREATE TABLE IF NOT EXISTS `Post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `text` varchar(4000) NOT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`author`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Дамп данных таблицы `Post`
--

INSERT INTO `Post` (`id`, `title`, `text`, `author`) VALUES
(8, 'brand_new_post_1703', 'somenew_text321', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `PostRating`
--

CREATE TABLE IF NOT EXISTS `PostRating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `rate` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`post_id`),
  KEY `post_id` (`post_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `PostRating`
--

INSERT INTO `PostRating` (`id`, `post_id`, `user_id`, `rate`) VALUES
(2, 8, 12, 7);

-- --------------------------------------------------------

--
-- Структура таблицы `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Дамп данных таблицы `User`
--

INSERT INTO `User` (`id`, `email`, `password`, `enabled`) VALUES
(1, 'test@test.com', '$2a$04$zjHuPJ1YoPowVLFohaCjeOumqI7bb88//GBh4qDvwJO9Rt48TKL9i', 1),
(2, 'admin@test.com', '$2a$04$GNOzn0dLRiGChAXCCz0fQekQ3AfeU3XavSSGoKtm8k9wLQJ.s6Vqq', 1),
(12, 'newtest@test.com', '$2a$10$5ffPvVsZ/dshxI/D98OPw.q.vzb6vK9Nf0Q/XlAkIv5kGbrTtXaDq', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `UserRole`
--

CREATE TABLE IF NOT EXISTS `UserRole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(64) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role` (`role`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `UserRole`
--

INSERT INTO `UserRole` (`id`, `role`, `parent_id`) VALUES
(2, 'ROLE_ADMIN', NULL),
(3, 'ROLE_USER', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(2, 2),
(1, 3),
(12, 3);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `Post`
--
ALTER TABLE `Post`
  ADD CONSTRAINT `Post_ibfk_1` FOREIGN KEY (`author`) REFERENCES `User` (`id`);

--
-- Ограничения внешнего ключа таблицы `PostRating`
--
ALTER TABLE `PostRating`
  ADD CONSTRAINT `PostRating_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `Post` (`id`),
  ADD CONSTRAINT `PostRating_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

--
-- Ограничения внешнего ключа таблицы `UserRole`
--
ALTER TABLE `UserRole`
  ADD CONSTRAINT `UserRole_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `UserRole` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `UserRole` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
