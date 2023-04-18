package ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.model.dto.LoginCredentials;

import java.util.function.Function;

public class LoginCredentialsDtoMapper implements Function<HttpServletRequest, LoginCredentials> {
    @Override
    public LoginCredentials apply(HttpServletRequest request) {
        return new LoginCredentials(
                request.getParameter("email"),
                request.getParameter("password")
        );
    }
}
