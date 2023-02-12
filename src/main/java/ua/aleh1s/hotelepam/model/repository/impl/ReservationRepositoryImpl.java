package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ReservationSimpleDao;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

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
}
