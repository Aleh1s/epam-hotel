package ua.aleh1s.hotelepam;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.database.DBManager;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private final static Logger LOGGER = LogManager.getLogger(HelloServlet.class);
    public void init() {
        LOGGER.trace("{} initialization...", HelloServlet.class.getName());
        DBManager.getInstance();
        LOGGER.trace("{} initialized", HttpServlet.class.getName());
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.trace("{} doGet is invoked", HelloServlet.class.getName());
    }
    public void destroy() {
        LOGGER.trace("{} destroying...", HelloServlet.class.getName());
        DBManager.shutdown();
        LOGGER.trace("{} destroyed", HttpServlet.class.getName());
    }
}