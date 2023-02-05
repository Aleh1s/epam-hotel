package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ApplicationSimpleDao;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.transaction.Transaction;

public class ApplicationRepositoryImpl implements ApplicationRepository {

    public static final Logger logger = LogManager.getLogger(ApplicationRepositoryImpl.class);

    @Override
    public void create(ApplicationEntity applicationEntity) {
        ApplicationSimpleDao applicationSimpleDao = new ApplicationSimpleDao();
        try (Transaction transaction = Transaction.start(applicationSimpleDao)) {
            try {
                applicationSimpleDao.save(applicationEntity);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }
}
