SELECT * FROM users;

-- SELECT * FROM users;
-- SELECT column_1, column_2, .... column_n
-- FROM table_name

SELECT first_name, last_name, age FROM users;

SELECT first_name, last_name, age, balance, phone FROM users;

-- DISTINCT: 중복없이 조회할때 사용
SELECT DISTINCT country FROM users;
SELECT DISTINCT first_name FROM users;
-- DISTINCT에 두개의 컬럼을 작성하면 어떻게 될까요?
SELECT DISTINCT first_name, last_name FROM users;

-- SELECT ORDER BY
SELECT first_name, age, balance
FROM users
ORDER BY first_name;

SELECT last_name, balance
FROM users
ORDER BY balance DESC;

SELECT first_name, last_name, balance, age
FROM users
ORDER BY balance, age;

-- WHERE: 조근을 덧붙인 조회
SELECT *
FROM users
WHERE age < 30;

-- 40 이상 50 미만
SELECT *
FROM users
WHERE 40 <= age AND  age< 50;

SELECT *
FROM users
WHERE age < 30 OR age >= 60;

-- WHERE 문자열 LIKE
-- email이 naver.com인 계정만 조회

SELECT id, first_name, email
FROM users
WHERE email LIKE '%naver.com'; -- 문자열에서 %는 0개 이상의 문자와 일치한다고 가정한다.

SELECT id, first_name, phone
FROM users
WHERE phone LIKE '010%';

SELECT id, first_name, phone
FROM users
WHERE phone NOT LIKE '010%';