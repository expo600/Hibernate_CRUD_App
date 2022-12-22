-- liquibase formatted sql

-- changeset expo600:1
CREATE TABLE IF NOT EXISTS developers_database.developers
(
    id           LONG AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100) NOT NULL,
    specialty_id LONG         NOT NULL,
    status_name  VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS developers_database.specialties
(
    id             LONG AUTO_INCREMENT PRIMARY KEY,
    specialty_name VARCHAR(100) NOT NULL,
    status_name    VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS developers_database.skills
(
    id          LONG AUTO_INCREMENT PRIMARY KEY,
    skill_name  VARCHAR(100) NOT NULL,
    status_name VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS developers_database.developer_skills
(
    developer_id LONG DEFAULT NULL,
    skill_id     LONG DEFAULT NULL,
    CONSTRAINT `FK_developer_skills_developers` FOREIGN KEY (`developer_id`) REFERENCES `developers` (`id`) ON DELETE CASCADE,
    CONSTRAINT `FK_developer_skills_skills` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`) ON DELETE CASCADE
);