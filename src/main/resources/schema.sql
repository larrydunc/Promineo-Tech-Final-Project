DROP TABLE IF EXISTS story_character;
DROP TABLE IF EXISTS character_main;
DROP TABLE IF EXISTS story;
DROP TABLE IF EXISTS author CASCADE;

CREATE TABLE author (
	author_id int NOT NULL AUTO_INCREMENT,
	author_first_name varchar(128) NOT NULL,
	author_last_name varchar(128) NOT NULL,
	date_of_birth varchar(50),
	date_of_death varchar(50),
	biography text,
	PRIMARY KEY (author_id)
);
CREATE TABLE story (
	story_id int NOT NULL AUTO_INCREMENT,
	author_id int NOT NULL,
	story_title varchar(256) NOT NULL,
	date_published varchar(50),
	publication varchar(256),
	summary text,
	PRIMARY KEY (story_id),
	FOREIGN KEY (author_id) REFERENCES author (author_id) ON DELETE CASCADE
);
CREATE TABLE character_main (
	character_id int NOT NULL AUTO_INCREMENT,
	character_name varchar(256) NOT NULL,
	character_type varchar(128),
	character_description text,
	PRIMARY KEY (character_id)
);
CREATE TABLE story_character (
	story_id int NOT NULL,
	character_id int NOT NULL,
	FOREIGN KEY (story_id) REFERENCES story (story_id) ON DELETE CASCADE,
	FOREIGN KEY (character_id) REFERENCES character_main (character_id) ON DELETE CASCADE,
	UNIQUE KEY (story_id, character_id)
);