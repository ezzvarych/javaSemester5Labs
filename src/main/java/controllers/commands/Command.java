package controllers.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws ServletException;
}
