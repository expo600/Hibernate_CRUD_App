package com.ryzhov_andrey.crud.repository;

import com.ryzhov_andrey.crud.model.Skill;

import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(ID id) throws Exception;

    List<T> getAll() throws Exception;

    Skill save(T t) throws Exception;

    Skill update(T t) throws Exception;

    void deleteById(ID id) throws Exception;

}
