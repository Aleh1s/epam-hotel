package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.controller.page.Page;
import ua.aleh1s.hotelepam.controller.page.PageRequest;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.criteria.impl.RoomListCriteria;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.RoomSimpleDao;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.pagination.Pagination;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    private static final Logger logger = LogManager.getLogger(RoomRepositoryImpl.class);

    @Override
    public Optional<RoomEntity> getByRoomNumber(Integer roomNumber) {
        Optional<RoomEntity> roomEntity = Optional.empty();
        RoomSimpleDao dao = new RoomSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                roomEntity = dao.findById(roomNumber);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return roomEntity;
    }

    @Override
    public List<RoomEntity> getAll(Criteria criteria, Pagination pagination) {
        List<RoomEntity> roomEntities = new ArrayList<>();
        RoomSimpleDao dao = new RoomSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                roomEntities = dao.getAll(criteria, pagination);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return roomEntities;
    }

    @Override
    public Page<RoomEntity> getAll(PageRequest pageRequest) {
        List<RoomEntity> result = new ArrayList<>();
        Integer count = 0;
        RoomSimpleDao dao = new RoomSimpleDao();
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
    public Integer count(RoomListCriteria criteria) {
        int count = 0;
        RoomSimpleDao dao = new RoomSimpleDao();
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
    public void update(RoomEntity entity) {
        RoomSimpleDao dao = new RoomSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                dao.update(entity);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<RoomEntity> getAll() {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        RoomSimpleDao dao = new RoomSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                roomEntityList = dao.getAll();
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return roomEntityList;
    }
}
