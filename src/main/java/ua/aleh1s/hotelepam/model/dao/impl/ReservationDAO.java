package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.dao.exception.DaoException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlReservationEntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.model.constant.SqlFieldName.*;
import static ua.aleh1s.hotelepam.model.constant.SqlQuery.*;

public class ReservationDAO extends DAO {


    private final SqlReservationEntityMapper mapper =
            AppContext.getInstance().getSqlReservationEntityMapper();

    public Optional<ReservationEntity> findById(Long id) throws DaoException {
        ReservationEntity reservationEntity = null;
        try (PreparedStatement statement = connection.prepareStatement(RESERVATION_SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reservationEntity = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(reservationEntity);
    }

    public void update(ReservationEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(RESERVATION_SELECT_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(RESERVATION_ID, entity.getId());
                    resultSet.updateInt(RESERVATION_ROOM_NUMBER, entity.getRoomNumber());
                    resultSet.updateLong(RESERVATION_CUSTOMER_ID, entity.getCustomerId());
                    resultSet.updateDate(RESERVATION_ENTRY_DATE, Date.valueOf(entity.getEntryDate()));
                    resultSet.updateDate(RESERVATION_LEAVING_DATE, Date.valueOf(entity.getLeavingDate()));
                    resultSet.updateTimestamp(RESERVATION_CREATED_AT, Timestamp.valueOf(entity.getCreatedAt()));
                    resultSet.updateTimestamp(RESERVATION_EXPIRED_AT, Timestamp.valueOf(entity.getExpiredAt()));
                    resultSet.updateTimestamp(RESERVATION_PAYED_AT, entity.getPayedAt() != null ? Timestamp.valueOf(entity.getPayedAt()) : null);
                    resultSet.updateBigDecimal(RESERVATION_TOTAL_AMOUNT, entity.getTotalAmount());
                    resultSet.updateInt(RESERVATION_STATUS, entity.getStatus().getIndex());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void save(ReservationEntity entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(RESERVATION_INSERT)) {
            statement.setInt(1, entity.getRoomNumber());
            statement.setLong(2, entity.getCustomerId());
            statement.setDate(3, Date.valueOf(entity.getEntryDate()));
            statement.setDate(4, Date.valueOf(entity.getLeavingDate()));
            statement.setTimestamp(5, Timestamp.valueOf(entity.getCreatedAt()));
            statement.setTimestamp(6, Timestamp.valueOf(entity.getExpiredAt()));
            statement.setTimestamp(7, entity.getPayedAt() != null ? Timestamp.valueOf(entity.getPayedAt()) : null);
            statement.setBigDecimal(8, entity.getTotalAmount());
            statement.setInt(9, entity.getStatus().getIndex());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest) throws DaoException {
        List<ReservationEntity> reservationEntities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(RESERVATION_SELECT_PAGE_BY_STATUS_ORDER_BY_CREATED_AT)) {
            statement.setInt(1, status.getIndex());
            statement.setInt(2, pageRequest.getOffset());
            statement.setInt(3, pageRequest.getLimit());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    reservationEntities.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return reservationEntities;
    }

    public List<ReservationEntity> getAllByCustomerId(Long userId) throws DaoException {
        List<ReservationEntity> reservationEntities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(RESERVATION_SELECT_ALL_BY_CUSTOMER_ID)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    reservationEntities.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return reservationEntities;
    }

    public Integer countByStatus(ReservationStatus status) throws DaoException {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(RESERVATION_SELECT_COUNT_BY_STATUS)) {
            statement.setInt(1, status.getIndex());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return count;
    }

    public List<ReservationEntity> getAll(PageRequest pageRequest) throws DaoException {
        List<ReservationEntity> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(RESERVATION_SELECT_PAGE_ORDER_BY_CREATED_AT)) {
            statement.setInt(1, pageRequest.getOffset());
            statement.setInt(2, pageRequest.getLimit());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public Integer count() throws DaoException {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(RESERVATION_COUNT_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return count;
    }
}
