package ua.aleh1s.hotelepam.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.model.entity.UserRole;

import java.io.IOException;
import java.util.*;

import static java.util.Objects.isNull;
import static ua.aleh1s.hotelepam.model.entity.UserRole.CUSTOMER;
import static ua.aleh1s.hotelepam.model.entity.UserRole.MANAGER;

@WebFilter(urlPatterns = "/**", initParams = {
        @WebInitParam(name = "manager",
                value = "applicationList,makeRequest," +
                        "reservationList,viewApplicationDetails"),
        @WebInitParam(name = "customer",
                value = "application,book,bookPage," +
                        "changeRequestStatus,confirmBooking," +
                        "confirmPayment,myBookings,paymentPage,topUpAccount"),
        @WebInitParam(name = "commons",
                value = "changeReservationStatus,logOut,profile"),
        @WebInitParam(name = "out_of_control",
                value = "i18n,login,redirectToErrorPage," +
                        "roomList,signup,viewRoom")
}, servletNames = "Controller")
public class SecurityFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(SecurityFilter.class);
    private Map<UserRole, Set<String>> accessMap;
    private Set<String> commons;
    private Set<String> outOfControl;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.trace("Filter initialization");

        accessMap = new HashMap<>();
        accessMap.put(CUSTOMER, toSet(filterConfig.getInitParameter("customer")));
        accessMap.put(MANAGER, toSet(filterConfig.getInitParameter("manager")));

        commons = toSet(filterConfig.getInitParameter("commons"));
        outOfControl = toSet(filterConfig.getInitParameter("out_of_control"));

        logger.trace("Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.trace("Filter starts");

        if (accessAllowed(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            logger.trace("Filter finished");
        } else {
            logger.debug("Access denied");
            servletRequest.getRequestDispatcher(ResourcesManager.getInstance().getValue("path.page.error"))
                    .forward(servletRequest, servletResponse);
        }
    }

    private boolean accessAllowed(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Optional<String> commandOptional =
                Optional.ofNullable(request.getParameter("command"));

        if (commandOptional.isEmpty())
            return false;

        String command = commandOptional.get();

        if (command.isBlank())
            return false;

        if (outOfControl.contains(command))
            return true;

        HttpSession session = request.getSession(false);
        if (isNull(session))
            return false;

        UserRole role = (UserRole) session.getAttribute("role");
        if (isNull(role))
            return false;

        return accessMap.get(role).contains(command) || commons.contains(command);
    }

    @Override
    public void destroy() {
        logger.trace("Security filter destroying");
        // do nothing
        logger.trace("Security filter destroyed");
    }

    private Set<String> toSet(String commands) {
        String[] split = commands.split(",");
        return new HashSet<>(List.of(split));
    }
}
