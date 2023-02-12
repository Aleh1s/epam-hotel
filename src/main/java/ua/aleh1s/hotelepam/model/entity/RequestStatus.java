package ua.aleh1s.hotelepam.model.entity;

public enum RequestStatus {

    NEW(1),
    VIEWED(2),
    CLOSED(3);

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
            case 2 -> VIEWED;
            case 3 -> CLOSED;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }
}
