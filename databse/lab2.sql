USE MyActivity
GO

-- inserts 

INSERT INTO area_of_interes(name) VALUES('Cluj')
INSERT INTO area_of_interes(name) VALUES('Suceava')
INSERT INTO area_of_interes(name) VALUES('Brasov')


INSERT INTO person(name, age, area_id, description)
VALUES ('Dobos Paul', 20, 1, 'Just a student')
INSERT INTO person(name, age, area_id, description)
VALUES ('Gingu Ionut', 20, 1, 'Just another student')
INSERT INTO person(name, age, area_id, description)
VALUES ('Florin Lup', 22, 2, 'Last year student')
INSERT INTO person(name, age, area_id, description)
VALUES ('Mara Andreea', 20, 1, 'Just started as a student')

INSERT INTO sport_activity_type(name)
VALUES ('runing')
INSERT INTO sport_activity_type(name)
VALUES ('football')
INSERT INTO sport_activity_type(name)
VALUES ('tenis')
INSERT INTO sport_activity_type(name)
VALUES ('swimming')


INSERT INTO sport_activity(id, activity_type_id, experience, grade_of_interes)
VALUES(1,1, 'experienced', 10)
INSERT INTO sport_activity(id, activity_type_id, experience, grade_of_interes)
VALUES(1, 4, 'beginer', 6)
INSERT INTO sport_activity(id, activity_type_id, experience, grade_of_interes)
VALUES(2, 3, 'experienced', 9)
INSERT INTO sport_activity(id, activity_type_id, experience, grade_of_interes)
VALUES(2, 4, 'medium', 7)
INSERT INTO sport_activity(id, activity_type_id, experience, grade_of_interes)
VALUES(3, 2, 'medium', 7)
INSERT INTO sport_activity(id, activity_type_id, experience, grade_of_interes)
VALUES(4, 1, 'beginer', 6)

INSERT INTO friends(first_id, second_id, frinds_since, relation_type)
VALUES(1, 2, '10-10-2017', 'close')
INSERT INTO friends(first_id, second_id, frinds_since, relation_type)
VALUES(1, 4, '1-9-2017', 'normal')

INSERT INTO groups(group_type, members, group_description)
VALUES('runing', 2, 'For the people that like running')

INSERT INTO group_to_person(group_id, person_id)
VALUES(1,1)
INSERT INTO group_to_person(group_id, person_id)
VALUES(1,4)


-- insert with violation

INSERT INTO person(name, age, area_id, description)
VALUES ('Farauan Ionut', 20, 5, 'Just a student or not?')


-- UPDATES
 
UPDATE sport_activity
SET grade_of_interes=10
WHERE experience ='experienced' AND grade_of_interes >= 9
SELECT * FROM sport_activity

UPDATE friends
SET relation_type='close'
WHERE relation_type = 'normal'
SELECT * FROM friends

UPDATE person
SET area_id=1
WHERE area_id IN (2, 3)
SELECT * FROM person

--DELITION
DELETE FROM sport_activity
WHERE grade_of_interes BETWEEN 5 AND 7
SELECT * FROM sport_activity

DELETE FROM person
WHERE age IS NOT NULL AND CAST(age as varchar) LIKE '_2'
SELECT * FROM person


-- A
SELECT DISTINCT p1.name
FROM person AS p1
WHERE p1.name LIKE 'D_%'
UNION ALL
SELECT P2.name
FROM person as p2
where p2.area_id = 2


SELECT sa.id, sa.activity_type_id
FROM sport_activity AS sa
WHERE sa.grade_of_interes BETWEEN 8 AND 9 OR sa.experience = 'experienced'

--B

SELECT p1.name
FROM person as p1
WHERE p1.area_id=1
INTERSECT
SELECT p2.name
FROM person AS p2
WHERE p2.name LIKE 'G_%'


SELECT sa.id, sa.activity_type_id
FROM sport_activity AS sa
WHERE sa.grade_of_interes BETWEEN 8 AND 9 AND sa.experience IN ('experienced', 'medium')


--C

SELECT DISTINCT p1.name
FROM person AS p1
WHERE p1.age >= 2
EXCEPT
SELECT p2.name
FROM person AS p2
WHERE p2.area_id != 1


SELECT sa.id, sa.activity_type_id, sa.experience
FROM sport_activity AS sa
WHERE sa.grade_of_interes BETWEEN 5 AND 9 AND sa.experience  NOT IN ('experienced')


-- D

SELECT TOP 2 PERCENT * FROM person AS p
FULL JOIN area_of_interes as aoi
ON p.area_id = aoi.id

SELECT * FROM person as p
LEFT JOIN sport_activity as sa 
ON p.id = sa.id
LEFT JOIN sport_activity_type as sat
ON sa.activity_type_id = sat.id

