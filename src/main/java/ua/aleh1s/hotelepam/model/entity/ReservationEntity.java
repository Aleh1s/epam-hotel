package ua.aleh1s.hotelepam.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationEntity {

    private Long id;
    private Integer roomNumber;
    private Long customerId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime payedAt;
    private BigDecimal totalAmount;
    private ReservationStatus status;

    private ReservationEntity(
            Long id,
            Integer roomNumber,
            Long customerId,
            LocalDate checkIn,
            LocalDate checkOut,
            LocalDateTime createdAt,
            LocalDateTime expiredAt,
            LocalDateTime payedAt,
            BigDecimal totalAmount,
            ReservationStatus status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.payedAt = payedAt;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public static class Builder {
        private Long id;
        private Integer roomNumber;
        private Long customerId;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private LocalDateTime createdAt;
        private LocalDateTime expiredAt;
        private LocalDateTime payedAt;
        private BigDecimal totalAmount;
        private ReservationStatus status;
        private Builder() {}

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder roomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
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

        public Builder expiredAt(LocalDateTime expiredAt) {
            this.expiredAt = expiredAt;
            return this;
        }

        public Builder payedAt(LocalDateTime payedAt) {
            this.payedAt = payedAt;
            return this;
        }

        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder status(ReservationStatus status) {
            this.status = status;
            return this;
        }

        public ReservationEntity build() {
            return new ReservationEntity(
                    id, roomNumber, customerId, checkIn, checkOut, createdAt, expiredAt, payedAt, totalAmount, status
            );
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public LocalDateTime getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(LocalDateTime payedAt) {
        this.payedAt = payedAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
