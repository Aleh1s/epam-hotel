package ua.aleh1s.hotelepam.model.entity;

public enum RoomClass {

    STANDARD(1),
    SUPERIOR(2),
    FAMILY(3),
    BUSINESS(4),
    PRESIDENT(5);

    private final int index;

    RoomClass(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static RoomClass atIndex(int index) {
        return switch (index) {
            case 1 -> STANDARD;
            case 2 -> SUPERIOR;
            case 3 -> FAMILY;
            case 4 -> BUSINESS;
            case 5 -> PRESIDENT;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }
}
