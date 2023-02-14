package ua.aleh1s.hotelepam.controller.dto;

import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookInfoDto {
    private final Integer roomNumber;
    private final String roomName;
    private final RoomClass roomClass;
    private final LocalDate entryDate;
    private final LocalDate leavingDate;
    private final Integer bedsNumber;
    private final Integer personsNumber;
    private final BigDecimal totalAmount;

    private BookInfoDto(
            Integer roomNumber,
            String roomName,
            RoomClass roomClass,
            LocalDate entryDate,
            LocalDate leavingDate,
            Integer bedsNumber,
            Integer personsNumber,
            BigDecimal totalAmount) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.roomClass = roomClass;
        this.entryDate = entryDate;
        this.leavingDate = leavingDate;
        this.bedsNumber = bedsNumber;
        this.personsNumber = personsNumber;
        this.totalAmount = totalAmount;
    }

    public static class Builder {
        private Integer roomNumber;
        private String roomName;
        private RoomClass roomClass;
        private LocalDate entryDate;
        private LocalDate leavingDate;
        private Integer bedsNumber;
        private Integer personsNumber;
        private BigDecimal totalAmount;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder roomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder roomName(String roomName) {
            this.roomName = roomName;
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

        public Builder bedsNumber(Integer bedsNumber) {
            this.bedsNumber = bedsNumber;
            return this;
        }

        public Builder personsNumber(Integer personsNumber) {
            this.personsNumber = personsNumber;
            return this;
        }

        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public BookInfoDto build() {
            return new BookInfoDto(
                    roomNumber, roomName, roomClass, entryDate, leavingDate, bedsNumber, personsNumber, totalAmount
            );
        }
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getRoomName() {
        return roomName;
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

    public Integer getBedsNumber() {
        return bedsNumber;
    }

    public Integer getPersonsNumber() {
        return personsNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
