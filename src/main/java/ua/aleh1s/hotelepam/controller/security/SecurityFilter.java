package ua.aleh1s.hotelepam.controller.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;

import java.io.IOException;

@WebFilter(urlPatterns = "/controller/*", servletNames = "Controller")
public class SecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(SecurityFilter.class);
    private SecurityManager securityManager;
    private String accessDeniedPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.trace("Filter initialization");

        this.accessDeniedPath = ResourcesManager.getInstance().getValue("path.page.access.denied");
        this.securityManager = AppContext.getInstance().getSecurityManager();
        this.securityManager.init();

        logger.trace("Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.trace("Filter starts");

        if (securityManager.isUserAuthorized((HttpServletRequest) servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            logger.trace("Filter finished");
        } else {
            logger.debug("Access denied");
            servletRequest.getRequestDispatcher(accessDeniedPath)
                    .forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.trace("Security filter destroying");
        // do nothing
        logger.trace("Security filter destroyed");
    }
}
