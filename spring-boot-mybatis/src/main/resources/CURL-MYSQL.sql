SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS student;




/* Create Tables */

CREATE TABLE student
(
	ID bigint NOT NULL AUTO_INCREMENT,
	STUDENT_ID char(32),
	NAME char(32),
	PRIMARY KEY (ID),
	UNIQUE (ID),
	UNIQUE (STUDENT_ID)
);



