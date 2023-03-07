package ua.aleh1s.hotelepam.utils;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageRequest that = (PageRequest) o;
        return pageNumber == that.pageNumber && pageSize == that.pageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize);
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
