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
            "SELECT * FROM developers_database.skills ;";


    public static final String SKILL_DELETE =
            "DELETE FROM developers_database.skills WHERE id = %d ;";





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
            "SELECT * FROM developers_database.specialties ;";


    public static final String SPECIALTY_DELETE =
            "DELETE FROM developers_database.specialties WHERE id = %d ;";

}
