package ua.aleh1s.hotelepam.model.entity;

import java.time.ZonedDateTime;
import java.util.Objects;

public class CustomerEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long userId;

    public CustomerEntity(
            Long id,
            String firstName,
            String lastName,
            String phoneNumber,
            Long userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private Long userId;

        private Builder() {}

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

       public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
       }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public CustomerEntity build() {
            return new CustomerEntity(id, firstName, lastName, phoneNumber, userId);
        }
    }

    public Long getId() {
        return id;
    }

    public CustomerEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public CustomerEntity setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerEntity that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(phoneNumber, that.phoneNumber)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", userId=" + userId +
                '}';
    }
}
