package ua.aleh1s.hotelepam.controller.security;

import jakarta.servlet.http.HttpServletRequest;

public interface SecurityManager {
    void init();
    boolean isUserAuthorized(HttpServletRequest request);
}
