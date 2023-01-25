CREATE TABLE developers
(
    id           SERIAL PRIMARY KEY,
    first_name   CHARACTER VARYING(100) NOT NULL,
    last_name    CHARACTER VARYING(100) NOT NULL,
    specialty_id INTEGER      NOT NULL,
    status_name  CHARACTER VARYING(100) NOT NULL
);


CREATE TABLE specialties
(
    id             SERIAL PRIMARY KEY,
    specialty_name CHARACTER VARYING(100) NOT NULL,
    status_name    CHARACTER VARYING(100) NOT NULL
);


CREATE TABLE skills
(
    id          SERIAL PRIMARY KEY,
    skill_name  CHARACTER VARYING(100) NOT NULL,
    status_name CHARACTER VARYING(100) NOT NULL
);

;
CREATE TABLE developer_skills
(
    developer_id INTEGER NOT NULL,
    skill_id     INTEGER NOT NULL,
    CONSTRAINT FK_developer_skills_developers FOREIGN KEY (developer_id) REFERENCES developers (id) ON DELETE CASCADE,
    CONSTRAINT FK_developer_skills_skills FOREIGN KEY (skill_id) REFERENCES skills (id) ON DELETE CASCADE
);