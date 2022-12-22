package com.ryzhov_andrey.crud.repository.jdbc;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.SpecialtyRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ryzhov_andrey.crud.repository.jdbc.JdbcUtils.*;

public class JdbcSpecialtyRepositoryImpl implements SpecialtyRepository {
    private ResultSet resultSet;
    private final Statement statement = JdbcConnection.getConnection();

    @Override
    public Specialty getById(Long id) {
        Specialty specialty = new Specialty();
        try {
            resultSet = statement.executeQuery(String.format(SPECIALTY_GET_BY_ID, id));

            while (resultSet.next()) {
                specialty = new Specialty(resultSet.getLong(1),
                                          resultSet.getString(2),
                                 (Status) resultSet.getObject(3));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialty;
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialtyList = new ArrayList<>();
        try {

            resultSet = statement.executeQuery(SPECIALTY_GET_ALL);

            while (resultSet.next()) {
                Specialty specialty = new Specialty(resultSet.getLong(1),
                                                    resultSet.getString(2),
                                           (Status) resultSet.getObject(3));

                specialtyList.add(specialty);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialtyList;
    }


    @Override
    public Specialty create(Specialty specialty) {
        Specialty spec = new Specialty();
        try {

            if (statement.executeUpdate(String.format(SPECIALTY_CREATE, specialty.getName(), "Active")) > 0) {

                resultSet = statement.executeQuery(RESULT_SPECIALTY_CREATE);

                if (resultSet.next()) {
                    specialty = new Specialty(resultSet.getLong(1),
                            resultSet.getString(2),
                            (Status) resultSet.getObject(3));
                }

                resultSet.close();
            } else {
                System.out.println(" Specialty with this ID already exists ...");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spec;
    }

    @Override
    public Specialty update(Specialty specialty) {
        Specialty spec = new Specialty();
        try {

            if (statement.executeUpdate(String.format(SPECIALTY_UPDATE, specialty.getName(), specialty.getId())) > 0) {

                resultSet = statement.executeQuery(String.format(RESULT_SPECIALTY_UPDATE, specialty.getId()));

                while (resultSet.next()) {
                    spec = new Specialty(resultSet.getLong(1),
                            resultSet.getString(2),
                            (Status) resultSet.getObject(3));
                }
                resultSet.close();

            } else {
                System.out.println(" Unable to update specialty ...");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spec;
    }

    @Override
    public void deleteById(Long id) {
        try {

            if (statement.executeUpdate(String.format(SPECIALTY_DELETE, id)) > 0) {

                System.out.println(" Specialty removed ...");
            } else {
                System.out.println(" No such specialty ...");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Specialty save(Specialty specialty) {
        try {
            statement.executeUpdate(String.format(SPECIALTY_SAVE, specialty.getId(), specialty.getName(), specialty.getStatus()));

        }catch (SQLException e) {

        }
        return specialty;
    }
}


