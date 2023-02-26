package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.constant.SqlColumn;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.queryspecification.QuerySpecification;
import ua.aleh1s.hotelepam.model.queryspecification.SqlBase;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.ApplicationDAO;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.querybuilder.specification.where.WhereSpecification;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlApplicationEntityMapper;
import ua.aleh1s.hotelepam.querybuilder.Root;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    @Override
    public Page<ApplicationEntity> getAllByApplicationStatus(ApplicationStatus status, PageRequest pageRequest) {
        QuerySpecification sqs = QuerySpecification.newSpecification(SqlBase.SELECT, "application");
        sqs.where(sqs.eq(SqlColumn.ApplicationTable.STATUS, status.getIndex())).offset(pageRequest.getOffset()).limit(pageRequest.getLimit());

        QuerySpecification cqs = QuerySpecification.newSpecification(SqlBase.COUNT, "application");
        cqs.where(cqs.eq(SqlColumn.ApplicationTable.STATUS, status.getIndex()));

        List<ApplicationEntity> applicationList = new ArrayList<>();
        long count = 0;
        ApplicationDAO dao = new ApplicationDAO();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                applicationList = dao.getAllBySpecification(sqs);
                count = dao.countBySpecification(cqs);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return Page.of(applicationList, count);
    }
}
