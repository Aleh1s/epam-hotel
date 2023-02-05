package ua.aleh1s.hotelepam.model.entity;

import java.time.LocalDate;

public class ApplicationEntity {

    private Long id;
    private Integer guestsNumber;
    private ApartmentClass apartmentClass;
    private LocalDate entryDate;
    private LocalDate leavingDate;
    private ApplicationStatus status;
    private Long customerId;

    public ApplicationEntity(
            Long id,
            Integer guestsNumber,
            ApartmentClass apartmentClass,
            LocalDate entryDate,
            LocalDate leavingDate,
            ApplicationStatus status,
            Long customerId) {
        this.id = id;
        this.guestsNumber = guestsNumber;
        this.apartmentClass = apartmentClass;
        this.entryDate = entryDate;
        this.leavingDate = leavingDate;
        this.status = status;
        this.customerId = customerId;
    }

    public static class Builder {
        private Long id;
        private Integer guestsNumber;
        private ApartmentClass apartmentClass;
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

        public Builder apartmentClass(ApartmentClass apartmentClass) {
            this.apartmentClass = apartmentClass;
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

        public ApplicationEntity build() {
            return new ApplicationEntity(
                    id, guestsNumber, apartmentClass, entryDate, leavingDate, status, customerId
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getGuestsNumber() {
        return guestsNumber;
    }

    public ApartmentClass getApartmentClass() {
        return apartmentClass;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setGuestsNumber(Integer guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public void setApartmentClass(ApartmentClass apartmentClass) {
        this.apartmentClass = apartmentClass;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}