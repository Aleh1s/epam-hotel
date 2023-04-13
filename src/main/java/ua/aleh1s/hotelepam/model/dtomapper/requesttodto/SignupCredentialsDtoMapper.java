package ua.aleh1s.hotelepam.model.dtomapper.requesttodto;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.model.dto.SignupCredentials;

import java.util.function.Function;

public class SignupCredentialsDtoMapper implements Function<HttpServletRequest, SignupCredentials> {
    @Override
    public SignupCredentials apply(HttpServletRequest request) {
        return new SignupCredentials(
                request.getParameter("email").trim(),
                request.getParameter("phoneNumber").trim(),
                request.getParameter("firstName").trim(),
                request.getParameter("lastName").trim(),
                request.getParameter("password").trim()
        );
    }
}
