USE MyActivity
GO

CREATE TABLE area_of_interes(
    id INT PRIMARY KEY IDENTITY(1,1),
    name char(100)
)

CREATE TABLE person(
    id INT CONSTRAINT pk_person
    PRIMARY KEY IDENTITY(1,1),
    name CHAR(50),
    age INT,
    area_id int,
    description VARCHAR(500),
    CONSTRAINT fk_area FOREIGN KEY(area_id)
    REFERENCES area_of_interes(id)

)

CREATE TABLE sport_activity_type(
    id int PRIMARY KEY IDENTITY(1,1),
    name char(100)
)

CREATE TABLE cultural_activity_type(
    id int PRIMARY KEY IDENTITY(1,1),
    name char(100)
)

CREATE TABLE leisure_activity_type(
    id int PRIMARY KEY IDENTITY(1,1),
    name char(100)
)

CREATE TABLE sport_activity(
    id INT,
    activity_type_id  INT,
    FOREIGN KEY(activity_type_id) REFERENCES sport_activity_type(id),
    FOREIGN KEY(id) REFERENCES person(id),
    experience char(100),
    grade_of_interes int,
    PRIMARY KEY(id, activity_type_id)
)

CREATE TABLE cultural_activity(
    id INT,
    activity_type_id  INT,
    FOREIGN KEY(activity_type_id) REFERENCES cultural_activity_type(id),
    FOREIGN KEY(id) REFERENCES person(id),
    experience char(100),
    grade_of_interes int,
    PRIMARY KEY(id, activity_type_id)
)

CREATE TABLE leisure_activity(
    id INT,
    activity_type_id  INT,
    FOREIGN KEY(activity_type_id) REFERENCES leisure_activity_type(id),
    FOREIGN KEY(id) REFERENCES person(id),
    experience char(100),
    grade_of_interes int,
    PRIMARY KEY(id, activity_type_id)
)

CREATE TABLE groups(
    id int PRIMARY KEY IDENTITY(1,1),
    group_type char(100),
    members int,
    group_description char(500)
)

CREATE TABLE group_to_person(
    group_id INT,
    person_id INT,
    FOREIGN KEY(group_id) REFERENCES groups(id),
    FOREIGN KEY(person_id) REFERENCES person(id),
    PRIMARY KEY(group_id, person_id)
)


CREATE TABLE subscription(
    id int PRIMARY KEY IDENTITY(1,1),
    person_id int,
    FOREIGN KEY(person_id) REFERENCES person(id),
    sub_type char(50),
    sub_description char(200),
    start_date datetime,
    end_date datetime
)

CREATE TABLE friends(
    first_id INT,
    second_id INT,
    FOREIGN KEY(first_id) REFERENCES person(id),
    FOREIGN KEY(second_id) REFERENCES person(id),
    friends_since datetime,
    relation_type char(50),
    CONSTRAINT pk_friends PRIMARY KEY(first_id, second_id)
)
