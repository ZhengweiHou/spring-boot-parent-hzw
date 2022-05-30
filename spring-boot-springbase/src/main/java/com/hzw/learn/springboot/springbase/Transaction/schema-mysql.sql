CREATE TABLE H (
    id INT auto_increment,
    name VARCHAR(20),
    age INT,
    notnull VARCHAR(10) NOT NULL,
    DATE DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Z (
    id INT auto_increment,
    name VARCHAR(20),
    age INT,
    notnull VARCHAR(9) NOT NULL,
    DATE DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE test (
    id INT ,
    name VARCHAR(10)
) ENGINE=InnoDB;