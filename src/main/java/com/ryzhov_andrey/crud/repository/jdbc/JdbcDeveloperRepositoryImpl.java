package com.ryzhov_andrey.crud.repository.jdbc;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
        List<Developer> developerList = new ArrayList<>();
        List<Skill> skillList = new ArrayList<>();

        // для отслеживания повторных ID
        Set<Long> idSet = new HashSet<>();
        List<Long> idList = new LinkedList<>();

        try {

            resultSet = statement.executeQuery(DEVELOPER_GET_ALL);

            while (resultSet.next()) {

                Developer developer = new Developer();

                // одноразовый постлист
                List<Skill> skills = new ArrayList<>();

                // поиск двух или нескольких скиллов у одного девелопера
                if (idSet.add(resultSet.getLong(1))) {

                    idList.add(resultSet.getLong(1));
                    skillList.add(new Skill(resultSet.getString(4)));
                    skills.add(new Skill(resultSet.getString(4)));

                    developer = new Developer(resultSet.getLong(1), resultSet.getString(2),
                            resultSet.getString(3), skills,
                            new Specialty(resultSet.getString(5)),
                            (Status) resultSet.getObject(6));

                    developerList.add(developer);

                } else {

                    int indexDuplicateId = idList.indexOf(resultSet.getLong(1));

                    skills.add(skillList.get(indexDuplicateId));
                    skills.add(new Skill(resultSet.getString(4)));

                    developer = new Developer(resultSet.getLong(1), resultSet.getString(2),
                            resultSet.getString(3), skills, developer.getSpecialty(),
                            (Status) resultSet.getObject(6));

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
        Developer dev = new Developer();
        List<Skill> skillList = new ArrayList<>();

        try {

            for (Skill s : developer.getSkills()) {
                statement.execute(String.format(DEVELOPER_CREATE_IN_SKILLS, developer.getId(), s.getId()));
                skillList.add(s);
            }
            resultSet = statement.executeQuery(String.format(DEVELOPER_CREATE, developer.getFirstName(),
                    developer.getLastName(),
                    developer.getSpecialty().getId(),
                    "ACTIVE"));

            if (resultSet.next()) {

                dev = new Developer(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        skillList,
                        new Specialty(resultSet.getString(4)),
                        (Status) resultSet.getObject(5));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dev;
    }

    @Override
    public Developer update(Developer developer) {
        Developer dev = new Developer();
        List<Skill> skillList = new ArrayList<>();
        try {




            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dev;
    }

    @Override
    public void deleteById(Long id) {
        try {

            if (statement.executeUpdate(String.format(DEVELOPER_DELETE_BY_ID, id)) > 0) {

                System.out.println(" Developer removed ...");
            } else {
                System.out.println(" No such developer ...");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

