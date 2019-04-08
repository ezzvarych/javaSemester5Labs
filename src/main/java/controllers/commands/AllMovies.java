package controllers.commands;

import lab1jdbc_hibernate.models.Movie;
import lab1jdbc_hibernate.services.MovieService;
import lab1jdbc_hibernate.services.MovieServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllMovies implements Command {
    private MovieService movieService;

    public AllMovies() {
        this.movieService = new MovieServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Movie> movies = movieService.getAll();
        request.setAttribute("movies", movies);
        return "/views/all-movies.xhtml";
    }
}
