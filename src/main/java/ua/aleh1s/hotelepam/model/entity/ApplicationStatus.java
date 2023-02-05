package ua.aleh1s.hotelepam.model.entity;

public enum ApplicationStatus {

    NEW(1),
    VIEWED(2),
    CLOSED(3);

    private final int index;

    ApplicationStatus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
