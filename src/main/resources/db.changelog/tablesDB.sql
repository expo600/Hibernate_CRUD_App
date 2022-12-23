-- liquibase formatted sql
-- changeset Expo600:1

CREATE TABLE IF NOT EXISTS developers_database.developers
(
    id           INTEGER      NOT NULL AUTO_INCREMENT,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100) NOT NULL,
    specialty_id INTEGER      NOT NULL,
    status_name  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS developers_database.specialties
(
    id             INTEGER      NOT NULL AUTO_INCREMENT,
    specialty_name VARCHAR(100) NOT NULL,
    status_name    VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS developers_database.skills
(
    id          INTEGER      NOT NULL AUTO_INCREMENT,
    skill_name  VARCHAR(100) NOT NULL,
    status_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS developers_database.developer_skills
(
    developer_id INTEGER DEFAULT NULL,
    skill_id     INTEGER DEFAULT NULL,
    CONSTRAINT `FK_developer_skills_developers` FOREIGN KEY (developer_id) REFERENCES developers (id) ON DELETE CASCADE,
    CONSTRAINT `FK_developer_skills_skills` FOREIGN KEY (skill_id) REFERENCES skills (id) ON DELETE CASCADE
);