package lab1jdbc_hibernate.dao;

import lab1jdbc_hibernate.models.Movie;

import java.util.List;

public interface MovieDAO extends CrudDAO<Movie> {
    Movie getByTitle(String title);
    //List<Movie> getByDirectorName(String directorName);
}
