package ua.aleh1s.hotelepam.model.queryspecification;

public enum SqlBase {
    SELECT("select * from"),
    COUNT("select count(*) from");

    private final String value;

    SqlBase(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
