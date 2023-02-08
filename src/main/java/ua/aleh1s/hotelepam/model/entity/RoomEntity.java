package ua.aleh1s.hotelepam.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class RoomEntity {
    private Integer roomNumber;
    private RoomClass roomClass;
    private RoomStatus status;
    private String description;
    private LocalDate busyUntil;
    private BigDecimal price;
    private String name;
    private String[] attributes;
    private Integer bedsNumber;
    private Integer personsNumber;
    private Integer area;

    public RoomEntity(
            Integer roomNumber,
            RoomClass roomClass,
            RoomStatus status,
            String description,
            LocalDate busyUntil,
            BigDecimal price,
            String name,
            String[] attributes,
            Integer bedsNumber,
            Integer personsNumber,
            Integer area) {
        this.roomNumber = roomNumber;
        this.roomClass = roomClass;
        this.status = status;
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
        private RoomClass roomClass;
        private RoomStatus status;
        private String description;
        private LocalDate busyUntil;
        private BigDecimal price;
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

        public Builder roomClass(RoomClass roomClass) {
            this.roomClass = roomClass;
            return this;
        }

        public Builder status(RoomStatus status) {
            this.status = status;
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

        public Builder price(BigDecimal price) {
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

        public RoomEntity build() {
            return new RoomEntity(
                    roomNumber, roomClass, status, description, busyUntil, price, name, attributes, bedsNumber, personsNumber, area
            );
        }
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBusyUntil() {
        return busyUntil;
    }

    public void setBusyUntil(LocalDate busyUntil) {
        this.busyUntil = busyUntil;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public Integer getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(Integer bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    public Integer getPersonsNumber() {
        return personsNumber;
    }

    public void setPersonsNumber(Integer personsNumber) {
        this.personsNumber = personsNumber;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return Objects.equals(roomNumber, that.roomNumber) && roomClass == that.roomClass && status == that.status && Objects.equals(description, that.description) && Objects.equals(busyUntil, that.busyUntil) && Objects.equals(price, that.price) && Objects.equals(name, that.name) && Arrays.equals(attributes, that.attributes) && Objects.equals(bedsNumber, that.bedsNumber) && Objects.equals(personsNumber, that.personsNumber) && Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(roomNumber, roomClass, status, description, busyUntil, price, name, bedsNumber, personsNumber, area);
        result = 31 * result + Arrays.hashCode(attributes);
        return result;
    }
}
