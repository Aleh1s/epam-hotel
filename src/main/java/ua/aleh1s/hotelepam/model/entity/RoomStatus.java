package ua.aleh1s.hotelepam.model.entity;

public enum RoomStatus {
    FREE(1),
    BOOKED(2),
    BUSY(3),
    UNAVAILABLE(4);

    private final int value;

    RoomStatus(int value) {
        this.value = value;
    }

    public int getIndex() {
        return value;
    }

    public static RoomStatus atIndex(int index) {
        return switch (index) {
            case 1 -> FREE;
            case 2 -> BOOKED;
            case 3 -> BUSY;
            case 4 -> UNAVAILABLE;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }
}
