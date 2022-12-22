package com.ryzhov_andrey.crud.repository.jdbc;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {
    private ResultSet resultSet;
    private final Statement statement = JdbcConnection.getConnection();
    @Override
    public Developer getById(Long id) {
        return null;
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

    @Override
    public Developer save(Developer developer) {
        return null;
    }
}
