package ua.gorbatov.library.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity);

    T findById(int id);

    List<T> findAll();

    void delete(int id);

    List<T> findAll(int offset, int noOfRecords);
}
