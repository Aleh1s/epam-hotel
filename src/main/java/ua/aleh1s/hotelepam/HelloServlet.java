package ua.aleh1s.hotelepam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.jdbc.DatabaseManagerFactory;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.UserSimpleDao;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.role.UserRole;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Locale;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private final static Logger LOGGER = LogManager.getLogger(HelloServlet.class);
    public void init() {
        LOGGER.trace("{} initialization...", HelloServlet.class.getName());
        DatabaseManagerFactory.INSTANCE.getDatabaseManager();
        LOGGER.trace("{} initialized", HttpServlet.class.getName());
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.trace("{} doGet is invoked", HelloServlet.class.getName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String timezone = req.getParameter("timezone");
        String locale = req.getParameter("locale");
        String role = req.getParameter("role");
        UserSimpleDao dao = new UserSimpleDao();
        UserEntity user = UserEntity.Builder.newBuilder()
                .login(login)
                .password(password)
                .timezone(ZoneId.of(timezone))
                .locale(new Locale(locale))
                .role(UserRole.valueOf(role))
                .build();
        try {
            dao.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        LOGGER.trace("{} destroying...", HelloServlet.class.getName());
        DatabaseManagerFactory.INSTANCE.getDatabaseManager();
        LOGGER.trace("{} destroyed", HttpServlet.class.getName());
    }
}