package ua.aleh1s.hotelepam.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationEntity {

    private Long id;
    private Integer roomNumber;
    private Long customerId;
    private LocalDate entryDate;
    private LocalDate leavingDate;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime payedAt;
    private BigDecimal totalAmount;

    private ReservationEntity(
            Long id,
            Integer roomNumber,
            Long customerId,
            LocalDate entryDate,
            LocalDate leavingDate,
            LocalDateTime createdAt,
            LocalDateTime expiredAt,
            LocalDateTime payedAt,
            BigDecimal totalAmount) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.entryDate = entryDate;
        this.leavingDate = leavingDate;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.payedAt = payedAt;
        this.totalAmount = totalAmount;
    }

    public static class Builder {
        private Long id;
        private Integer roomNumber;
        private Long customerId;
        private LocalDate entryDate;
        private LocalDate leavingDate;
        private LocalDateTime createdAt;
        private LocalDateTime expiredAt;
        private LocalDateTime payedAt;
        private BigDecimal totalAmount;

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

        public Builder entryDate(LocalDate entryDate) {
            this.entryDate = entryDate;
            return this;
        }

        public Builder leavingDate(LocalDate leavingDate) {
            this.leavingDate = leavingDate;
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

        public ReservationEntity build() {
            return new ReservationEntity(
                    id, roomNumber, customerId, entryDate, leavingDate, createdAt, expiredAt, payedAt, totalAmount
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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
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
}
