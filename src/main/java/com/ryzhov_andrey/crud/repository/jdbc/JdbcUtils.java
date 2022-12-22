package com.ryzhov_andrey.crud.repository.jdbc;

public class JdbcUtils {
    // for  Developer

    public static final String WRITER_GET_BY_ID = "SELECT writer_id,a.first_name,a.last_name, " +
            "a.region_name " +
            "FROM (SELECT writers.id, first_name, last_name, region_name " +
            "FROM practice.writers " +
            "LEFT JOIN practice.regions " +
            "ON practice.writers.id = practice.regions.writer_id) AS a  " +
            "LEFT JOIN practice.posts " +
            "ON a.id = practice.posts.writer_id  " +
            "WHERE a.id = %d ;";


    public static final String WRITER_GET_BY_ID_FOR_POSTS = "SELECT posts.content " +
            "FROM (SELECT practice.writers.id " +
            "FROM practice.writers " +
            "LEFT JOIN practice.regions " +
            "ON practice.writers.id = practice.regions.writer_id) AS a  " +
            "LEFT JOIN practice.posts " +
            "ON  practice.posts.writer_id = a.id  " +
            "WHERE a.id = %d ;";



    public static final String WRITER_CREATE = "INSERT INTO practice.writers (first_name, last_name) " +
            "VALUES('%s' , '%s') ;";

    public static final String WRITER_CREATE_IN_REGION = "INSERT INTO practice.regions (writer_id) " +
            "VALUES((SELECT MAX(id) FROM practice.writers)) ;";

    public static final String WRITER_CREATE_IN_POST = "INSERT INTO practice.posts (created, writer_id) " +
            "VALUES( \"%tF\", (SELECT MAX(id) FROM practice.writers)) ;";


    public static final String RESULT_WRITER_CREATE = "SELECT writer_id,a.first_name,a.last_name, " +
            "a.region_name " +
            "FROM (SELECT writers.id, first_name, last_name, region_name " +
            "FROM practice.writers " +
            "LEFT JOIN practice.regions " +
            "ON writers.id = regions.writer_id " +
            "WHERE writers.id = (SELECT MAX(id) " +
            "FROM practice.writers)) AS a " +
            "LEFT JOIN practice.posts " +
            "ON a.id = practice.posts.writer_id ;";


    public static final String WRITER_CREATE_FOR_POSTS = "SELECT content " +
            "FROM (SELECT writers.id, first_name, last_name, region_name " +
            "FROM practice.writers " +
            "LEFT JOIN practice.regions " +
            "ON writers.id = regions.writer_id " +
            "WHERE writers.id = (SELECT MAX(id) " +
            "FROM practice.writers)) AS a " +
            "LEFT JOIN practice.posts " +
            "ON practice.posts.writer_id = a.id ;";



    public static final String WRITER_UPDATE = "UPDATE practice.writers " +
            "SET first_name = '%s' , last_name = '%s'  " +
            "WHERE id = %d ;";

    public static final String RESULT_WRITER_UPDATE = "SELECT writer_id,a.first_name,a.last_name, " +
            "a.region_name " +
            "FROM (SELECT writers.id, first_name, last_name, region_name " +
            "FROM practice.writers " +
            "LEFT JOIN practice.regions " +
            "ON practice.writers.id = practice.regions.writer_id ) AS a " +
            "LEFT JOIN practice.posts " +
            "ON practice.posts.writer_id  = a.id " +
            "WHERE a.id = %d ;";


    public static final String WRITER_UPDATE_FOR_POSTS = "SELECT content " +
            "FROM (SELECT writers.id, first_name, last_name, region_name " +
            "FROM practice.writers " +
            "LEFT JOIN practice.regions " +
            "ON practice.writers.id = practice.regions.writer_id ) AS a " +
            "LEFT JOIN practice.posts " +
            "ON practice.posts.writer_id = a.id " +
            "WHERE a.id = %d ;";


    public static final String WRITER_GET_ALL = "SELECT writer_id,a.first_name,a.last_name, a.region_name, " +
            "content   FROM (SELECT writers.id, first_name, last_name, region_name " +
            "FROM practice.writers " +
            "LEFT JOIN practice.regions " +
            "ON practice.writers.id = practice.regions.writer_id) AS a " +
            "LEFT JOIN practice.posts " +
            "ON practice.posts.writer_id = a.id ;";



    public static final String WRITER_DELETE = "DELETE " +
            "FROM practice.writers " +
            "WHERE id = %d ;";


    public static final String DELETE_WRITER_ID_IN_POST = "DELETE " +
            "FROM practice.posts " +
            "WHERE writer_id = %d ;";


    public static final String DELETE_WRITER_ID_IN_REGION = "DELETE " +
            "FROM practice.regions " +
            "WHERE writer_id = %d ;";



    // for  Skill

    public static final String SKILL_GET_BY_ID =
            "SELECT * FROM developers_database.skills WHERE id = %d ;";


    public static final String SKILL_CREATE =
            "INSERT INTO developers_database.skills (skill_name, status_name) " +
            "VALUES('%s','%s');";


    public static final String RESULT_SKILL_CREATE =
            "SELECT * FROM developers_database.skills " +
            "WHERE id = (SELECT MAX(id) FROM developers_database.skills) ;";


    public static final String SKILL_UPDATE =
            "UPDATE developers_database.skills SET specialty_name = '%s' WHERE id = %d ;";

    public static final String RESULT_SKILL_UPDATE = "SELECT * FROM developers_database.skills " +
            "WHERE id = %d ;";


    public static final String SKILL_GET_ALL = "SELECT * FROM developers_database.skills ;";


    public static final String SKILL_DELETE =
            "DELETE FROM developers_database.skills WHERE id = %d ;";


    public static final String SKILL_SAVE =
            "INSERT INTO developers_database.skills (id, skill_name, status_name) " +
                    "VALUES(%d,'%s','%s');";


    // for  Specialty

    public static final String SPECIALTY_GET_BY_ID = "SELECT * " +
            "FROM developers_database.specialties " +
            "WHERE id = %d ;";

    public static final String SPECIALTY_CREATE =
            "INSERT INTO developers_database.specialties (specialty_name, status_name) " +
            "VALUES('%s','%s');";


    public static final String RESULT_SPECIALTY_CREATE =
            "SELECT * FROM developers_database.specialties " +
            "WHERE id =(SELECT MAX(id) FROM developers_database.specialties) ;";

    public static final String SPECIALTY_UPDATE =
            "UPDATE developers_database.specialties SET specialty_name = '%s' WHERE id = %d ;";


    public static final String RESULT_SPECIALTY_UPDATE =
            "SELECT * FROM developers_database.specialties  WHERE id = %d ;";


    public static final String SPECIALTY_GET_ALL =
            "SELECT * FROM developers_database.specialties ;";


    public static final String SPECIALTY_DELETE =
            "DELETE FROM developers_database.specialties WHERE id = %d ;";


    public static final String SPECIALTY_SAVE =
            "INSERT INTO developers_database.specialties (id, specialty_name, status_name) " +
                    "VALUES(%d,'%s','%s');";
}
