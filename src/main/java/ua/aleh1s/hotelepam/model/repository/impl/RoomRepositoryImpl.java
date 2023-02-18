package ua.aleh1s.hotelepam.model.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.dao.impl.RoomDAO;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    private static final Logger logger = LogManager.getLogger(RoomRepositoryImpl.class);

    @Override
    public Optional<RoomEntity> getByRoomNumber(Integer roomNumber) {
        Optional<RoomEntity> roomEntity = Optional.empty();
        RoomDAO dao = new RoomDAO();
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
    public void update(RoomEntity entity) {
        RoomDAO dao = new RoomDAO();
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
        RoomDAO dao = new RoomDAO();
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
