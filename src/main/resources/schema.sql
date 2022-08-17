DROP TABLE IF EXISTS book;

CREATE TABLE book  (
                         id SERIAL NOT NULL PRIMARY KEY,
                         title VARCHAR(1000) NOT NULL,
                         authors VARCHAR(1000) NOT NULL,
                         rating NUMERIC (2,1)
);