package ua.aleh1s.hotelepam.model.pagination;

import java.util.List;

public class Page<T> {

    private final List<T> result;
    private final Integer count;

    private Page(List<T> result, Integer count) {
        this.result = result;
        this.count = count;
    }

    public static <T> Page<T> of(List<T> result, Integer count) {
        return new Page<>(result, count);
    }

    public List<T> getResult() {
        return result;
    }


    public Integer getCount() {
        return count;
    }
}
