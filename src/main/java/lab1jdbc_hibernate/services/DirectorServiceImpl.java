package lab1jdbc_hibernate.services;

import lab1jdbc_hibernate.dao.DirectorDAO;
import lab1jdbc_hibernate.dao.impl.DirectorDAOImpl;
import lab1jdbc_hibernate.models.Director;

import java.util.List;

public class DirectorServiceImpl implements DirectorService {

    private DirectorDAO directorDAO = new DirectorDAOImpl();

    @Override
    public Director getByName(String name) {
        return directorDAO.getByName(name);
    }

    @Override
    public List<Director> getAll() {
        return directorDAO.getAll();
    }

    @Override
    public Director getById(int id) {
        return directorDAO.getById(id);
    }

    @Override
    public Director insertEntity(Director entity) {
        return directorDAO.insert(entity);
    }

    @Override
    public Director updateEntity(Director entity) {
        return directorDAO.update(entity);
    }

    @Override
    public boolean delete(int id) {
        return directorDAO.delete(id);
    }
}
