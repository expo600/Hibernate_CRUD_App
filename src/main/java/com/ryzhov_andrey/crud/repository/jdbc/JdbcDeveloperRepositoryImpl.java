package com.ryzhov_andrey.crud.repository.jdbc;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ryzhov_andrey.crud.repository.jdbc.JdbcUtils.*;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {
    private ResultSet resultSet;
    private final Statement statement = JdbcConnection.getConnection();

    @Override
    public Developer getById(Long id) {
        Developer developer = new Developer();
        List<Skill> skillsList = new ArrayList<>();

        try {

            resultSet = statement.executeQuery(String.format(DEVELOPER_GET_BY_ID_FOR_SKILLS, id));

            while (resultSet.next()) {
                skillsList.add(new Skill(resultSet.getString(1)));
            }

            resultSet = statement.executeQuery(String.format(DEVELOPER_GET_BY_ID, id));

            if (resultSet.next()) {

                developer = new Developer(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        skillsList,
                        new Specialty(resultSet.getString(4)),
                        (Status) resultSet.getObject(5));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developer;
    }

    @Override
    public List<Developer> getAll() {
        return null;
    }

    @Override
    public Developer create(Developer developer) {
        return null;
    }

    @Override
    public Developer update(Developer developer) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

}
