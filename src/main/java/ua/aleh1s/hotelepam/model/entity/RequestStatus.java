package ua.aleh1s.hotelepam.model.entity;

public enum RequestStatus {

    NEW(1),
    REJECTED(2),
    CONFIRMED(3);

    private final int index;

    RequestStatus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static RequestStatus atIndex(int index) {
        return switch (index) {
            case 1 -> NEW;
            case 2 -> REJECTED;
            case 3 -> CONFIRMED;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }
}
