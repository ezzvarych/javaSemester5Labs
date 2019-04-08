package controllers.commands;

import controllers.EntityFromRequestCreator;
import lab1jdbc_hibernate.models.Movie;
import lab1jdbc_hibernate.services.DirectorService;
import lab1jdbc_hibernate.services.DirectorServiceImpl;
import lab1jdbc_hibernate.services.MovieService;
import lab1jdbc_hibernate.services.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class UpdateMovie implements Command {

    private MovieService movieService;
    private DirectorService directorService;

    public UpdateMovie() {
        movieService = new MovieServiceImpl();
        directorService = new DirectorServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        if (request.getMethod().equals("GET")) {
            Integer id;
            if (request.getParameter("id") == null ||
                    (id = Integer.parseInt(request.getParameter("id"))) == null) {
                throw new ServletException("Cannot pass request parameters!!!");
            } else {
                request.setAttribute("movie", movieService.getById(id));
                return "/views/update-movie.xhtml";
            }
        } else if (request.getMethod().equals("POST")) {
            Movie movie = movieService.updateEntity(EntityFromRequestCreator
                    .fulfillMovieFromRequest(request));
            request.setAttribute("movie", movie);
            return "/views/movie.xhtml";
        }
        return "";
    }
}
