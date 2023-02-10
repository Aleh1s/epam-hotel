package ua.aleh1s.hotelepam.model.constant;

public enum RoomListParam {

    PRICE_FROM("priceFrom"),
    PRICE_TO("priceTo"),
    PERSONS_FROM("personsFrom"),
    PERSONS_TO("personsTo"),
    STANDARD("standard"),
    SUPERIOR("superior"),
    FAMILY("family"),
    BUSINESS("business"),
    PRESIDENT("president"),
    FREE("free"),
    BUSY("busy"),
    BOOKED("booked"),
    UNAVAILABLE("unavailable");

    private final String value;

    RoomListParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
