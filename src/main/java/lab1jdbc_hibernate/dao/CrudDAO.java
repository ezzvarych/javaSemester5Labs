package lab1jdbc_hibernate.dao;

import java.util.List;

public interface CrudDAO<T> {
    List<T> getAll();
    T getById(int id);
    T insert(T item);
    T update(T item);
    boolean delete(int id);
}
