package ua.aleh1s.hotelepam.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class RequestEntity {

    private Long id;
    private Integer roomNumber;
    private Long customerId;
    private RequestStatus status;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalAmount;

    private RequestEntity(
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

        public RequestEntity build() {
            return new RequestEntity(
                    id, roomNumber, customerId, status, checkIn, checkOut, totalAmount
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestEntity request = (RequestEntity) o;
        return Objects.equals(id, request.id) && Objects.equals(roomNumber, request.roomNumber) && Objects.equals(customerId, request.customerId) && status == request.status && Objects.equals(checkIn, request.checkIn) && Objects.equals(checkOut, request.checkOut) && Objects.equals(totalAmount, request.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, customerId, status, checkIn, checkOut, totalAmount);
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", customerId=" + customerId +
                ", status=" + status +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
