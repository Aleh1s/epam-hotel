package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ReservationSimpleDao;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRepositoryImpl implements ReservationRepository {

    private static final Logger logger = LogManager.getLogger(ReservationRepositoryImpl.class);

    @Override
    public void create(ReservationEntity entity) {
        ReservationSimpleDao dao = new ReservationSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.save(entity);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Page<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest) {
        List<ReservationEntity> result = new ArrayList<>();
        Integer count = 0;
        ReservationSimpleDao dao = new ReservationSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                result = dao.getAllByStatus(status, pageRequest);
                count = dao.countByStatus(status);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return Page.of(result, count);
    }

    @Override
    public Integer count(Criteria criteria) {
        Integer count = 0;
        ReservationSimpleDao dao = new ReservationSimpleDao();
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
    public Optional<ReservationEntity> getById(Long reservationId) {
        Optional<ReservationEntity> reservationEntity = Optional.empty();
        ReservationSimpleDao dao = new ReservationSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                reservationEntity = dao.findById(reservationId);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return reservationEntity;
    }

    @Override
    public void update(ReservationEntity reservation) {
        ReservationSimpleDao dao = new ReservationSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.update(reservation);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<ReservationEntity> getAllByCustomerId(Long userId) {
        ReservationSimpleDao dao = new ReservationSimpleDao();
        List<ReservationEntity> result = new ArrayList<>();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                result = dao.getAllByCustomerId(userId);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return result;
    }

    @Override
    public Page<ReservationEntity> getAll(PageRequest pageRequest) {
        ReservationSimpleDao dao = new ReservationSimpleDao();
        Integer count = 0;
        List<ReservationEntity> result = new ArrayList<>();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                result = dao.getAll(pageRequest);
                count = dao.count();
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return Page.of(result, count);
    }
}
