package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.RequestSimpleDao;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

public class RequestRepositoryImpl implements RequestRepository {

    private static final Logger logger = LogManager.getLogger(RequestRepositoryImpl.class);

    @Override
    public void create(RequestEntity request) {
        RequestSimpleDao dao = new RequestSimpleDao();
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
}
