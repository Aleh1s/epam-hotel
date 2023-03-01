package ua.aleh1s.hotelepam.model.entity;

public enum ReservationStatus {

    PENDING_CONFIRMATION(1),
    PENDING_PAYMENT(2),
    PAYED(3),
    CANCELED(4);
    private final int index;

    ReservationStatus(int index) {
        this.index = index;
    }

    public static ReservationStatus atIndex(int index) {
        return switch (index) {
            case 1 -> PENDING_CONFIRMATION;
            case 2 -> PENDING_PAYMENT;
            case 3 -> PAYED;
            case 4 -> CANCELED;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public int getIndex() {
        return index;
    }
}
