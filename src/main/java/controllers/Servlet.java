package controllers;

import controllers.commands.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/app/*")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put("movies", new AllMovies());
        commands.put("movie", new SpecificMovie());
        commands.put("post-new-movie", new PostNewMovie());
        commands.put("update-movie", new UpdateMovie());
        commands.put("directors", new AllDirectors());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path, r -> "index.jsp");
        try {
            String page = command.execute(request);
            if (page.contains("redirect")) {
                response.sendRedirect(page.replace("redirect:", ""));
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new ServletException(e);
        }
    }
}
