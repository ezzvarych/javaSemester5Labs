package lab1jdbc_hibernate.dao;

import lab1jdbc_hibernate.models.Director;
import lab1jdbc_hibernate.models.Movie;

import java.util.List;

public interface DirectorDAO extends CrudDAO<Director> {
    Director getByName(String name);
    List<Movie> getDirectorMovies(String author);
}
