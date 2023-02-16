package ua.aleh1s.hotelepam.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.trace("Context initialization");
        // do nothing
        logger.trace("Context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.trace("Context destroying");
        // do nothing
        logger.trace("Context destroyed");
    }
}