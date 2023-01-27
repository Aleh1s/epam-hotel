package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.Gender;
import ua.aleh1s.hotelepam.model.entity.UserRole;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

public class CustomerDto {

    private final String email;
    private final String firstName;
    private final String lastName;
    private final String country;
    private final ZonedDateTime dateOfBirth;
    private final Gender gender;
    private final ZoneId timezone;
    private final Locale locale;
    private final UserRole role;

    private CustomerDto(
            String email,
            String firstName,
            String lastName,
            String country,
            ZonedDateTime dateOfBirth,
            Gender gender,
            ZoneId timezone,
            Locale locale,
            UserRole role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.timezone = timezone;
        this.locale = locale;
        this.role = role;
    }

    public static class Builder {
        private String email;
        private String firstName;
        private String lastName;
        private String country;
        private ZonedDateTime dateOfBirth;
        private Gender gender;
        private ZoneId timezone;
        private Locale locale;
        private UserRole role;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder timezone(ZoneId zoneId) {
            this.timezone = zoneId;
            return this;
        }

        public Builder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder dateOfBirth(ZonedDateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public CustomerDto build() {
            return new CustomerDto(email, firstName, lastName, country, dateOfBirth, gender, timezone, locale, role);
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public Locale getLocale() {
        return locale;
    }

    public UserRole getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }
}
