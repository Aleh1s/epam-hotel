package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlReservationEntityMapper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.aleh1s.hotelepam.constant.SqlColumn.ReservationTable.*;
import static ua.aleh1s.hotelepam.constant.SqlQuery.ReservationTable.*;

public class ReservationDAO extends DAO {


    private final SqlReservationEntityMapper mapper =
            AppContext.getInstance().getSqlReservationEntityMapper();

    public Optional<ReservationEntity> findById(Long id) throws DaoException {
        ReservationEntity reservationEntity = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID)) {
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
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.updateLong(ID.getName(), entity.getId());
                    resultSet.updateInt(ROOM_NUMBER.getName(), entity.getRoomNumber());
                    resultSet.updateLong(CUSTOMER_ID.getName(), entity.getCustomerId());
                    resultSet.updateDate(ENTRY_DATE.getName(), Date.valueOf(entity.getEntryDate()));
                    resultSet.updateDate(LEAVING_DATE.getName(), Date.valueOf(entity.getLeavingDate()));
                    resultSet.updateTimestamp(CREATED_AT.getName(), Timestamp.valueOf(entity.getCreatedAt()));
                    resultSet.updateTimestamp(EXPIRED_AT.getName(), Timestamp.valueOf(entity.getExpiredAt()));
                    resultSet.updateTimestamp(PAYED_AT.getName(), entity.getPayedAt() != null ? Timestamp.valueOf(entity.getPayedAt()) : null);
                    resultSet.updateBigDecimal(TOTAL_AMOUNT.getName(), entity.getTotalAmount());
                    resultSet.updateInt(STATUS.getName(), entity.getStatus().getIndex());
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Long save(ReservationEntity entity) throws DaoException {
        long id = 0L;
        try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getRoomNumber());
            statement.setLong(2, entity.getCustomerId());
            statement.setDate(3, Date.valueOf(entity.getEntryDate()));
            statement.setDate(4, Date.valueOf(entity.getLeavingDate()));
            statement.setTimestamp(5, Timestamp.valueOf(entity.getCreatedAt()));
            statement.setTimestamp(6, Timestamp.valueOf(entity.getExpiredAt()));
            statement.setTimestamp(7, entity.getPayedAt() != null ? Timestamp.valueOf(entity.getPayedAt()) : null);
            statement.setBigDecimal(8, entity.getTotalAmount());
            statement.setInt(9, entity.getStatus().getIndex());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return id;
    }

    public List<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest) throws DaoException {
        List<ReservationEntity> reservationEntities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PAGE_BY_STATUS_ORDER_BY_CREATED_AT)) {
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
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_CUSTOMER_ID)) {
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
        try (PreparedStatement statement = connection.prepareStatement(COUNT_ALL_BY_STATUS)) {
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
        try (PreparedStatement statement = connection.prepareStatement(SELECT_AVAILABLE_PAGE_ORDER_BY_CREATED_AT)) {
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
        try (PreparedStatement statement = connection.prepareStatement(COUNT_AVAILABLE)) {
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

    public List<ReservationEntity> getActualReservations() throws DaoException {
        List<ReservationEntity> actualReservations = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACTUAL)) {
            LocalDate now = LocalDate.now();
            statement.setDate(1, Date.valueOf(now));
            statement.setDate(2, Date.valueOf(now));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    actualReservations.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return actualReservations;
    }

    public List<ReservationEntity> getActualReservationsByRoomNumber(Integer number) throws DaoException {
        List<ReservationEntity> actualReservations = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACTUAL_BY_ROOM_NUMBER)) {
            LocalDate now = LocalDate.now();
            statement.setInt(1, number);
            statement.setDate(2, Date.valueOf(now));
            statement.setDate(3, Date.valueOf(now));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    actualReservations.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return actualReservations;
    }

    public void updateStatus(ReservationEntity reservation) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
            statement.setInt(1, reservation.getStatus().getIndex());
            statement.setLong(2, reservation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
