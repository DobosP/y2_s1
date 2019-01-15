Create DATABASE Practicexam
USE Practicexam
go

CREATE TABLE PresentType(
    ptid int PRIMARY KEY IDENTITY(1,1),
    type VARCHAR(50)
)

CREATE TABLE Chocolates(
    cid int PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(50),
    price int
)

CREATE TABLE Person(
    pid int PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(50),
    date_of_b DATETIME
)



CREATE TABLE Present(
    prid int PRIMARY KEY IDENTITY(1,1),
    color VARCHAR(50),
    heavy BIT,
    ptid int REFERENCES PresentType(ptid)
)

CREATE TABLE PersonPresents(
    pid int REFERENCES Person(pid),
    prid int REFERENCES Present(prid),
    sharingdate DATETIME,
    PRIMARY KEY(pid,prid)
)

CREATE TABLE PresentChocolates(
    prid int REFERENCES Present(prid),
    cid int REFERENCES Chocolates(cid),
    quantity int,
    expirationdate DATETIME,
    PRIMARY KEY(prid, cid)
)

INSERT INTO PresentType(type) VALUES('pt1')
INSERT INTO PresentType(type) VALUES('pt2')
INSERT INTO Person(name,date_of_b) VALUES('Paul','2000-12-12')
INSERT INTO Person(name,date_of_b) VALUES('Andrei','2000-12-12')
INSERT INTO Chocolates(name,price) VALUES('c1', 10)
INSERT INTO Chocolates(name,price) VALUES('c2', 20)
INSERT INTO Present(color,heavy,ptid) VALUES('read', 1, 1)
INSERT INTO Present(color,heavy,ptid) VALUES('blue', 0, 2)
INSERT INTO PersonPresents(pid,prid,sharingdate)
VALUES(1,1,'2018-12-12')

SELECT * from Present
SELECT * from Person
SELECT * from Chocolates
SELECT * FROM PresentChocolates pr
WHere pr.cid = 1  and pr.prid = 1

USE Practicexam
go
CREATE OR ALTER PROCEDURE addchocolateoresebt(@cid int, @prid int, @quantity int, @expiration DATETIME)
AS
BEGIN
    IF @cid not in (select c.cid from Chocolates c) or @prid not in (select pr.prid from Present pr)
        RAISERROR('Chocolate or present dose not exists!',16,1)
    ELSE
        IF EXISTS(SELECT * from PresentChocolates pre 
                  where  pre.cid = @cid and pre.prid = @prid )
        RAISERROR('The chocolate already exists in present!',16,1)
        ELSE
            INSERT INTO PresentChocolates(cid,prid,quantity,expirationdate)
            VALUES(@cid, @prid, @quantity, @expiration)

END

CREATE OR ALTER VIEW nrpresentperson
AS
    SELECT p.name FROM Person AS p INNER JOIN
    (select count(*) as nr, pp.pid from PersonPresents pp
    GROUP by pp.pid) as cp
    on cp.pid = p.pid
    WHERE cp.pid >= 1
GO

CREATE OR ALTER FUNCTION maxnrchoco()
RETURNS TABLE
AS
    RETURN
    SELECT cc.prid FROM Present as pr 
    INNER JOIN(select count(*) as nr, pc.prid 
        from PresentChocolates pc
        GROUP BY PC.prid) as cc
        on pr.prid = cc.nr
        WHERE cc.nr = 
    (SELECT  max(mc.nr) as mx FROM Present as pr
    INNER JOIN (
        select count(*) as nr, pc.prid 
        from PresentChocolates pc
        GROUP BY PC.prid
    ) AS mc ON pr.prid = mc.prid)
GO





EXEC addchocolateoresebt 1,1,2,'2020-12-12'
EXEC addchocolateoresebt 1,2,2,'2020-12-12'
select * from nrpresentperson
SELECT * from maxnrchoco()

