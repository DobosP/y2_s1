USE Trainsmanagement
GO

IF OBJECT_ID('StationRoutes', 'U') IS NOT NULL
    DROP TABLE StationsRouts
IF OBJECT_ID('Stations', 'U') IS NOT NULL
    DROP TABLE Stations
IF OBJECT_ID('Routes', 'U') IS NOT NULL
    DROP TABLE Routs
IF OBJECT_ID('Trains', 'U') IS NOT NULL
    DROP TABLE Trains
IF OBJECT_ID('TrainTypes', 'U') IS NOT NULL
    DROP TABLE TrainTypes

CREATE TABLE TrainTypes
(TTID TINYINT PRIMARY KEY IDENTITY(1,1),
Description VARCHAR(100)
)

Create TABLE Trains(
    TID SMALLINT PRIMARY KEY IDENTITY(1,1),
    Tname VARCHAR(100),
    TTID TINYINT REFERENCES TrainTypes(TTID)
)

Create TABLE Routes(
    RID SMALLINT PRIMARY KEY IDENTITY(1,1),
    Rname VARCHAR(100) UNIQUE,
    TID SMALLINT REFERENCES Trains(TID)
)

Create TABLE Stations(
    SID SMALLINT PRIMARY KEY IDENTITY(1,1),
    Sname VARCHAR(100) UNIQUE
)

Create TABLE StationsRoutes
(   SID SMALLINT REFERENCES Stations(SID),
    RID SMALLINT REFERENCES Routes(RID),
    Arrival TIME,
    Departure Time,
    Primary KEY(SID, RID)
)

INSERT  TrainTypes VALUES('regio') , ('interregio')
INSERT Trains VALUEs('t1', 1), ('t2', 1), ('t3', 2)
INSERT Routes VALUES('r1', 1), ('r2', 1), ('r3', 3)
INSERT Stations VALUES('s1'), ('s2'), ('s3')
INSERT StationsRoutes VALUES(1, 1, '10:10', '10:10')

CREATE PROC uspAddStationToRoute @RName VARCHAR(100), @SName VARCHAR(100),
   @Arrival Time, @Departure TIME
AS 
    DECLARE @RID INT = (SELECT RID FROM Routes WHERE Rname = @RName),
    @SID INT = (SELECT SID FROM Stations WHERE Sname = @ @SName)

    IF @RID IS NULL OR @SID IS NULL
        RAISERROR('station / route do / does not exist', 16, 1)
    ELSE 
        IF EXISTS ( SELECT * FROM StationsRoutes WHERE sid = @SID and RID = @RID)
            RAISERROR('station already on route', 16, 1)
        ELSE
        INSERT StationsRoutes(SID, RID, Arrival, Departure)
        VALUES(@SID, @RID, @Arrival, @Departure)
GO