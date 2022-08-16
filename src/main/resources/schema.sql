DROP TABLE IF EXISTS book;

CREATE TABLE book  (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(300),
                         authors VARCHAR(300),
                         rating NUMERIC (2,1)
);