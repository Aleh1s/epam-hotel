package ua.aleh1s.hotelepam.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static java.util.Objects.*;

@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class EncodingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(EncodingFilter.class);
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.trace("Filter initialization");
        encoding = filterConfig.getInitParameter("encoding");
        logger.trace("Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.trace("Filter starts");

        if (isNull(servletRequest.getCharacterEncoding())) {
            logger.warn("Request character encoding is null, set default");
            servletRequest.setCharacterEncoding(encoding);
        }

        logger.trace("Filter finished");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.trace("Filter destroying");
        // do nothing
        logger.trace("Filter destroyed");
    }
}
