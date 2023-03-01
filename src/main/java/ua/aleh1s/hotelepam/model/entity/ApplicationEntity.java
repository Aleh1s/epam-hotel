package ua.aleh1s.hotelepam.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApplicationEntity {

    private Long id;
    private Integer guests;
    private RoomClass clazz;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDateTime createdAt;
    private ApplicationStatus status;
    private Long customerId;

    public ApplicationEntity(
            Long id,
            Integer guests,
            RoomClass clazz,
            LocalDate checkIn,
            LocalDate checkOut,
            LocalDateTime createdAt,
            ApplicationStatus status,
            Long customerId) {
        this.id = id;
        this.guests = guests;
        this.clazz = clazz;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.createdAt = createdAt;
        this.status = status;
        this.customerId = customerId;
    }

    public static class Builder {
        private Long id;
        private Integer guests;
        private RoomClass clazz;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private LocalDateTime createdAt;
        private ApplicationStatus status;
        private Long customerId;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder guests(Integer guests) {
            this.guests = guests;
            return this;
        }

        public Builder clazz(RoomClass clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder checkIn(LocalDate checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public Builder checkOut(LocalDate checkOut) {
            this.checkOut = checkOut;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder status(ApplicationStatus status) {
            this.status = status;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public ApplicationEntity build() {
            return new ApplicationEntity(
                    id, guests, clazz, checkIn, checkOut, createdAt, status, customerId
            );
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public RoomClass getClazz() {
        return clazz;
    }

    public void setClazz(RoomClass clazz) {
        this.clazz = clazz;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}