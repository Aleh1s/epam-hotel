package ua.aleh1s.hotelepam.utils;

import java.util.List;

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
}
