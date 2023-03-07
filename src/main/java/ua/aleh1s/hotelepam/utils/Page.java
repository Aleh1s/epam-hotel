package ua.aleh1s.hotelepam.utils;

import java.util.List;
import java.util.Objects;

public class Page<T> {

    private final List<T> result;
    private final long count;

    private Page(List<T> result, long count) {
        this.result = result;
        this.count = count;
    }

    public static <T> Page<T> of(List<T> result, long count) {
        return new Page<>(result, count);
    }

    public List<T> getResult() {
        return result;
    }
    public long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page<?> page = (Page<?>) o;
        return count == page.count && Objects.equals(result, page.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, count);
    }

    @Override
    public String toString() {
        return "Page{" +
                "result=" + result +
                ", count=" + count +
                '}';
    }
}
