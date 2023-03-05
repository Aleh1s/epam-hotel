package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ApplicationDto {

    private final Long id;
    private final Integer guests;
    private final RoomClass roomClass;
    private final Date checkIn;
    private final Date checkOut;
    private final LocalDateTime createdAt;
    private final ApplicationStatus status;
    private final Long customerId;

    public ApplicationDto(
            Long id,
            Integer guests,
            RoomClass roomClass,
            Date checkIn,
            Date checkOut,
            LocalDateTime createdAt,
            ApplicationStatus status,
            Long customerId) {
        this.id = id;
        this.guests = guests;
        this.roomClass = roomClass;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.createdAt = createdAt;
        this.status = status;
        this.customerId = customerId;
    }

    public static class Builder {
        private Long id;
        private Integer guests;
        private RoomClass roomClass;
        private Date checkIn;
        private Date checkOut;
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

        public Builder roomClass(RoomClass roomClass) {
            this.roomClass = roomClass;
            return this;
        }

        public Builder checkIn(Date checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public Builder checkOut(Date checkOut) {
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

        public ApplicationDto build() {
            return new ApplicationDto(
                    id, guests, roomClass, checkIn, checkOut, createdAt, status, customerId
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getGuests() {
        return guests;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
