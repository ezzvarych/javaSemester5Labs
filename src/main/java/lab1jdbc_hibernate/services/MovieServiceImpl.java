package lab1jdbc_hibernate.services;

import lab1jdbc_hibernate.dao.MovieDAO;
import lab1jdbc_hibernate.dao.impl.MovieDAOImpl;
import lab1jdbc_hibernate.models.Movie;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private MovieDAO movieDAO = new MovieDAOImpl();

    @Override
    public Movie getByTitle(String title) {
        return movieDAO.getByTitle(title);
    }

    /*@Override
    public List<Movie> getByDirectorName(String directorName) {
        return movieDAO.getByDirectorName(directorName);
    }*/

    @Override
    public List<Movie> getAll() {
        return movieDAO.getAll();
    }

    @Override
    public Movie getById(int id) {
        return movieDAO.getById(id);
    }

    @Override
    public Movie insertEntity(Movie entity) {
        return movieDAO.insert(entity);
    }

    @Override
    public Movie updateEntity(Movie entity) {
        return movieDAO.update(entity);
    }

    @Override
    public boolean delete(int id) {
        return movieDAO.delete(id);
    }
}
