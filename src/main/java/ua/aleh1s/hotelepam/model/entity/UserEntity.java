package ua.aleh1s.hotelepam.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class UserEntity {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Locale locale;
    private UserRole role;
    private BigDecimal account;
}
