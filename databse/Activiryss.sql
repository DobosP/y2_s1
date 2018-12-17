CREATE TABLE sportactivity(
    sa_id INT IDENTITY(1,1),
    activity_type char(100),
    grade_of_interest int,
    experience char(100),
    PRIMARY KEY(sa_id, activity_type)
)

CREATE TABLE culturalactivity(
    ca_id INT IDENTITY(1,1),
    activity_type char(100),
    grade_of_interest int,
    experience char(100),
    PRIMARY KEY(ca_id, activity_type)
)