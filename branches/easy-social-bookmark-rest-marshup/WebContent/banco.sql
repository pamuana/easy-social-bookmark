
CREATE DATABASE IF NOT EXISTS bookmarks;
USE bookmarks;

DROP TABLE IF EXISTS `bookmarks`.`Bookmark`;
CREATE TABLE  `bookmarks`.`Bookmark` (
  `id` bigint(20) NOT NULL auto_increment,
  `url` varchar(255) NOT NULL,
  `idUser` bigint(20) default NULL,
  `name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `tags` text,
  PRIMARY KEY  (`id`)
);

DROP TABLE IF EXISTS `bookmarks`.`Comment`;
CREATE TABLE  `bookmarks`.`Comment` (
  `id` bigint(20) NOT NULL auto_increment,
  `text` varchar(255) default NULL,
  `idBookmark` bigint(20) default NULL,
  `idUser` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
);

DROP TABLE IF EXISTS `bookmarks`.`Community`;
CREATE TABLE  `bookmarks`.`Community` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
);


DROP TABLE IF EXISTS `bookmarks`.`Community_Bookmark`;
CREATE TABLE  `bookmarks`.`Community_Bookmark` (
  `Community_id` bigint(20) NOT NULL,
  `bookmarks_id` bigint(20) NOT NULL
);

DROP TABLE IF EXISTS `bookmarks`.`Community_User`;
CREATE TABLE  `bookmarks`.`Community_User` (
  `Community_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL
);

DROP TABLE IF EXISTS `bookmarks`.`Participant`;
CREATE TABLE  `bookmarks`.`Participant` (
  `id` bigint(20) NOT NULL auto_increment,
  `role` enum('ADMIN','USER') default NULL,
  `idCommunity` bigint(20) NOT NULL,
  `idUser` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
);

DROP TABLE IF EXISTS `bookmarks`.`User`;
CREATE TABLE  `bookmarks`.`User` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) default NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
);


