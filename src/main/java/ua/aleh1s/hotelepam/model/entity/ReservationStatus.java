package ua.aleh1s.hotelepam.model.entity;

public enum ReservationStatus {

    PENDING(1),
    CONFIRMED(2),
    CANCELLED(3),
    PAYED(4),
    EXPIRED(5);

    private final int index;

    ReservationStatus(int index) {
        this.index = index;
    }

    public static ReservationStatus atIndex(int index) {
        return switch (index) {
            case 1 -> PENDING;
            case 2 -> CONFIRMED;
            case 3 -> CANCELLED;
            case 4 -> EXPIRED;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public int getIndex() {
        return index;
    }
}
