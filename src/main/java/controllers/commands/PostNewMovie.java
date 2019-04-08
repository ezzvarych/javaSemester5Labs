package controllers.commands;

import controllers.EntityFromRequestCreator;
import lab1jdbc_hibernate.models.Movie;
import lab1jdbc_hibernate.services.DirectorService;
import lab1jdbc_hibernate.services.DirectorServiceImpl;
import lab1jdbc_hibernate.services.MovieService;
import lab1jdbc_hibernate.services.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class PostNewMovie implements Command {
    private MovieService movieService;
    private DirectorService directorService;

    public PostNewMovie() {
        this.movieService = new MovieServiceImpl();
        this.directorService = new DirectorServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        if (request.getMethod().equals("GET")) {
            return "/views/add-new-movie.xhtml";
        } else if (request.getMethod().equals("POST")) {
            Movie movie;
            try {
                movie = movieService.insertEntity(EntityFromRequestCreator
                        .fulfillMovieFromRequest(request));
            } catch (NumberFormatException n) {
                throw new ServletException(n);
            }
            request.setAttribute("movie", movie);
            return "/views/movie.xhtml";
        }
        return "";
    }
}
