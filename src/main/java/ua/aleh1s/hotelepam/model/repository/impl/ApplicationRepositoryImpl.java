package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.pagination.Pagination;
import ua.aleh1s.hotelepam.model.pagination.impl.ApplicationListPagination;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ApplicationSimpleDao;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ApplicationEntity> getAll(Criteria criteria, Pagination pagination) {
        List<ApplicationEntity> applicationList = new ArrayList<>();
        ApplicationSimpleDao dao = new ApplicationSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                applicationList = dao.getAll(criteria, pagination);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return applicationList;
    }

    @Override
    public Integer count(Criteria criteria) {
        Integer count = 0;
        ApplicationSimpleDao dao = new ApplicationSimpleDao();
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
