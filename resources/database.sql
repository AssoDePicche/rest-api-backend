CREATE TABLE IF NOT EXISTS Countries (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE NOT NULL,
  iso3166_alpha2 CHAR(2) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) NOT NULL,
  surname VARCHAR(60) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  birth DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Artists (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) NOT NULL,
  surname VARCHAR(60) NOT NULL,
  birth DATE NOT NULL,
  country INT NOT NULL,
  FOREIGN KEY (country) REFERENCES Countries (id)
);

CREATE TABLE IF NOT EXISTS ArtistsRoles (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Publishers (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE NOT NULL,
  founded_in DATE NOT NULL,
  country INT NOT NULL,
  FOREIGN KEY (country) REFERENCES Countries (id)
);

CREATE TABLE IF NOT EXISTS Genres (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Series (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE NOT NULL,
  volumes INT DEFAULT 0,
  started_in DATE NOT NULL,
  ended_in DATE DEFAULT NULL,
  publisher INT NOT NULL,
  FOREIGN KEY (publisher) REFERENCES Publishers (id)
);
