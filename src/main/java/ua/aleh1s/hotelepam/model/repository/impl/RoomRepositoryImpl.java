package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.criteria.RoomListCriteria;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.RoomSimpleDao;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private static final Logger logger = LogManager.getLogger(RoomRepositoryImpl.class);

    @Override
    public List<RoomEntity> getAll(RoomListCriteria criteria) {
        List<RoomEntity> roomEntities = new ArrayList<>();
        RoomSimpleDao dao = new RoomSimpleDao();
        try (Transaction transaction = Transaction.start(dao)) {
            try {
                roomEntities = dao.getAll(criteria);
                transaction.commit();
            } catch (DaoException e) {
                transaction.rollback();
                logger.error(e.getMessage(), e);
            }
        }
        return roomEntities;
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
}
