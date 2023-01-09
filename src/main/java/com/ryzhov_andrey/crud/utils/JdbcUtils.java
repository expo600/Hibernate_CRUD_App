package com.ryzhov_andrey.crud.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static Connection connection;
    private static Statement statement;


    private static synchronized Connection getConnection() {
        try {
            if (connection == null) {
                Properties properties = new Properties();

                try {
                    properties.load(new FileInputStream("src/main/resources/application.properties"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                connection = DriverManager.getConnection(properties.getProperty("url"),
                        properties.getProperty("username"), properties.getProperty("password"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return connection;
    }

    public static synchronized Statement getStatement() throws SQLException {
        return getConnection().createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }


    //TODO: merge 2 queries into 1
    // for  Developer
    public static final String DEVELOPER_GET_BY_ID =
            "SELECT d.id,d.first_name,d.last_name,s.specialty_name,d.status_name " +
                    "FROM developers d LEFT JOIN specialties s ON d.specialty_id = s.id WHERE d.id = %d;";


    public static final String DEVELOPER_DELETE_BY_ID =
            "UPDATE developers_database.developers SET status_name = '%s' WHERE id = %d ;";


    public static final String DEVELOPER_GET_ALL =
            " SELECT d.id,d.first_name,d.last_name,sp.specialty_name,d.status_name FROM developers d " +
                    "LEFT JOIN specialties sp ON d.specialty_id = sp.id WHERE d.status_name = 'Active';";
//            "SELECT d.id, d.first_name, d.last_name, s.skill_name,sp.specialty_name,d.status_name FROM developers d" +
//                    "LEFT JOIN developer_skills ds ON d.id = ds.developer_id" +
//                    "LEFT JOIN skills s ON s.id = ds.skill_id" +
//                    "LEFT JOIN specialties sp ON d.specialty_id = sp.id WHERE d.status_name = 'Active';";

    public static final String DEVELOPER_CREATE =
            "INSERT INTO developers_database.developers (first_name, last_name, specialty_id, status_name)" +
                    " VALUES('%s', '%s', %d, '%s') ;";


    public static final String DEVELOPER_CREATE_IN_SKILLS =
            "INSERT INTO developers_database.developer_skills (developer_id ,skill_id) VALUES((select max(id) from developers), %d);";


    public static final String DEVELOPER_UPDATE =
            "UPDATE developers_database.developers SET" +
                    "(first_name = '%s', last_name = '%s', specialty_id = %d, status_name = '%s') WHERE id = %d ;";

    public static final String DEVELOPER_UPDATE_IN_SKILLS =
            "UPDATE developers_database.developer_skills SET skill_id = '%s' WHERE developer_id = %d);";

    public static final String RESULT_DEVELOPER_UPDATE =
            "SELECT * FROM developers WHERE id = %d ;";

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
            "UPDATE developers_database.skills SET skill_name = '%s', status_name = '%s' WHERE id = %d ;";

    public static final String RESULT_SKILL_UPDATE =
            "SELECT * FROM developers_database.skills WHERE id = %d ;";


    public static final String SKILL_GET_ALL =
            "SELECT * FROM developers_database.skills WHERE status_name = '%s';";


    public static final String SKILL_DELETE_BY_ID =
            "UPDATE developers_database.skills SET status_name = 'Delete' WHERE id = %d ;";


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
            "UPDATE developers_database.specialties SET specialty_name = '%s',status_name = '%s' WHERE id = %d ;";


    public static final String RESULT_SPECIALTY_UPDATE =
            "SELECT * FROM developers_database.specialties  WHERE id = %d ;";


    public static final String SPECIALTY_GET_ALL =
            "SELECT * FROM developers_database.specialties WHERE status_name = '%s';";


    public static final String SPECIALTY_DELETE_BY_ID =
            "UPDATE developers_database.specialties SET status_name = 'Delete' WHERE id = %d ;";

}
