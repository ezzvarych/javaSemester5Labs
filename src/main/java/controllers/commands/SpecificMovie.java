package controllers.commands;

import lab1jdbc_hibernate.models.Movie;
import lab1jdbc_hibernate.services.MovieService;
import lab1jdbc_hibernate.services.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class SpecificMovie implements Command {
    private MovieService movieService;

    public SpecificMovie() {
        this.movieService = new MovieServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        Movie movie = null;
        if (request.getParameter("id") == null ||
                (movie = movieService.getById(Integer.parseInt(request.getParameter("id")))) == null) {
            //resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new ServletException("NO OR WRONG PARAMETERS");
        } else {
            request.setAttribute("movie", movie);
            return "/views/movie.xhtml";
        }
    }
}
