package ua.aleh1s.hotelepam.model.entity;

public class RequestEntity {

    private Long id;
    private Integer roomNumber;
    private Long managerId;
    private Long customerId;
    private RequestStatus status;

    private RequestEntity(
            Long id,
            Integer roomNumber,
            Long managerId,
            Long customerId,
            RequestStatus status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.managerId = managerId;
        this.customerId = customerId;
        this.status = status;
    }

    public static class Builder {
        private Long id;
        private Integer roomNumber;
        private Long managerId;
        private Long customerId;
        private RequestStatus status;

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

        public Builder managerId(Long managerId) {
            this.managerId = managerId;
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

        public RequestEntity build() {
            return new RequestEntity(
                    id, roomNumber, managerId, customerId, status
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
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
}
