package ua.aleh1s.hotelepam.model.constant;

public enum PaginationParam {

    PAGE_NUMBER("pageNumber");

    private final String value;

    PaginationParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
