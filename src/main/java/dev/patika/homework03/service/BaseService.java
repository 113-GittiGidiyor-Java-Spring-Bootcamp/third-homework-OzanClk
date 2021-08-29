package dev.patika.homework03.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    Optional<T> save(T object);

    Optional<T> findById(int id);

    Optional<T> update(T object);

    Iterable<T> findByAll();

    void deleteById(int id);


}
