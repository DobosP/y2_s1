
use Lab5
go
CREATE TABLE worker(-- Ta
id INT PRIMARY KEY IDENTITY, -- aid
name VARCHAR(50),
income INT UNIQUE -- a2
)
CREATE TABLE boss(-- Tb
id INT PRIMARY KEY IDENTITY, -- bid
name VARCHAR(50),
income INT-- b2
)
CREATE TABLE linker(-- Tc
id INT PRIMARY KEY IDENTITY, -- cid
w_id INT FOREIGN KEY REFERENCES worker(id), -- aid
b_id INT FOREIGN KEY REFERENCES worker(id) -- bid
)

drop TABLE linker
drop TABLE worker
drop TABLE boss


DECLARE @n INTEGER
set @n = 1

WHILE @n <= 1001
BEGIN
    INSERT into worker(name,income)
    VALUES('Paul', @n)
    SET @n = @n + 1
END

DECLARE @n INTEGER
set @n = 2000

WHILE @n <= 6000
BEGIN
    INSERT into boss(name,income)
    VALUES('Paul', @n)
    SET @n = @n + 1
END

DECLARE @n INTEGER
set @n = 1

WHILE @n <= 1000
BEGIN
    INSERT into linker(w_id,b_id)
    VALUES(@n, @n)
    SET @n = @n + 1
END



-- a)
-- CLUSTER INDEX SCAN
SELECT * FROM boss


-- clustered index seek
SELECT w.name, w.id from worker as w
WHERE w.id = 1000


IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'N_idx_name')
DROP INDEX N_idx_name ON boss;
GO

CREATE NONCLUSTERED INDEX N_idx_name ON boss(name);
GO


-- nonclustered index scan;
SELECT * FROM boss ORDER by boss.name

-- nonclustered index seek;
-- key lookup.
SELECT w.name, w.id from worker as w
WHERE w.income = 2000
order by name

-- b)

IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'N_idx_income')
DROP INDEX N_idx_name ON boss;
GO

CREATE NONCLUSTERED INDEX N_idx_name ON boss(income);
GO


-- list the index plan
SET NOCOUNT ON;
GO
SET SHOWPLAN_ALL ON;
GO
--
SELECT * FROM boss WHERE income = 100;
GO
--
SET SHOWPLAN_ALL OFF
GO

-- c)


IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'I_idx_income')
DROP INDEX I_idx_income ON boss;
GO

CREATE NONCLUSTERED INDEX I_idx_income ON boss(income);
GO

IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'N_idx_name')
DROP INDEX N_idx_name ON boss;
GO

CREATE NONCLUSTERED INDEX N_idx_name ON boss(name);
GO

create VIEW teams
as
SELECT  b.name from boss as b
INNER JOIN linker as l
on l.b_id = b.id
where b.income > 100
go

drop view teams

select * from teams

EXEC sp_helpindex 'boss'