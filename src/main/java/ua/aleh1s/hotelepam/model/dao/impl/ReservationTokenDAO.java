package ua.aleh1s.hotelepam.model.dao.impl;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.constant.SqlQuery;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.model.dao.DaoException;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.sqlmapper.SqlReservationTokenEntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import static ua.aleh1s.hotelepam.constant.SqlQuery.ReservationTokenTable.*;
import static ua.aleh1s.hotelepam.constant.SqlQuery.ReservationTokenTable.INSERT;

public class ReservationTokenDAO extends DAO {

    private final SqlReservationTokenEntityMapper mapper = AppContext.getInstance().getSqlReservationTokenEntityMapper();

    public void save(ReservationTokenEntity reservationToken) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, reservationToken.getId());
            statement.setTimestamp(2, Timestamp.valueOf(reservationToken.getCreatedAt()));
            statement.setTimestamp(3, Timestamp.valueOf(reservationToken.getExpiredAt()));
            statement.setLong(4, reservationToken.getReservationId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<ReservationTokenEntity> findById(String tokenId) throws DaoException {
        ReservationTokenEntity reservationToken = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setString(1, tokenId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reservationToken = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(reservationToken);
    }

    public void updateConfirmedAt(ReservationTokenEntity reservationToken) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement("update reservation_token set confirmed_at = ? where id = ?")) {
            statement.setTimestamp(1, Timestamp.valueOf(reservationToken.getConfirmedAt()));
            statement.setString(2, reservationToken.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
