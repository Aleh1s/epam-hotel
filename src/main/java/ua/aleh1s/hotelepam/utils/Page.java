package ua.aleh1s.hotelepam.utils;

import java.util.List;

public record Page<T>(List<T> result, long count) {

    public static <T> Page<T> of(List<T> result, long count) {
        return new Page<>(result, count);
    }
}