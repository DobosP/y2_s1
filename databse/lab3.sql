USE MyActivity
GO

SELECT * FROM friends

CREATE PROCEDURE change_data_type
AS 
BEGIN
ALTER TABLE friends 
ALTER COLUMN friends_since VARCHAR(50) NOT NULL
END

CREATE PROCEDURE restore_data_type
AS
BEGIN
ALTER TABLE friends 
ALTER COLUMN friends_since DATETIME NOT NULL
END


CREATE PROCEDURE remove_column
AS
BEGIN
ALTER TABLE person
DROP COLUMN description
END

CREATE PROCEDURE restore_column
AS
BEGIN
ALTER TABLE person
ADD description VARCHAR(500)
END

CREATE PROCEDURE add_const_constrain
AS
BEGIN
ALTER TABLE friends
ADD CONSTRAINT to_day DEFAULT GETDATE()
FOR friends_since
END

CREATE PROCEDURE restore_const_constrain
AS
BEGIN
ALTER TABLE friends
DROP CONSTRAINT to_day
END


CREATE PROCEDURE remove_primary_key
AS 
BEGIN 
ALTER TABLE friends
DROP CONSTRAINT pk_friends
END

CREATE PROCEDURE restore_primary_key
AS 
BEGIN 
ALTER TABLE friends
ADD CONSTRAINT pk_friends PRIMARY KEY(first_id, second_id)
END

CREATE PROCEDURE create_candidate_key
AS
BEGIN
ALTER TABLE subscription
ADD CONSTRAINT uniq_subscription 
UNIQUE (person_id, sub_type, start_date, end_date)
END

CREATE PROCEDURE restore_candidate_key
AS
BEGIN
ALTER TABLE subscription
DROP CONSTRAINT uniq_subscription 
END

CREATE PROCEDURE remove_foreign_key
AS
BEGIN
ALTER TABLE person
DROP CONSTRAINT fk_area
END

CREATE PROCEDURE restore_foreign_key
AS
BEGIN
ALTER TABLE person
ADD CONSTRAINT fk_area 
FOREIGN KEY (area_id) REFERENCES area_of_interes(id)
END

CREATE PROCEDURE create_new_tabel
AS
BEGIN
CREATE TABLE musical_activity_type(
    id int PRIMARY KEY IDENTITY(1,1),
    name char(100)
);
CREATE TABLE musical_activity(
    id INT,
    activity_type_id  INT,
    FOREIGN KEY(activity_type_id) REFERENCES musical_activity_type(id),
    FOREIGN KEY(id) REFERENCES person(id),
    experience char(100),
    grade_of_interes int,
    PRIMARY KEY(id, activity_type_id)
);
END

CREATE PROCEDURE restore_new_tabel
AS
BEGIN
DROP TABLE musical_activity
DROP TABLE musical_activity_type
END


USE MyActivity
go
CREATE TABLE procedure_tabel(
    ver_id int PRIMARY KEY IDENTITY(1,1),
    do_procedure VARCHAR(100),
    undo_procedure VARCHAR(100)
)


CREATE TABLE current_version(
    vers int
)

INSERT INTO procedure_tabel(do_procedure, undo_procedure)
VALUES ('change_data_type', 'restore_data_type')
INSERT INTO procedure_tabel(do_procedure, undo_procedure)
VALUES ('remove_column', 'restore_column')
INSERT INTO procedure_tabel(do_procedure, undo_procedure)
VALUES ('add_const_constrain', 'restore_const_constrain')
INSERT INTO procedure_tabel(do_procedure, undo_procedure)
VALUES ('remove_primary_key', 'restore_primary_key')
INSERT INTO procedure_tabel(do_procedure, undo_procedure)
VALUES ('create_candidate_key', 'restore_candidate_key')
INSERT INTO procedure_tabel(do_procedure, undo_procedure)
VALUES ('remove_foreign_key', 'restore_foreign_key')
INSERT INTO procedure_tabel(do_procedure, undo_procedure)
VALUES ('create_new_tabel', 'restore_new_tabel')

INSERT INTO current_version(vers)
VALUES (0)

SELECT * from procedure_tabel
SELECT * from current_version

SELECT TOP 1 * FROM current_version
USE MyActivity
GO
CREATE PROCEDURE main
@new_vers int
AS
BEGIN
    if @new_vers < 0 or @new_vers > 7
    BEGIN
    PRINT "Invalid Version"
    END
    ELSE
    BEGIN
    DECLARE @current int
    SET @current = (SELECT TOP 1 * FROM current_version)
    PRINT 'VERSION ' + CAST(@current AS VARCHAR(10))
    PRINT 'NEW VERSION ' + CAST(@new_vers AS VARCHAR(10))
    IF @new_vers < @current
    BEGIN
        DECLARE @do_procedure VARCHAR(100)
        WHILE @new_vers < @current
        BEGIN
            SET @do_procedure = (SELECT TOP 1 pt.undo_procedure FROM MyActivity.dbo.procedure_tabel AS pt 
            WHERE @current = ver_id)
            EXEC @do_procedure
            SET @current = @current - 1
            PRINT 'Migrate to ' + CAST(@current AS VARCHAR(10))
        END 
    END
    ELSE
    BEGIN
        DECLARE @undo_procedure VARCHAR(100)
        WHILE @new_vers > @current
        BEGIN
            SET @current = @current + 1
            SET @undo_procedure = (SELECT TOP 1 pt.do_procedure FROM MyActivity.dbo.procedure_tabel AS pt
                WHERE @current = ver_id)
            EXEC @undo_procedure
            PRINT 'Migrate to ' + CAST(@current AS VARCHAR(10))
        END
    END
    UPDATE current_version
    SET vers = @new_vers
    END
END

DROP PROCEDURE main

USE MyActivity
GO
DECLARE @new_vers int
SET @new_vers = 7
EXECUTE main @new_vers

SELECT * FROM current_version
SELECT * FROM procedure_tabel

UPDATE current_version
    SET vers = 2

EXECUTE change_data_type
EXECUTE restore_data_type
EXECUTE remove_column
EXECUTE restore_column
EXECUTE add_const_constrain
EXECUTE restore_const_constrain
EXECUTE remove_primary_key
EXECUTE restore_primary_key
EXECUTE create_candidate_key
EXECUTE restore_candidate_key
EXECUTE remove_foreign_key
EXECUTE restore_foreign_key
EXECUTE create_new_tabel
EXECUTE restore_new_tabe



DROP PROCEDURE remove_primary_key
drop PROCEDURE restore_primary_key