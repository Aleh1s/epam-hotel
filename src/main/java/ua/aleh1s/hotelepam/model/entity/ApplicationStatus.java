package ua.aleh1s.hotelepam.model.entity;

public enum ApplicationStatus {

    NEW(1),
    VIEWED(2),
    CLOSED(3);

    private final int index;

    ApplicationStatus(int index) {
        this.index = index;
    }

    public static ApplicationStatus atIndex(int index) {
        return switch (index) {
            case 1 -> NEW;
            case 2 -> VIEWED;
            case 3 -> CLOSED;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public int getIndex() {
        return index;
    }
}
