
-- CREATE TABLE table_name(
--   colunm_name_1 integer,
--   colunm_name_2 integer,
--   colunm_name_3 integer
-- );

-- id, name, email
CREATE TABLE students(
    id integer,
    name VARCHAR(32),
    email text
);

-- id, username, first_name, last_name, email
CREATE TABLE students2(
    id INTEGER,
    username TEXT,
    first_name TEXT,
    last_name TEXT,
    email TEXT
);

-- Constraints
-- NOT NULL Constraint
CREATE TABLE students_not_null (
    id INTEGER,
    username TEXT,
    first_name TEXT,
    last_name TEXT,
    email TEXT NOT NULL -- email은 null이 될 수 없다.
);

-- UNIQUE Constraint
CREATE TABLE students_unique (
    id INTEGER,
    username TEXT UNIQUE, -- username은 레코드별로 고유하다.
    first_name TEXT,
    last_name TEXT,
    email TEXT
);

-- PRIMARY KEY + AUTOINCREMENT
CREATE TABLE students_pka (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT,
    first_name TEXT,
    last_name TEXT,
    email TEXT
);

-- final
CREATE TABLE students_final (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE,
    first_name TEXT,
    last_name TEXT,
    email TEXT NOT NULL
);

-- ALTER TABLE
-- RENAME TO
ALTER TABLE students2 RENAME TO students_temp;
ALTER TABLE students_temp RENAME TO students2;

-- RENAME COLUMN
ALTER TABLE students2 RENAME COLUMN first_name TO given_name;
ALTER TABLE students2 RENAME COLUMN given_name TO first_name;

ALTER TABLE students2 RENAME COLUMN last_name TO sur_name;
ALTER TABLE students2 RENAME COLUMN sur_name TO last_name;

-- ADD COLUMN
ALTER TABLE students2 ADD COLUMN phone VARCAR(64);
ALTER TABLE students2 ADD COLUMN phone_ VARCAR(64) NOT NULL; -- 기존에 있던 레코드들의 값은 어떻게 할꺼냐?
ALTER TABLE students2 ADD COLUMN phone_ VARCAR(64) NOT NULL DEFAULT '';

-- DROP TABLE
DROP TABLE students2;
DROP TABLE IF EXISTS students2;
