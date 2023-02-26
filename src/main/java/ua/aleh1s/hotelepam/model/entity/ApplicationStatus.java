package ua.aleh1s.hotelepam.model.entity;

public enum ApplicationStatus {

    NEW(1),
    PROCESSED(2);

    private final int index;

    ApplicationStatus(int index) {
        this.index = index;
    }

    public static ApplicationStatus atIndex(int index) {
        return switch (index) {
            case 1 -> NEW;
            case 2 -> PROCESSED;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
