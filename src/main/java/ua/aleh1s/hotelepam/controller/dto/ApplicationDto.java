package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.time.LocalDate;

public class ApplicationDto {

    private final Long id;
    private final Integer guestsNumber;
    private final RoomClass roomClass;
    private final LocalDate entryDate;
    private final LocalDate leavingDate;
    private final ApplicationStatus status;
    private final Long customerId;

    public ApplicationDto (
            Long id,
            Integer guestsNumber,
            RoomClass roomClass,
            LocalDate entryDate,
            LocalDate leavingDate,
            ApplicationStatus status,
            Long customerId) {
        this.id = id;
        this.guestsNumber = guestsNumber;
        this.roomClass = roomClass;
        this.entryDate = entryDate;
        this.leavingDate = leavingDate;
        this.status = status;
        this.customerId = customerId;
    }

    public static class Builder {
        private Long id;
        private Integer guestsNumber;
        private RoomClass roomClass;
        private LocalDate entryDate;
        private LocalDate leavingDate;
        private ApplicationStatus status;
        private Long customerId;

        private Builder() {}

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder guestsNumber(Integer guestsNumber) {
            this.guestsNumber = guestsNumber;
            return this;
        }

        public Builder roomClass(RoomClass roomClass) {
            this.roomClass = roomClass;
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
                    id, guestsNumber, roomClass, entryDate, leavingDate, status, customerId
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getGuestsNumber() {
        return guestsNumber;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
