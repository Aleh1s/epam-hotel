package ua.aleh1s.hotelepam.model.dto;

public record SignupCredentials(
        String email,
        String phoneNumber,
        String firstName,
        String lastName,
        String password
) {}