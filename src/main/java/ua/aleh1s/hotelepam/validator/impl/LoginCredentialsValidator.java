package ua.aleh1s.hotelepam.validator.impl;

import ua.aleh1s.hotelepam.model.dto.LoginCredentials;
import ua.aleh1s.hotelepam.validator.Validator;

import java.util.regex.Pattern;

public class LoginCredentialsValidator extends Validator<LoginCredentials> {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    @Override
    public void validate(LoginCredentials credentials) {
        validateEmail(credentials.email());
        validatePassword(credentials.password());
    }

    private void validateEmail(String email) {
        String fieldName = "email";

        if (isNull(email)) {
            rejectValue(fieldName, requiredValueMessage(fieldName));
        } else {
            if (isBlank(email))
                rejectValue(fieldName, emptyValueMessage(fieldName));

            if (!EMAIL_PATTERN.matcher(email).matches())
                rejectValue(fieldName, "Invalid email. Example: 'example@gmail.com'");
        }
    }

    private void validatePassword(String password) {
        String fieldName = "password";

        if (isNull(password)) {
            rejectValue(fieldName, requiredValueMessage(fieldName));
        } else {
            if (isBlank(password))
                rejectValue(fieldName, emptyValueMessage(fieldName));

            if (password.length() < 6 || password.length() > 20)
                rejectValue(fieldName, "Password length should be between 6 and 20");
        }
    }
}

