package com.ryzhov_andrey.crud.repository.jdbc;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.SkillRepository;
import com.ryzhov_andrey.crud.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.ryzhov_andrey.crud.utils.JdbcUtils.*;


public class JdbcSkillRepositoryImpl implements SkillRepository {

    private Skill convertResultSetToSkill(ResultSet resultSet) throws SQLException {
        return new Skill(resultSet.getLong(1),
                resultSet.getString(2),
                Status.valueOf(resultSet.getString(3).toUpperCase(Locale.ENGLISH)));
    }

    @Override
    public Skill getById(Long id) {
        Skill skill = null;
        try (Statement statement = JdbcUtils.getStatement()){
            ResultSet resultSet = statement.executeQuery(String.format(SKILL_GET_BY_ID,id));
            while (resultSet.next()) {
                skill = convertResultSetToSkill(resultSet);
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
        try (Statement statement = JdbcUtils.getStatement()){
            ResultSet resultSet = statement.executeQuery(String.format(SKILL_GET_ALL,"Active"));
            while (resultSet.next()) {
                skillList.add(convertResultSetToSkill(resultSet));
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
        try (Statement statement = JdbcUtils.getStatement()) {
            statement.executeUpdate(String.format(SKILL_CREATE, skill.getName(), "Active"));
            ResultSet resultSet = statement.executeQuery(String.format(RESULT_SKILL_CREATE, skill.getId()));
            while (resultSet.next()) {
                sk = convertResultSetToSkill(resultSet);
            }
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sk;
    }

    @Override
    public Skill update(Skill skill) {
        Skill sk = new Skill();
        try (Statement statement = JdbcUtils.getStatement()) {
            statement.executeUpdate(String.format(SKILL_UPDATE, skill.getName(),"Active", skill.getId()));
            ResultSet resultSet = statement.executeQuery(String.format(RESULT_SKILL_UPDATE, skill.getId()));
            while (resultSet.next()) {
                sk = convertResultSetToSkill(resultSet);
            }
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sk;
    }

    @Override
    public void deleteById(Long id) {
        try (Statement statement = JdbcUtils.getStatement()) {
            statement.executeUpdate(String.format(SKILL_DELETE_BY_ID, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
