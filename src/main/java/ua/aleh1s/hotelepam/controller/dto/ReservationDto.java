package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDto {

    private final Long id;
    private final Integer roomNumber;
    private final Long customerId;
    private final LocalDate entryDate;
    private final LocalDate leavingDate;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiredAt;
    private final LocalDateTime payedAt;
    private final BigDecimal totalAmount;
    private final ReservationStatus status;

    private ReservationDto(
            Long id,
            Integer roomNumber,
            Long customerId,
            LocalDate entryDate,
            LocalDate leavingDate,
            LocalDateTime createdAt,
            LocalDateTime expiredAt,
            LocalDateTime payedAt,
            BigDecimal totalAmount,
            ReservationStatus status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.entryDate = entryDate;
        this.leavingDate = leavingDate;
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
        private LocalDate entryDate;
        private LocalDate leavingDate;
        private LocalDateTime createdAt;
        private LocalDateTime expiredAt;
        private LocalDateTime payedAt;
        private BigDecimal totalAmount;
        private ReservationStatus status;

        private Builder() {
        }

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

        public Builder status(ReservationStatus status) {
            this.status = status;
            return this;
        }

        public ReservationDto build() {
            return new ReservationDto(
                    id, roomNumber, customerId, entryDate, leavingDate, createdAt, expiredAt, payedAt, totalAmount, status
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public LocalDateTime getPayedAt() {
        return payedAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
