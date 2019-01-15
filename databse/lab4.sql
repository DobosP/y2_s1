USE MyActivity
GO

INSERT INTO Tables(name) VALUES('area_of_interes')
INSERT INTO Tables(name) VALUES('person')
INSERT INTO Tables(name) VALUES('sport_activity_type')
INSERT INTO Tables(name) VALUES('sport_activity')

INSERT INTO Views(name) VALUES('view_1')
INSERT INTO Views(name) VALUES('view_2')
INSERT INTO Views(name) VALUES('view_3')

INSERT INTO Tests(name) VALUES('test_10')
INSERT INTO Tests(name) VALUES('test_100')
INSERT INTO Tests(name) VALUES('test_1000')

INSERT INTO TestViews(TestID, ViewID) VALUES(1, 1)
INSERT INTO TestViews(TestID, ViewID) VALUES(1, 2)
INSERT INTO TestViews(TestID, ViewID) VALUES(1, 3)
INSERT INTO TestViews(TestID, ViewID) VALUES(2, 1)
INSERT INTO TestViews(TestID, ViewID) VALUES(2, 2)
INSERT INTO TestViews(TestID, ViewID) VALUES(2, 3)
INSERT INTO TestViews(TestID, ViewID) VALUES(3, 1)
INSERT INTO TestViews(TestID, ViewID) VALUES(3, 2)
INSERT INTO TestViews(TestID, ViewID) VALUES(3, 3)


INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(1, 1, 10, 1)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(1, 2, 10, 2)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(1, 3, 10, 2)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(1, 4, 10, 3)

INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(2, 1, 100, 1)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(2, 2, 100, 2)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(2, 3, 100, 2)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(2, 4, 100, 3)

INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(3, 1, 1000, 1)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(3, 2, 1000, 2)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(3, 3, 1000, 2)
INSERT INTO TestTables(TestID, TableID, NoOfRows, Position)
VALUES(3, 4, 1000, 3)


CREATE PROCEDURE clean_table
@table CHAR(50)
AS
BEGIN

    DECLARE @delete_statement NVARCHAR(4000)
    SET @delete_statement = 'DELETE FROM' + QUOTENAME(@table)
    EXECUTE sp_executesql @delete_statement
    IF EXISTS (SELECT * from syscolumns where id = Object_ID(@table) and colstat & 1 = 1)
    BEGIN
        DBCC CHECKIDENT (@table, RESEED, 0);
    END
END

CREATE PROCEDURE insert_area_of_interes
@no_of_rows int
AS
BEGIN
DECLARE @n int
DECLARE @name char(100)

SET @n = 1

WHILE @n <= @no_of_rows
BEGIN
    SET @name = 'area_' + CONVERT (VARCHAR(5), @n)
    INSERT INTO area_of_interes(name) VALUES (@name)
    SET @n=@n + 1
END
END

CREATE PROCEDURE insert_person
@no_of_rows int
AS
BEGIN
DECLARE @n int
DECLARE @name char(50)
DECLARE @age int
DECLARE @area_id int
SET @n = 1

WHILE @n <= @no_of_rows
BEGIN
    SET @name = 'user_' + CONVERT (VARCHAR(5), @n)
    SET @age = FLOOR(RAND()*(100-6))+5
    SET @area_id = FLOOR(RAND()*(@no_of_rows-2))+1 
    INSERT INTO person(name,age,area_id,description) 
    VALUES (@name, @age, @area_id, 'to be or not to be')
    SET @n=@n + 1
END
END


CREATE PROCEDURE insert_sport_activity_type
@no_of_rows int
AS
BEGIN
DECLARE @n int
DECLARE @name char(100)

SET @n = 1

WHILE @n <= @no_of_rows
BEGIN
    SET @name = 'activity_' + CONVERT (VARCHAR(5), @n)
    INSERT INTO sport_activity_type(name) VALUES (@name)
    SET @n=@n + 1
END
END


CREATE PROCEDURE insert_sport_activity
@no_of_rows int
AS
BEGIN
DECLARE @n int
DECLARE @id int
DECLARE @at_id int
DECLARE @interes int
SET @n = 1

WHILE @n <= @no_of_rows
BEGIN

    SET @id = FLOOR(RAND()*(@no_of_rows-3)) + 2
    SET @at_id = FLOOR(RAND()*(@no_of_rows-3)) + 2
    SET @interes = FLOOR(RAND()*(10-2))+1
    INSERT INTO sport_activity(id,activity_type_id,experience,grade_of_interes) 
    VALUES (@id, @at_id, 'intermediate', @interes)
    SET @n=@n + 1
END
END


CREATE VIEW view_1
AS
    SELECT p.name, p.age from person as p
    WHERE p.age < 20

CREATE VIEW view_2
AS
    SELECT * FROM sport_activity as sa
    WHERE sa.grade_of_interes BETWEEN 1 AND 10 AND EXISTS(
        SELECT * from person as p 
        where sa.id = p.id and p.age < 20
    )

CREATE VIEW view_3
AS
    SELECT p.name, MAX(sa.grade_of_interes) AS greade_of_interes
    FROM person as p
    INNER JOIN sport_activity as sa
    on sa.id = p.id
    GROUP BY p.name



