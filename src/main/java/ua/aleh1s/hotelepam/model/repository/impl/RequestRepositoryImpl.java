package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.RequestDAO;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestRepositoryImpl implements RequestRepository {

    private static final Logger logger = LogManager.getLogger(RequestRepositoryImpl.class);

    @Override
    public void create(RequestEntity request) {
        RequestDAO dao = new RequestDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.save(request);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<RequestEntity> getAll(Criteria criteria, Pagination pagination) {
        List<RequestEntity> requestEntityList = new ArrayList<>();
        RequestDAO dao = new RequestDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                requestEntityList = dao.getAll(criteria, pagination);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return requestEntityList;
    }

    @Override
    public Integer count(Criteria criteria) {
        Integer count = 0;
        RequestDAO dao = new RequestDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                count = dao.count(criteria);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return count;
    }

    @Override
    public Optional<RequestEntity> getById(Long requestId) {
        Optional<RequestEntity> requestEntity = Optional.empty();
        RequestDAO dao = new RequestDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                requestEntity = dao.findById(requestId);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return requestEntity;
    }

    @Override
    public void update(RequestEntity requestEntity) {
        RequestDAO dao = new RequestDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.update(requestEntity);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }
}
