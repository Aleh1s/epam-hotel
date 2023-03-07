package ua.aleh1s.hotelepam.model.dto;

import ua.aleh1s.hotelepam.model.entity.UserRole;

import java.math.BigDecimal;
import java.util.Locale;

public record UserDto (
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        Locale locale,
        UserRole role,
        BigDecimal account
) { }
