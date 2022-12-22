-- liquibase formatted sql
-- changeset Expo600:2

/*Populate specialties*/
INSERT INTO specialties (specialty_name,status_name) values ('Java','Active');
INSERT INTO specialties (specialty_name,status_name) values ('C#','Active');
INSERT INTO specialties (specialty_name,status_name) values ('C++','Active');
INSERT INTO specialties (specialty_name,status_name) values ('JavaScript','Active');
INSERT INTO specialties (specialty_name,status_name) values ('PHP','Active');
INSERT INTO specialties (specialty_name,status_name) values ('Kotlin','Active');
INSERT INTO specialties (specialty_name,status_name) values ('Python','Active');
INSERT INTO specialties (specialty_name,status_name) values ('Go','Active');

/*Populate skills*/
INSERT INTO skills (skill_name,status_name) values ('Database and SQL','Active');
INSERT INTO skills (skill_name,status_name) values ('Machine learning','Active');
INSERT INTO skills (skill_name,status_name) values ('Graphic Design','Active');
INSERT INTO skills (skill_name,status_name) values ('Algorithms','Active');
INSERT INTO skills (skill_name,status_name) values ('Git version control','Active');
INSERT INTO skills (skill_name,status_name) values ('Text editors','Active');
INSERT INTO skills (skill_name,status_name) values ('Web development','Active');
INSERT INTO skills (skill_name,status_name) values ('Containers','Active');

/*Populate developers*/
INSERT INTO developers (first_name, last_name, specialty_id,status_name) values ('Alex','Alexov',3,'Active');
INSERT INTO developers (first_name, last_name, specialty_id,status_name) values ('Max','Maxov',1,'Active');
INSERT INTO developers (first_name, last_name, specialty_id,status_name) values ('Ivan','Ivanov',4,'Active');
INSERT INTO developers (first_name, last_name, specialty_id,status_name) values ('Petr','Petrov',1,'Active');
INSERT INTO developers (first_name, last_name, specialty_id,status_name) values ('Semen','Semenov',7,'Active');

/*Populate developer_skills*/
INSERT INTO developer_skills (developer_id, skill_id) values (1, 2);
INSERT INTO developer_skills (developer_id, skill_id) values (1, 4);
INSERT INTO developer_skills (developer_id, skill_id) values (1, 7);
INSERT INTO developer_skills (developer_id, skill_id) values (2, 6);
INSERT INTO developer_skills (developer_id, skill_id) values (2, 2);
INSERT INTO developer_skills (developer_id, skill_id) values (3, 6);
INSERT INTO developer_skills (developer_id, skill_id) values (3, 1);
INSERT INTO developer_skills (developer_id, skill_id) values (4, 1);
INSERT INTO developer_skills (developer_id, skill_id) values (5, 2);
INSERT INTO developer_skills (developer_id, skill_id) values (5, 5);
INSERT INTO developer_skills (developer_id, skill_id) values (5, 7);
INSERT INTO developer_skills (developer_id, skill_id) values (5, 1);