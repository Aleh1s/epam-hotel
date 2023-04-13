package ua.aleh1s.hotelepam.validator.impl;

import ua.aleh1s.hotelepam.model.dto.SignupCredentials;
import ua.aleh1s.hotelepam.validator.Validator;

import java.util.regex.Pattern;

public class SignupCredentialsValidator extends Validator<SignupCredentials> {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    private static final Pattern PHONE_NUMBER_PATTERN =
            Pattern.compile("^\\+380\\d{9}$");

    public void validate(SignupCredentials credentials) {
        validateEmail(credentials.email());
        validatePhoneNumber(credentials.phoneNumber());
        validateFirstName(credentials.firstName());
        validateLastName(credentials.lastName());
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

    private void validatePhoneNumber(String phoneNumber) {
        String fieldName = "phoneNumber";

        if (isNull(phoneNumber)) {
            rejectValue(fieldName, requiredValueMessage(fieldName));
        } else {
            if (isBlank(phoneNumber))
                rejectValue(fieldName, emptyValueMessage(fieldName));

            if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches())
                rejectValue(fieldName, "Invalid phone number. Example '+380-XX-XXX-XXXX'");
        }
    }

    private void validateFirstName(String firstName) {
        String fieldName = "firstName";

        if (isNull(firstName)) {
            rejectValue(fieldName, requiredValueMessage(fieldName));
        } else {
            if (isBlank(firstName))
                rejectValue(fieldName, emptyValueMessage(fieldName));

            if (firstName.length() < 2 || firstName.length() > 50)
                rejectValue(fieldName, "First name length should be between 2 and 50");
        }
    }

    private void validateLastName(String lastName) {
        String fieldName = "lastName";

        if (isNull(lastName)) {
            rejectValue(fieldName, requiredValueMessage(fieldName));
        } else {
            if (isBlank(lastName))
                rejectValue(fieldName, emptyValueMessage(fieldName));

            if (lastName.length() < 2 || lastName.length() > 50)
                rejectValue(fieldName, "Last name length should be between 2 and 50");
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
                rejectValue(fieldName, "Password length should be between 2 and 50");
        }
    }
}
