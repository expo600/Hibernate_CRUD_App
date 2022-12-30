package com.ryzhov_andrey.crud.repository.jdbc;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.SpecialtyRepository;
import com.ryzhov_andrey.crud.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.ryzhov_andrey.crud.utils.JdbcUtils.*;

public class JdbcSpecialtyRepositoryImpl implements SpecialtyRepository {

    private Specialty convertResultSetToSpecialty(ResultSet resultSet) throws SQLException {
        return new Specialty(resultSet.getLong(1),
                             resultSet.getString(2),
                Status.valueOf(resultSet.getString(3).toUpperCase(Locale.ENGLISH)));
    }

    @Override
    public Specialty getById(Long id) {
        Specialty specialty = null;
        try (Statement statement = JdbcUtils.getStatement()){
            ResultSet resultSet = statement.executeQuery(String.format(SPECIALTY_GET_BY_ID,id));
            while (resultSet.next()) {
                specialty = convertResultSetToSpecialty(resultSet);
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
        try (Statement statement = JdbcUtils.getStatement()){
            ResultSet resultSet = statement.executeQuery(String.format(SPECIALTY_GET_ALL,"Active"));
            while (resultSet.next()) {
                specialtyList.add(convertResultSetToSpecialty(resultSet));
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
        try (Statement statement = JdbcUtils.getStatement()) {
       statement.executeUpdate(String.format(SPECIALTY_CREATE, specialty.getName(), "Active"));
                ResultSet resultSet = statement.executeQuery(RESULT_SPECIALTY_CREATE);
                while (resultSet.next()) {
                    specialty = convertResultSetToSpecialty(resultSet);
                }
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spec;
    }


    @Override
    public Specialty update(Specialty specialty) {
        Specialty spec = new Specialty();
        try (Statement statement = JdbcUtils.getStatement()) {
            statement.executeUpdate(String.format(SPECIALTY_UPDATE, specialty.getName(),"Active", specialty.getId()));
             ResultSet resultSet = statement.executeQuery(String.format(RESULT_SPECIALTY_UPDATE, specialty.getId()));
                while (resultSet.next()) {
                    spec = convertResultSetToSpecialty(resultSet);
                }
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spec;
    }

    @Override
    public void deleteById(Long id) {
        try (Statement statement = JdbcUtils.getStatement()) {
            statement.executeUpdate(String.format(SPECIALTY_DELETE_BY_ID, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


