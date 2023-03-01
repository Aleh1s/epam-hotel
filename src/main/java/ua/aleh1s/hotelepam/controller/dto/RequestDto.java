package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.RequestStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RequestDto {

    private final Long id;
    private final Integer roomNumber;
    private final Long customerId;
    private final RequestStatus status;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final BigDecimal totalAmount;

    private RequestDto(
            Long id,
            Integer roomNumber,
            Long customerId,
            RequestStatus status,
            LocalDate checkIn,
            LocalDate checkOut,
            BigDecimal totalAmount) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.status = status;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalAmount = totalAmount;
    }

    public static class Builder {
        private Long id;
        private Integer roomNumber;
        private Long customerId;
        private RequestStatus status;
        private LocalDate checkIn;
        private LocalDate checkOut;
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

        public Builder status(RequestStatus status) {
            this.status = status;
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

        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public RequestDto build() {
            return new RequestDto(
                    id, roomNumber, customerId, status, checkIn, checkOut, totalAmount
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

    public RequestStatus getStatus() {
        return status;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
