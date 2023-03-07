package ua.aleh1s.hotelepam.utils;

public record PageRequest(int pageNumber, int pageSize) {

    public static PageRequest of(int pageNumber, int pageSize) {
        return new PageRequest(pageNumber, pageSize);
    }

    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}
