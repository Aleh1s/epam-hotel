package ua.aleh1s.hotelepam.model.entity;

import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;

public class UserEntity {
    private Long id;
    private String login;
    private String password;
    private ZoneId timezone;
    private Locale locale;
    private UserRole role;

    public UserEntity(
            Long id,
            String login,
            String password,
            ZoneId timezone,
            Locale locale,
            UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.timezone = timezone;
        this.locale = locale;
        this.role = role;
    }

    public static class Builder {
        private Long id;
        private String login;
        private String password;
        private ZoneId timezone;
        private Locale locale;
        private UserRole role;

        private Builder() {}

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder timezone(ZoneId timezone) {
            this.timezone = timezone;
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

        public UserEntity build() {
            return new UserEntity(id, login, password, timezone, locale, role);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public void setTimezone(ZoneId timezone) {
        this.timezone = timezone;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(login, that.login)) return false;
        if (!Objects.equals(password, that.password)) return false;
        if (!Objects.equals(timezone, that.timezone)) return false;
        if (!Objects.equals(locale, that.locale)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserEntity{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", timezone=").append(timezone);
        sb.append(", locale=").append(locale);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
