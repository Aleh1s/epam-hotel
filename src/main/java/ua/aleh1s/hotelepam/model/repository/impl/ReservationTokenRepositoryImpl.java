package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ReservationTokenDAO;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationTokenRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.Optional;

public class ReservationTokenRepositoryImpl implements ReservationTokenRepository {

    private static final Logger logger = LogManager.getLogger(ReservationTokenRepositoryImpl.class);

    @Override
    public void create(ReservationTokenEntity reservationToken) {
        ReservationTokenDAO dao = new ReservationTokenDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.save(reservationToken);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Optional<ReservationTokenEntity> findById(String tokenId) {
        Optional<ReservationTokenEntity> reservationToken = Optional.empty();
        ReservationTokenDAO dao = new ReservationTokenDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                reservationToken = dao.findById(tokenId);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return reservationToken;
    }

    @Override
    public void updateConfirmedAt(ReservationTokenEntity reservationToken) {
        ReservationTokenDAO dao = new ReservationTokenDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.updateConfirmedAt(reservationToken);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }
}
