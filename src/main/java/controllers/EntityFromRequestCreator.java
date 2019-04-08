package controllers;

import lab1jdbc_hibernate.models.Director;
import lab1jdbc_hibernate.models.Movie;
import lab1jdbc_hibernate.services.DirectorService;
import lab1jdbc_hibernate.services.DirectorServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class EntityFromRequestCreator {

    //private static final MovieService movieService = new MovieServiceImpl();
    private static final DirectorService directorService = new DirectorServiceImpl();

    public static Movie fulfillMovieFromRequest(HttpServletRequest req) {
        Movie movie = new Movie();
        movie.setId(Integer.parseInt(req.getParameter("id")));
        movie.setTitle(req.getParameter("title").trim());
        movie.setDirector(createMovieDirector(req));
        movie.setLength(Integer.parseInt(req.getParameter("length")));
        movie.setShortDescr(req.getParameter("short-descr"));
        movie.setYear(Integer.parseInt(req.getParameter("year")));
        return movie;
    }

    private static Director createMovieDirector(HttpServletRequest req) {
        String directorName = req.getParameter("director");
        Director director = directorService.getByName(directorName);
        if (director == null) {
            return directorService.updateEntity(new Director(directorName));
        } else {
            return director;
        }
    }

}
