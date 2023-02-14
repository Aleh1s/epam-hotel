package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ReservationSimpleDao;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.pagination.Pagination;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

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
    public List<ReservationEntity> getAllByRoomNumberAndStatus(Integer roomNumber, ReservationStatus status) {
        List<ReservationEntity> reservationEntities = new ArrayList<>();
        ReservationSimpleDao dao = new ReservationSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                reservationEntities = dao.getAllByRoomNumberAndStatus(roomNumber, status);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return reservationEntities;
    }

    @Override
    public List<ReservationEntity> getAll(Criteria criteria, Pagination pagination) {
        List<ReservationEntity> reservationEntities = new ArrayList<>();
        ReservationSimpleDao dao = new ReservationSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                reservationEntities = dao.getAll(criteria, pagination);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return reservationEntities;
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
}
