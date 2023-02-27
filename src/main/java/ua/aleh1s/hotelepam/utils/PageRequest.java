package ua.aleh1s.hotelepam.utils;

public class PageRequest {

    private final int pageNumber;
    private final int pageSize;

    private PageRequest(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

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
