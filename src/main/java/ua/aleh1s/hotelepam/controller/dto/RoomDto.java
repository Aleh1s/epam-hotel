package ua.aleh1s.hotelepam.controller.dto;

import java.time.LocalDate;

public class RoomDto {

    private final Integer roomNumber;
    private final String roomClass;
    private final String roomStatus;
    private final String description;
    private final LocalDate busyUntil;
    private final Double price;
    private final String name;
    private final String[] attributes;
    private final Integer bedsNumber;
    private final Integer personsNumber;
    private final Integer area;

    private RoomDto(Integer roomNumber,
                   String roomClass,
                   String roomStatus,
                   String description,
                   LocalDate busyUntil,
                   Double price,
                   String name,
                   String[] attributes,
                   Integer bedsNumber,
                   Integer personsNumber,
                   Integer area) {
        this.roomNumber = roomNumber;
        this.roomClass = roomClass;
        this.roomStatus = roomStatus;
        this.description = description;
        this.busyUntil = busyUntil;
        this.price = price;
        this.name = name;
        this.attributes = attributes;
        this.bedsNumber = bedsNumber;
        this.personsNumber = personsNumber;
        this.area = area;
    }

    public static class Builder {
        private Integer roomNumber;
        private String roomClass;
        private String roomStatus;
        private String description;
        private LocalDate busyUntil;
        private Double price;
        private String name;
        private String[] attributes;
        private Integer bedsNumber;
        private Integer personsNumber;
        private Integer area;

        private Builder() {}

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder roomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder roomClass(String roomClass) {
            this.roomClass = roomClass;
            return this;
        }

        public Builder roomStatus(String roomStatus) {
            this.roomStatus = roomStatus;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder busyUntil(LocalDate busyUntil) {
            this.busyUntil = busyUntil;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder attributes(String[] attributes) {
            this.attributes = attributes;
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

        public Builder area(Integer area) {
            this.area = area;
            return this;
        }

        public RoomDto build() {
            return new RoomDto(
                    roomNumber, roomClass, roomStatus, description, busyUntil,
                    price, name, attributes, bedsNumber, personsNumber, area
            );
        }
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getBusyUntil() {
        return busyUntil;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public Integer getBedsNumber() {
        return bedsNumber;
    }

    public Integer getPersonsNumber() {
        return personsNumber;
    }

    public Integer getArea() {
        return area;
    }
}
