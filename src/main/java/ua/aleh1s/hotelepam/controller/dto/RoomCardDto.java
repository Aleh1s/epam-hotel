package ua.aleh1s.hotelepam.controller.dto;

public class RoomCardDto {

    private final String roomStatus;
    private final String roomClass;
    private final Integer personsNumber;
    private final Integer bedsNumber;
    private final Integer area;
    private final Double price;

    private RoomCardDto(String roomStatus,
                       String roomClass,
                       Integer personsNumber,
                       Integer bedsNumber,
                       Integer area,
                       Double price) {
        this.roomStatus = roomStatus;
        this.roomClass = roomClass;
        this.personsNumber = personsNumber;
        this.bedsNumber = bedsNumber;
        this.area = area;
        this.price = price;
    }

    public static class Builder {
        private String roomStatus;
        private String roomClass;
        private Integer personsNumber;
        private Integer bedsNumber;
        private Integer area;
        private Double price;

        private Builder() {}

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder roomStatus(String roomStatus) {
            this.roomStatus = roomStatus;
            return this;
        }

        public Builder roomClass(String roomClass) {
            this.roomClass = roomClass;
            return this;
        }

        public Builder personsNumber(Integer personsNumber) {
            this.personsNumber = personsNumber;
            return this;
        }

        public Builder bedsNumber(Integer bedsNumber) {
            this.bedsNumber = bedsNumber;
            return this;
        }

        public Builder area(Integer area) {
            this.area = area;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public RoomCardDto build() {
            return new RoomCardDto(
                    roomStatus, roomClass, personsNumber, bedsNumber, area, price
            );
        }
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public Integer getPersonsNumber() {
        return personsNumber;
    }

    public Integer getBedsNumber() {
        return bedsNumber;
    }

    public Integer getArea() {
        return area;
    }

    public Double getPrice() {
        return price;
    }
}
