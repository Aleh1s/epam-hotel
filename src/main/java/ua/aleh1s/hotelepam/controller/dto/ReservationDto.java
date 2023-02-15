package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ReservationDto {

    private final Long id;
    private final Integer roomNumber;
    private final Long customerId;
    private final Date entryDate;
    private final Date leavingDate;
    private final Date createdAt;
    private final Date expiredAt;
    private final Date payedAt;
    private final BigDecimal totalAmount;
    private final ReservationStatus status;

    private ReservationDto(
            Long id,
            Integer roomNumber,
            Long customerId,
            Date entryDate,
            Date leavingDate,
            Date createdAt,
            Date expiredAt,
            Date payedAt,
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
        private Date entryDate;
        private Date leavingDate;
        private Date createdAt;
        private Date expiredAt;
        private Date payedAt;
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

        public Builder entryDate(Date entryDate) {
            this.entryDate = entryDate;
            return this;
        }

        public Builder leavingDate(Date leavingDate) {
            this.leavingDate = leavingDate;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder expiredAt(Date expiredAt) {
            this.expiredAt = expiredAt;
            return this;
        }

        public Builder payedAt(Date payedAt) {
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

    public Date getEntryDate() {
        return entryDate;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public Date getPayedAt() {
        return payedAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
