package controllers.commands;

import lab1jdbc_hibernate.models.Director;
import lab1jdbc_hibernate.services.DirectorService;
import lab1jdbc_hibernate.services.DirectorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllDirectors implements Command {

    private DirectorService directorService = new DirectorServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServletException {
        List<Director> directors = directorService.getAll();
        request.setAttribute("directors", directors);
        return "/views/all-directors.xhtml";
    }
}
