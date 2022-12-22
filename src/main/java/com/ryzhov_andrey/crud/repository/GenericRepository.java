package com.ryzhov_andrey.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(ID id);

    List<T> getAll();

    T create(T t);

    T update(T t);

    void deleteById(ID id);

    T save(T t);

}
