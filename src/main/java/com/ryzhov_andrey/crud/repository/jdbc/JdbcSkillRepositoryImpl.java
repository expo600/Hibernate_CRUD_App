package com.ryzhov_andrey.crud.repository.jdbc;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.SkillRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ryzhov_andrey.crud.repository.jdbc.JdbcUtils.*;


public class JdbcSkillRepositoryImpl implements SkillRepository {
    private ResultSet resultSet;
    private final Statement statement = JdbcConnection.getConnection();

    @Override
    public Skill getById(Long id) {
        Skill skill = new Skill();
        try {
            resultSet = statement.executeQuery(String.format(SKILL_GET_BY_ID, id));

            while (resultSet.next()) {
                skill = new Skill(resultSet.getLong(1),
                                  resultSet.getString(2),
                         (Status) resultSet.getObject(3));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skillList = new ArrayList<>();
        try {

            resultSet = statement.executeQuery(SKILL_GET_ALL);

            while (resultSet.next()) {
                Skill skill = new Skill(resultSet.getLong(1),
                                        resultSet.getString(2),
                               (Status) resultSet.getObject(3));

                skillList.add(skill);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillList;
    }


    @Override
    public Skill create(Skill skill) {
        Skill sk = new Skill();
        try {

            if (statement.executeUpdate(String.format(SKILL_CREATE, skill.getName(), "Active")) > 0) {

                resultSet = statement.executeQuery(RESULT_SKILL_CREATE);

                if (resultSet.next()) {
                      sk = new Skill(resultSet.getLong(1),
                                     resultSet.getString(2),
                            (Status) resultSet.getObject(3));
                }

                resultSet.close();
            } else {
                System.out.println(" Skill with this ID already exists ...");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sk;
    }

    @Override
    public Skill update(Skill skill) {
        Skill sk = new Skill();
        try {

            if (statement.executeUpdate(String.format(SKILL_UPDATE, skill.getName(), skill.getId())) > 0) {

                resultSet = statement.executeQuery(String.format(RESULT_SKILL_UPDATE, skill.getId()));

                while (resultSet.next()) {
                      sk = new Skill(resultSet.getLong(1),
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
        return sk;
    }

    @Override
    public void deleteById(Long id) {
        try {

            if (statement.executeUpdate(String.format(SKILL_DELETE, id)) > 0) {

                System.out.println(" Skill removed ...");
            } else {
                System.out.println(" No such skill ...");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
