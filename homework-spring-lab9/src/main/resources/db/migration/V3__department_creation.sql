CREATE TABLE departments (
    departmentId BIGINT NOT NULL AUTO_INCREMENT,
    departmentName VARCHAR(50) NOT NULL,
    description VARCHAR(200),

    PRIMARY KEY (departmentId)
);