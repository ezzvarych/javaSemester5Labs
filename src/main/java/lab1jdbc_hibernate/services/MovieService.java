package lab1jdbc_hibernate.services;

import lab1jdbc_hibernate.models.Movie;

import java.util.List;

public interface MovieService extends AbstractService<Movie> {
    Movie getByTitle(String title);

    //List<Movie> getByDirectorName(String directorName);
}
