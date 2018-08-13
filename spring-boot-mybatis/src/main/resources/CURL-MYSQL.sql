/* Drop Tables */
DROP TABLE IF EXISTS student;

/* Create Tables */
CREATE TABLE student
(
	id bigint NOT NULL AUTO_INCREMENT,
	studentId char(32),
	name char(32),
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (studentId)
);



