package com.ryzhov_andrey.crud.repository.jdbc;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;
import com.ryzhov_andrey.crud.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static com.ryzhov_andrey.crud.utils.JdbcUtils.*;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    private Developer convertResultSetToDeveloper(ResultSet resultSet) throws SQLException {
        List<Skill> skillsList = new ArrayList<>();
        return new Developer(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                skillsList,
                new Specialty(resultSet.getString(4)),
                Status.valueOf(resultSet.getString(5).toUpperCase(Locale.ENGLISH)));
    }

    @Override
    public Developer getById(Long id) {
        Developer developer = null;
        try (Statement statement = JdbcUtils.getStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(DEVELOPER_GET_BY_ID, id));
            while (resultSet.next()) {
                developer = convertResultSetToDeveloper(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        List<Skill> skillList = new ArrayList<>();

        // для отслеживания повторных ID
        Set<Long> idSet = new HashSet<>();
        List<Long> idList = new LinkedList<>();

        try (Statement statement = JdbcUtils.getStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(DEVELOPER_GET_ALL, "Active"));
            while (resultSet.next()) {

                Developer developer = new Developer();

                // одноразовый лист скиллов
                List<Skill> skills = new ArrayList<>();

                // поиск двух или нескольких скиллов у одного разраба
                if (idSet.add(resultSet.getLong(1))) {

                    idList.add(resultSet.getLong(1));
                    skillList.add(new Skill(resultSet.getString(4)));
                    skills.add(new Skill(resultSet.getString(4)));

                    developer = new Developer(resultSet.getLong(1),
                                              resultSet.getString(2),
                                              resultSet.getString(3),
                                              skills,
                                new Specialty(resultSet.getString(5)),
                            Status.valueOf(resultSet.getString(6).toUpperCase(Locale.ENGLISH)));

                    developerList.add(developer);
                } else {
                    int indexDuplicateId = idList.indexOf(resultSet.getLong(1));

                    skills.add(skillList.get(indexDuplicateId));
                    skills.add(new Skill(resultSet.getString(4)));

                    developer = new Developer(resultSet.getLong(1),
                                              resultSet.getString(2),
                                              resultSet.getString(3),
                                              skills,
                            new Specialty(resultSet.getString(5)),
                            Status.valueOf(resultSet.getString(6).toUpperCase(Locale.ENGLISH)));
                    developerList.set(indexDuplicateId, developer);
                }
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developerList;
    }

    @Override
    public Developer create(Developer developer) {
        try (Statement statement = JdbcUtils.getStatement()) {
            statement.executeUpdate(String.format(DEVELOPER_CREATE,
                    developer.getFirstName(),
                    developer.getLastName(),
                    developer.getSpecialty().getId(),
                    "Active"));
            for (Skill s : developer.getSkills()) {
                statement.executeUpdate(String.format(DEVELOPER_CREATE_IN_SKILLS, s.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        try (Statement statement = JdbcUtils.getStatement()) {
            statement.executeUpdate(String.format(DEVELOPER_UPDATE,
                    developer.getFirstName(),
                    developer.getLastName(),
                    developer.getSpecialty().getId(),
                    "Active",
                    developer.getId()));

            statement.executeUpdate(String.format(DEVELOPER_DELETE_SKILLS, developer.getId()));

            for (Skill s : developer.getSkills()) {
                statement.executeUpdate(String.format(DEVELOPER_UPDATE_IN_SKILLS, developer.getId(), s.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        try (Statement statement = JdbcUtils.getStatement()) {
            statement.executeUpdate(String.format(DEVELOPER_DELETE_BY_ID, "Delete", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

