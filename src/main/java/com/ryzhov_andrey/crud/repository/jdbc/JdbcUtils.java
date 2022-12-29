package com.ryzhov_andrey.crud.repository.jdbc;

public class JdbcUtils {

    // for  Developer
    public static final String DEVELOPER_GET_BY_ID_FOR_SKILLS =
            "SELECT sk.skill_name FROM skills sk " +
                    "JOIN developer_skills dsk ON sk.id = dsk.skill_id WHERE dsk.developer_id = %d;";

    public static final String DEVELOPER_GET_BY_ID =
            "SELECT d.id,d.first_name,d.last_name,s.specialty_name,d.status_name" +
                    "FROM developers d" +
                    "LEFT JOIN specialties s on d.specialty_id = s.id" +
                    "where d.id = %d;";


    public static final String DEVELOPER_DELETE_BY_ID =
            "UPDATE developers_database.developers SET status_name = 'DELETE' WHERE id = %d ;";


    public static final String DEVELOPER_GET_ALL =
            "SELECT d.id,d.first_name,d.last_name,s.skill_name,sp.specialty_name,d.status_name FROM developers d" +
                    "LEFT JOIN developer_skills ds ON d.id = ds.developer_id" +
                    "LEFT JOIN skills s ON s.id = ds.skill_id" +
                    "LEFT JOIN specialties sp ON d.specialty_id = sp.id WHERE d.status_name = 'ACTIVE';";

    public static final String DEVELOPER_CREATE =
            "INSERT INTO developers_database.developers (first_name, last_name, specialty_id, status_name)" +
                    " VALUES('%s', '%s', %d, '%s') ;";


    public static final String DEVELOPER_CREATE_IN_SKILLS =
            "INSERT INTO developers_database.developer_skills (developer_id,skill_id) VALUES(%d,%d);";


    public static final String DEVELOPER_UPDATE =
            "UPDATE developers_database.developers SET  ";

    // for  Skill ============================================================================================

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

    public static final String RESULT_SKILL_UPDATE =
            "SELECT * FROM developers_database.skills WHERE id = %d ;";


    public static final String SKILL_GET_ALL =
            "SELECT * FROM developers_database.skills WHERE status_name = 'ACTIVE';";


    public static final String SKILL_DELETE_BY_ID =
            "UPDATE developers_database.skills SET status_name = 'DELETE' WHERE id = %d ;";


    // for  Specialty ============================================================================================

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
            "SELECT * FROM developers_database.specialties WHERE status_name = 'ACTIVE';";


    public static final String SPECIALTY_DELETE_BY_ID =
            "UPDATE developers_database.specialties SET status_name = 'DELETE' WHERE id = %d ;";

}
