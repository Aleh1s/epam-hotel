package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.RequestStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RequestDto {

    private final Long id;
    private final Integer roomNumber;
    private final Long customerId;
    private final RequestStatus status;
    private final LocalDate entryDate;
    private final LocalDate leavingDate;
    private final BigDecimal totalAmount;

    private RequestDto(
            Long id,
            Integer roomNumber,
            Long customerId,
            RequestStatus status,
            LocalDate entryDate,
            LocalDate leavingDate,
            BigDecimal totalAmount) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.status = status;
        this.entryDate = entryDate;
        this.leavingDate = leavingDate;
        this.totalAmount = totalAmount;
    }

    public static class Builder {
        private Long id;
        private Integer roomNumber;
        private Long customerId;
        private RequestStatus status;
        private LocalDate entryDate;
        private LocalDate leavingDate;
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

        public Builder entryDate(LocalDate entryDate) {
            this.entryDate = entryDate;
            return this;
        }

        public Builder leavingDate(LocalDate leavingDate) {
            this.leavingDate = leavingDate;
            return this;
        }

        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public RequestDto build() {
            return new RequestDto(
                    id, roomNumber, customerId, status, entryDate, leavingDate, totalAmount
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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