CREATE VIEW table_test_data
AS
    SELECT t.name as test_name,tt.NoOfRows, tt.Position, ta.Name as table_name, ta.TableID as table_id
    FROM Tests as t
    INNER JOIN TestTables as tt 
    on t.TestId = tt.TestID
    INNER JOIN Tables AS ta
    on ta.TableID = tt.TableID

CREATE VIEW view_test_data
AS
    SELECT t.name as test_name, v.name as view_name , v.ViewID as view_id
    FROM Tests as t
    INNER JOIN TestViews as tv
    on tv.TestID = t.TestID
    INNER JOIN Views AS v
    on v.ViewID = tv.ViewID


SELECT * from table_test_data

CREATE PROCEDURE test_all
AS
BEGIN
    DECLARE @test_name VARCHAR(30)
	DECLARE @test_id INT
	DECLARE tests_cursor CURSOR FOR
	SELECT T.Name, T.TestID
	FROM Tests  T

    	OPEN tests_cursor
	FETCH NEXT FROM tests_cursor INTO @test_name, @test_id

	IF @@FETCH_STATUS <> 0
		PRINT 'NO TEST'
	WHILE @@FETCH_STATUS = 0
	BEGIN
        DECLARE @select_query NVARCHAR(150)
        DECLARE @fun_name VARCHAR(50)
        DECLARE @no_of_rows INT
        DECLARE @table_name char(50)
        DECLARE @view_name char(50)
        DECLARE @table_id INT
        DECLARE @view_id INT
        DECLARE @start_time DATETIME
        DECLARE @end_time DATETIME
        DECLARE @test_start_time DATETIME
        DECLARE table_cursor CURSOR FOR
            SELECT dt.NoOfRows, dt.table_name, dt.table_id
                FROM table_test_data as dt
                WHERE dt.test_name = @test_name
                ORDER by dt.Position 
        DECLARE view_cursor CURSOR FOR
            SELECT dv.view_name, dv.view_id
                FROM view_test_data as dv
                WHERE dv.test_name = @test_name
        DECLARE clean_cursor CURSOR FOR
            SELECT dt.table_name
                FROM table_test_data as dt
                WHERE dt.test_name = @test_name
                ORDER by dt.Position DESC
        SET @start_time = SYSDATETIME()
        INSERT INTO TestRuns(Description) VALUES ('data for test ' + @test_name)
        DECLARE @testrun_id int
        SET @testrun_id = @@IDENTITY
            OPEN table_cursor
        FETCH NEXT FROM table_cursor INTO @no_of_rows, @table_name, @table_id

        IF @@FETCH_STATUS <> 0
            PRINT 'NO TEST'
        WHILE @@FETCH_STATUS = 0
        BEGIN  
            set @test_start_time = SYSDATETIME()
            SET @fun_name = 'insert_' + @table_name
            EXECUTE @fun_name @no_of_rows
            INSERT INTO TestRunTables(TestRunID,TableID,StartAt,EndAt)
            VALUES(@testrun_id, @table_id, @test_start_time, SYSDATETIME())
        FETCH NEXT FROM table_cursor INTO @no_of_rows, @table_name, @table_id
    	END
        CLOSE table_cursor
        DEALLOCATE table_cursor

            OPEN view_cursor
        FETCH NEXT FROM view_cursor INTO @view_name, @view_id

        IF @@FETCH_STATUS <> 0
            PRINT 'NO TEST'
        WHILE @@FETCH_STATUS = 0
        BEGIN  
            SET @test_start_time = SYSDATETIME()
            set @select_query =  'SELECT * FROM ' +  QUOTENAME(@view_name)
            EXECUTE sp_executesql @select_query
            INSERT INTO TestRunViews(TestRunID,ViewID,StartAt,EndAt)
            VALUES(@testrun_id, @view_id, @test_start_time, SYSDATETIME())
            FETCH NEXT FROM view_cursor INTO @view_name, @view_id
    	END
        CLOSE view_cursor 
        DEALLOCATE view_cursor

     OPEN clean_cursor
        FETCH NEXT FROM clean_cursor INTO @table_name

        IF @@FETCH_STATUS <> 0
            PRINT 'NO TEST'
        WHILE @@FETCH_STATUS = 0
        BEGIN  
            EXECUTE clean_table @table_name
        FETCH NEXT FROM clean_cursor INTO @table_name
    	END
        CLOSE clean_cursor 
        DEALLOCATE clean_cursor 
    
    SET @end_time = SYSDATETIME()
    
    UPDATE TestRuns
    SET StartAt = @start_time , EndAt = @end_time
    WHERE TestRunID = @testrun_id

    FETCH NEXT FROM tests_cursor INTO @test_name, @test_id
	END

	CLOSE tests_cursor  
    DEALLOCATE tests_cursor 

END

drop PROCEDURE test_all


use MyActivity
go
EXECUTE test_all

SELECT * from view_test_data

EXECUTE clean_table 'sport_activity'
EXECUTE clean_table 'sport_activity_type'
EXECUTE clean_table 'person'
EXECUTE clean_table 'area_of_interes'


select * from sport_activity
select * from sport_activity_type
select * from person
select * from area_of_interes


select * from TestRuns
SELECT * from TestRunViews
SELECT * from TestRunTables

EXECUTE clean_table 'TestRunViews'
EXECUTE clean_table 'TestRunTables'
EXECUTE clean_table 'TestRuns'
