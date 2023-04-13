package ua.aleh1s.hotelepam.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.CommandFactory;
import ua.aleh1s.hotelepam.controller.command.impl.UnknownCommand;
import ua.aleh1s.hotelepam.controller.exception.HandlerFactory;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.model.jdbc.DBManager;

import java.io.IOException;

@MultipartConfig(maxFileSize = 1_048_576)
@WebServlet(name = "Controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {

    private static final Logger log = LogManager.getLogger(Controller.class);
    private CommandFactory commandFactory;
    private HandlerFactory handlerFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.trace("Controller initialization");
        DBManager.getInstance();
        commandFactory = CommandFactory.getInstance();
        handlerFactory = HandlerFactory.getInstance();
        log.trace("Controller initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandStr = request.getParameter("command");
        Command command = commandFactory.getCommand(commandStr)
                .orElse(new UnknownCommand());

        String path;
        try {
            path = command.execute(request, response);
        } catch (ApplicationException e) {
            path = handlerFactory.getHandler(e).handle(e, request, response);
        }

        if (!path.equals("redirect")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        log.trace("Controller destroying");
        DBManager.getInstance().shutdown();
        log.trace("Controller destroyed");
    }
}
