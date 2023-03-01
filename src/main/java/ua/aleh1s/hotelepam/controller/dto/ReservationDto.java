package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.math.BigDecimal;
import java.util.Date;

public class ReservationDto {

    private final Long id;
    private final Integer roomNumber;
    private final Long customerId;
    private final Date checkIn;
    private final Date checkOut;
    private final Date createdAt;
    private final Date expiredAt;
    private final Date payedAt;
    private final BigDecimal totalAmount;
    private final ReservationStatus status;

    private ReservationDto(
            Long id,
            Integer roomNumber,
            Long customerId,
            Date checkIn,
            Date checkOut,
            Date createdAt,
            Date expiredAt,
            Date payedAt,
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
        private Date checkIn;
        private Date checkOut;
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

        public Builder checkIn(Date entryDate) {
            this.checkIn = entryDate;
            return this;
        }

        public Builder checkOut(Date leavingDate) {
            this.checkOut = leavingDate;
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
                    id, roomNumber, customerId, checkIn, checkOut, createdAt, expiredAt, payedAt, totalAmount, status
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

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
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

