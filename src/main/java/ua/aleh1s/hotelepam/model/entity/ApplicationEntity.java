package ua.aleh1s.hotelepam.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationEntity that = (ApplicationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(guests, that.guests) && clazz == that.clazz && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && Objects.equals(createdAt, that.createdAt) && status == that.status && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guests, clazz, checkIn, checkOut, createdAt, status, customerId);
    }

    @Override
    public String toString() {
        return "ApplicationEntity{" +
                "id=" + id +
                ", guests=" + guests +
                ", clazz=" + clazz +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", customerId=" + customerId +
                '}';
    }
}