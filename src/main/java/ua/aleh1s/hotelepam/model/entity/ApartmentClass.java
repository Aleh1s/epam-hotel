package ua.aleh1s.hotelepam.model.entity;

public enum ApartmentClass {

    STANDARD(1),
    SUPERIOR(2),
    FAMILY(3),
    BUSINESS(4),
    PRESIDENT(5);

    private final int index;

    ApartmentClass(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