SELECT TOP 1 PERCENT * FROM person AS p
INNER JOIN area_of_interes as aoi
ON p.area_id = aoi.id
INNER JOIN group_to_person as gtp
on p.id = gtp.person_id
INNER JOIN groups as g
ON gtp.group_id = g.id



SELECT * FROM friends as f
RIGHT jOIN person as p1
On f.first_id = p1.id
RIGHT JOIN person as p2
on f.second_id = p2.id


-- E

SELECT * FROM person as p
WHERE p.age <= 22 and p.id in(
    SELECT p.id FROM person as p
    WHERE p.area_id = 1
)


SELECT * FROM person as p
WHERE p.area_id = 1 and p.id in(
    SELECT sa.id FROM sport_activity as sa
    WHERE sa.grade_of_interes >= 9
)
ORDER BY id DESC

-- F

SELECT * FROM person as p
WHERE p.area_id = 1 and EXISTS(
    SELECT gtp.person_id from group_to_person as gtp
    WHERE gtp.person_id = p.id
)

SELECT * FROM sport_activity as sa
WHERE sa.grade_of_interes BETWEEN 1 AND 10 AND EXISTS(
    SELECT * from person as p 
    where p.area_id = 1 and sa.id = p.id
)
ORDER by sa.grade_of_interes DESC

-- G

SELECT DISTINCT A.name, A.id FROM(
    SELECT p.name, p.id from person as p
    INNER JOIN sport_activity as sa
    ON sa.id = p.id and sa.grade_of_interes >= 7
) A

SELECT DISTINCT A.name, A.id FROM(
    SELECT p.name, p.id from person as p
    INNER JOIN group_to_person as gtp
    ON p.id = gtp.person_id
) A

-- H

SELECT sa.experience, AVG(sa.grade_of_interes) AS greade_of_interes
FROM person as p
INNER JOIN sport_activity as sa
on p.id = sa.id
GROUP BY  sa.experience

SELECT p.name, MAX(sa.grade_of_interes) AS greade_of_interes
FROM person as p
INNER JOIN sport_activity as sa
on sa.id = p.id
GROUP BY p.name
HAVING MAX(sa.grade_of_interes) > 7


-- minumum greade of interes from cluj

SELECT p.name, MIN(sa.grade_of_interes) AS greade_of_interes
FROM person as p
INNER JOIN sport_activity as sa
on sa.id = P.id
GROUP BY p.name, p.area_id
HAVING p.area_id in (
    SELECT a.id from area_of_interes as a 
    where a.name = 'Cluj'
)

-- sum of the graades of interes of the people that are in a group

SELECT p.id, SUM(sa.grade_of_interes) AS greade_of_interes
FROM person as p
INNER JOIN sport_activity as sa
on sa.id = p.id
GROUP BY p.id
HAVING p.id in (
    SELECT gtp.person_id 
    FROM group_to_person as gtp 
    where gtp.person_id = p.id
) 

-- I

-- person with maximum grade of interes

SELECT p.name, p.id, sa.activity_type_id, sa.grade_of_interes FROM person as p
INNER JOIN sport_activity as sa
ON sa.id = p.id
where grade_of_interes >= ALL(
    SELECT max(sa2.grade_of_interes)
    FROM sport_activity as sa2
    )

-- PERSON WITH GRADE OF INTERES BIGGER THEN AVRAGE

SELECT p.name, p.id, sa.activity_type_id, sa.grade_of_interes FROM person as p
INNER JOIN sport_activity as sa
ON sa.id = p.id
where grade_of_interes > ANY(
    SELECT AVG(sa2.grade_of_interes)
    FROM sport_activity as sa2
    )

-- people not from Cluj

SELECT p.name, p.id
FROM person as p
WHERE p.area_id = ANY(
    SELECT A.id from area_of_interes as a
    where a.name not in ('Cluj')
)



-- people from runing group

SELECT p.name, p.id
FROM person as p
WHERE p.id = ANY(
    SELECT gtp.person_id from group_to_person as gtp
    where gtp.group_id IN (1)
)


-- print data

SELECT * FROM area_of_interes
SELECT * FROM person
SELECT * FROM sport_activity_type
SELECT * FROM sport_activity
SELECT * FROM friends
SELECT * FROM groups
SELECT * FROM group_to_person








-- clean database

DELETE FROM friends
where 1 = 1
DELETE FROM sport_activity
where 1 = 1
DELETE FROM sport_activity_type
where 1 = 1
DELETE FROM group_to_person
where 1 = 1
DELETE FROM groups
where 1 = 1
DELETE FROM person
where 1 = 1
DELETE FROM area_of_interes
where 1 = 1




