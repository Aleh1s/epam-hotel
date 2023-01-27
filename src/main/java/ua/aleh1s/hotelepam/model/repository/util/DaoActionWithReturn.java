package ua.aleh1s.hotelepam.model.repository.util;

import ua.aleh1s.hotelepam.model.dao.exception.DaoException;

@FunctionalInterface
public interface DaoActionWithReturn<T, D> {
    T execute(D dao) throws DaoException;
}