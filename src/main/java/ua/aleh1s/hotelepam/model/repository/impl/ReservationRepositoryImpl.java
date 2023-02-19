package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ReservationDAO;
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
        ReservationDAO dao = new ReservationDAO();
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
        ReservationDAO dao = new ReservationDAO();
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
    public Optional<ReservationEntity> getById(Long reservationId) {
        Optional<ReservationEntity> reservationEntity = Optional.empty();
        ReservationDAO dao = new ReservationDAO();
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
        ReservationDAO dao = new ReservationDAO();
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
        ReservationDAO dao = new ReservationDAO();
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
        ReservationDAO dao = new ReservationDAO();
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

    @Override
    public List<ReservationEntity> getActualReservations() {
        List<ReservationEntity> actualReservations = new ArrayList<>();
        ReservationDAO dao = new ReservationDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                actualReservations = dao.getActualReservations();
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return actualReservations;
    }

    @Override
    public List<ReservationEntity> getActualReservationsByRoomNumber(Integer number) {
        List<ReservationEntity> actualReservations = new ArrayList<>();
        ReservationDAO dao = new ReservationDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                actualReservations = dao.getActualReservationsByRoomNumber(number);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return actualReservations;
    }
}
