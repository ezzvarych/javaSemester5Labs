package lab1jdbc_hibernate.services;

import java.util.List;

public interface AbstractService<E> {
    List<E> getAll();
    E getById(int id);
    E insertEntity(E entity);
    E updateEntity(E entity);
    boolean delete(int id);
}
