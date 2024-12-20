CREATE TABLE IF NOT EXISTS Countries (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE NOT NULL,
  iso3166_alpha2 CHAR(2) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  picture CHAR(32) NOT NULL DEFAULT 'user-default-profile-picture',
  name VARCHAR(60) NOT NULL,
  surname VARCHAR(60) NOT NULL,
  username VARCHAR(30) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password BINARY(60) NOT NULL,
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
  picture CHAR(32) NOT NULL DEFAULT 'publisher-default-cover-picture',
  name VARCHAR(60) UNIQUE NOT NULL,
  founded_in DATE NOT NULL,
  country INT NOT NULL,
  FOREIGN KEY (country) REFERENCES Countries (id)
);

CREATE TABLE IF NOT EXISTS Genres (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Formats (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Series (
  id INT PRIMARY KEY AUTO_INCREMENT,
  picture CHAR(32) NOT NULL DEFAULT 'series-default-cover-picture',
  name VARCHAR(60) UNIQUE NOT NULL,
  volumes INT DEFAULT 0,
  started_in DATE NOT NULL,
  ended_in DATE DEFAULT NULL,
  publisher INT NOT NULL,
  format INT NOT NULL,
  FOREIGN KEY (publisher) REFERENCES Publishers (id),
  FOREIGN KEY (format) REFERENCES Formats (id)
);

CREATE TABLE IF NOT EXISTS SeriesGenres (
  series INT NOT NULL,
  genre INT NOT NULL,
  FOREIGN KEY (series) REFERENCES Series (id),
  FOREIGN KEY (genre) REFERENCES Genres (id),
  PRIMARY KEY (series, genre)
);

CREATE TABLE IF NOT EXISTS SeriesArtists (
  series INT NOT NULL,
  artist INT NOT NULL,
  role INT NOT NULL,
  FOREIGN KEY (series) REFERENCES Series (id),
  FOREIGN KEY (artist) REFERENCES Artists (id),
  FOREIGN KEY (role) REFERENCES ArtitstsRoles (id),
  PRIMARY KEY (series, artist, role)
);

CREATE TABLE IF NOT EXISTS Comics (
  id INT PRIMARY KEY AUTO_INCREMENT,
  picture CHAR(32) NOT NULL DEFAULT 'comic-default-cover-picture',
  cover_price DECIMAL (6, 2) NOT NULL,
  pages INT NOT NULL,
  published_in DATE NOT NULL,
  series INT NOT NULL,
  FOREIGN KEY (series) REFERENCES Series (id)
);

CREATE TABLE IF NOT EXISTS ComicsReadingStates (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(60) UNIQUE
);

CREATE TABLE IF NOT EXISTS UsersComics (
  user INT NOT NULL,
  comic INT NOT NULL,
  purchased_by DECIMAL(6, 2),
  purchased_at DATE,
  reading_state INT NOT NULL,
  FOREIGN KEY (user) REFERENCES Users (id),
  FOREIGN KEY (comic) REFERENCES Comics (id),
  FOREIGN KEY (reading_state) REFERENCES ComicsReadingStates (id),
  PRIMARY KEY (user, comic)
);

CREATE TABLE IF NOT EXISTS UsersWishlists (
  user INT NOT NULL,
  comic INT NOT NULL,
  added_at DATE DEFAULT CURDATE(),
  FOREIGN KEY (user) REFERENCES Users (id),
  FOREIGN KEY (comic) REFERENCES Comics (id),
  PRIMARY KEY (user, comic)
);
