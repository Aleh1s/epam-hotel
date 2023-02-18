package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ApplicationDAO;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationRepositoryImpl implements ApplicationRepository {

    public static final Logger logger = LogManager.getLogger(ApplicationRepositoryImpl.class);

    @Override
    public void create(ApplicationEntity applicationEntity) {
        ApplicationDAO applicationSimpleDao = new ApplicationDAO();
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
    public Page<ApplicationEntity> getAllByStatus(ApplicationStatus status, PageRequest pageRequest) {
        Integer count = 0;
        List<ApplicationEntity> applicationList = new ArrayList<>();
        ApplicationDAO dao = new ApplicationDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                applicationList = dao.getAllByStatus(status, pageRequest);
                count = dao.countByStatus(status);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return Page.of(applicationList, count);
    }


    @Override
    public Optional<ApplicationEntity> getById(Long id) {
        Optional<ApplicationEntity> application = Optional.empty();
        ApplicationDAO dao = new ApplicationDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                application = dao.findById(id);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return application;
    }

    @Override
    public void update(ApplicationEntity application) {
        ApplicationDAO dao = new ApplicationDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.update(application);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }
}
