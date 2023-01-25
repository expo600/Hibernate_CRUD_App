/*Populate specialties*/
INSERT INTO specialties (specialty_name,status_name) values ('Java','ACTIVE');
INSERT INTO specialties (specialty_name,status_name) values ('C#','ACTIVE');
INSERT INTO specialties (specialty_name,status_name) values ('C++','ACTIVE');
INSERT INTO specialties (specialty_name,status_name) values ('JavaScript','ACTIVE');
INSERT INTO specialties (specialty_name,status_name) values ('PHP','ACTIVE');
INSERT INTO specialties (specialty_name,status_name) values ('Kotlin','ACTIVE');
INSERT INTO specialties (specialty_name,status_name) values ('Python','ACTIVE');
INSERT INTO specialties (specialty_name,status_name) values ('Go','ACTIVE');

/*Populate skills*/
INSERT INTO skills (skill_name,status_name) values ('skill_1','ACTIVE');
INSERT INTO skills (skill_name,status_name) values ('skill_2','ACTIVE');
INSERT INTO skills (skill_name,status_name) values ('skill_3','ACTIVE');
INSERT INTO skills (skill_name,status_name) values ('skill_4','ACTIVE');
INSERT INTO skills (skill_name,status_name) values ('skill_5','ACTIVE');
INSERT INTO skills (skill_name,status_name) values ('skill_6','ACTIVE');
INSERT INTO skills (skill_name,status_name) values ('skill_7','ACTIVE');
INSERT INTO skills (skill_name,status_name) values ('skill_8','ACTIVE');

/*Populate developers*/
INSERT INTO developers (first_name, last_name, specialty_id,status_name) values ('Alex','Alexov',3,'ACTIVE');
INSERT INTO developers (first_name, last_name, specialty_id,status_name) values ('Max','Maxov',1,'ACTIVE');
INSERT INTO developers (first_name, last_name, specialty_id,status_name) values ('Ivan','Ivanov',4,'ACTIVE');


/*Populate developer_skills*/
INSERT INTO developer_skills (developer_id, skill_id) values (1, 2);
INSERT INTO developer_skills (developer_id, skill_id) values (1, 4);
INSERT INTO developer_skills (developer_id, skill_id) values (1, 7);
INSERT INTO developer_skills (developer_id, skill_id) values (2, 6);
INSERT INTO developer_skills (developer_id, skill_id) values (2, 2);
INSERT INTO developer_skills (developer_id, skill_id) values (3, 6);