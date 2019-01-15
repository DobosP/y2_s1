CREATE DATABASE TrainManagem
GO
USE TrainManagem
GO

CREATE TABLE TrainType(
    ttid int  PRIMARY KEY IDENTITY(1,1),
    descript varchar(50)  
)

CREATE TABLE Train(
    tid int   PRIMARY KEY IDENTITY(1,1),
    name varchar(50),
    ttid int REFERENCES TrainType(ttid)

)

CREATE TABLE Sation(
    sid int IDENTITY(1,1)  PRIMARY KEY,
    name varchar(50) UNIQUE
)

CREATE TABLE Routes(
    rid int IDENTITY(1,1) PRIMARY KEY,
    tid int REFERENCES Train(tid),
    name varchar(50) UNIQUE,
)

CREATE TABLE RoutesStation(
    rid int  REFERENCES Routes(rid),
    sid int REFERENCES Sation(sid),
    arival varchar(50),
    departure varchar(50),
    PRIMARY KEY(rid, sid) 
)

INSERT INTO TrainType(descript) VALUES('d1')
INSERT INTO TrainType(descript) VALUES('d2')
INSERT INTO Train(name, ttid) VALUES('t1',1)
INSERT INTO Train(name, ttid) VALUES('t1',2)
INSERT INTO Sation(name) VALUES('s1')
INSERT INTO Sation(name) VALUES('s2')
INSERT INTO Routes(tid,name) VALUES(1,'r1')
INSERT INTO Routes(tid,name) VALUES(2,'r2')
INSERT INTO RoutesStation(rid,sid,departure,arival)
VALUES(1,1,'10:20','10:30')



CREATE OR ALTER PROCEDURE addStationToRoute @rname VARCHAR(50), @sname VARCHAR(50), @arival VARCHAR(50), @departure VARCHAR(50)
as
BEGIN
    DECLARE @sid  int = (
        SELECT s.sid from Sation s
        WHERE s.name = @sname
    )
        DECLARE @rid  int = (
        SELECT r.rid from Routes r
        WHERE r.name = @rname
    )

    if @sid is null or @rid is null
        RAISERROR('Station/Route dose not exists!',10,1)
    ELSE
        if EXISTS(SELECT * from RoutesStation  rs 
                    WHERE rs.rid = @rid and rs.sid = @sid)
            RAISERROR('Station to route already exists!',10,1)
        ELSE
            INSERT INTO RoutesStation VALUES(@rid,@sid,@arival,@departure)
END


SELECT * from Routes
SELECT * from Sation

exec addStationToRoute 'r2', 's2', '10:30', '10:40'

select * from RoutesStation

CREATE OR ALTER VIEW allstations
as
    select r.name from Routes r, (SELECT count(*) as nr, rs.rid from 
                                RoutesStation  as rs 
                                GROUP by rs.rid)  as cr
    WHERE cr.rid = r.rid and cr.nr = (select count(*) from Sation)



CREATE  FUNCTION filterstations(@R int)
RETURNS TABLE 
 AS
    RETURN
        SELECT s.name FROM Sation as s, (select count(*) as nr, rs.sid 
                                        FROM RoutesStation as rs
                                        GROUP BY rs.sid)  as cs
        WHERE s.sid = cs.sid and cs.nr >= @R 
 GO


CREATE or ALTER FUNCTION filterstations2(@R int)
RETURNS TABLE
AS
    RETURN
    SELECT s.name FROM Sation as s INNER JOIN 
    (select count(*) as nr, rs.sid 
     FROM RoutesStation as rs
     GROUP BY rs.sid) AS cs
     on s.sid = cs.nr
     WHERE cs.nr >= @R
GO



SELECT * from filterstations(2)





