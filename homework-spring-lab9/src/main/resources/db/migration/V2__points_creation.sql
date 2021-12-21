CREATE TABLE points (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    no_points BIGINT,
    points_added datetime,

    PRIMARY KEY (id)
);