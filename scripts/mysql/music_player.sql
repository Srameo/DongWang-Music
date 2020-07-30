/*
 Navicat Premium Data Transfer

 Source Server         : aliyun docker mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 101.200.189.12:3306
 Source Schema         : music_player

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 30/07/2020 18:16:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `uid` int NOT NULL,
  `mid` int NOT NULL,
  `content` text NOT NULL,
  `comment_time` datetime NOT NULL,
  PRIMARY KEY (`uid`,`mid`,`comment_time`) USING BTREE,
  KEY `mid` (`mid`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `music` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for history_list
-- ----------------------------
DROP TABLE IF EXISTS `history_list`;
CREATE TABLE `history_list` (
  `uid` int NOT NULL,
  `mid` int NOT NULL,
  `play_time` datetime NOT NULL,
  PRIMARY KEY (`uid`,`mid`,`play_time`) USING BTREE,
  KEY `mid` (`mid`),
  CONSTRAINT `history_list_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_list_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `music` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `num` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1465067355 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for music_style
-- ----------------------------
DROP TABLE IF EXISTS `music_style`;
CREATE TABLE `music_style` (
  `mid` int NOT NULL,
  `sid` int NOT NULL,
  PRIMARY KEY (`mid`,`sid`),
  KEY `sid` (`sid`),
  CONSTRAINT `music_style_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `music` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `music_style_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `style` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for singer_music
-- ----------------------------
DROP TABLE IF EXISTS `singer_music`;
CREATE TABLE `singer_music` (
  `sid` int NOT NULL,
  `mid` int NOT NULL,
  PRIMARY KEY (`sid`,`mid`),
  KEY `mid` (`mid`),
  CONSTRAINT `singer_music_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `singers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `singer_music_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `music` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for singers
-- ----------------------------
DROP TABLE IF EXISTS `singers`;
CREATE TABLE `singers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `location` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1465067357 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for stars
-- ----------------------------
DROP TABLE IF EXISTS `stars`;
CREATE TABLE `stars` (
  `uid` int NOT NULL,
  `mid` int NOT NULL,
  `star_time` datetime NOT NULL,
  PRIMARY KEY (`uid`,`mid`),
  KEY `mid` (`mid`),
  CONSTRAINT `stars_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stars_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `music` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for style
-- ----------------------------
DROP TABLE IF EXISTS `style`;
CREATE TABLE `style` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user_style
-- ----------------------------
DROP TABLE IF EXISTS `user_style`;
CREATE TABLE `user_style` (
  `uid` int NOT NULL,
  `sid` int NOT NULL,
  PRIMARY KEY (`uid`,`sid`),
  KEY `sid` (`sid`),
  CONSTRAINT `user_style_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_style_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `style` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `gender` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
