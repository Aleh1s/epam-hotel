package ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration;

import ua.aleh1s.hotelepam.constant.SqlColumn;
import ua.aleh1s.hotelepam.model.entity.*;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class EntityConfigurationHolder {

    private static EntityConfigurationHolder instance;
    private final Map<Class<?>, EntityConfiguration> entityConfigurationMap;

    public static synchronized EntityConfigurationHolder getInstance() {
        if (isNull(instance))
            instance = new EntityConfigurationHolder();
        return instance;
    }

    {
        entityConfigurationMap = new HashMap<>();

        Map<String, Column> userEntityColumnMap = new HashMap<>();
        userEntityColumnMap.put("id", SqlColumn.UserTable.ID);
        userEntityColumnMap.put("email", SqlColumn.UserTable.EMAIL);
        userEntityColumnMap.put("firstName", SqlColumn.UserTable.FIRST_NAME);
        userEntityColumnMap.put("lastName", SqlColumn.UserTable.LAST_NAME);
        userEntityColumnMap.put("phoneNumber", SqlColumn.UserTable.PHONE_NUMBER);
        userEntityColumnMap.put("password", SqlColumn.UserTable.PASSWORD);
        userEntityColumnMap.put("locale", SqlColumn.UserTable.LOCALE);
        userEntityColumnMap.put("role", SqlColumn.UserTable.ROLE);
        userEntityColumnMap.put("account", SqlColumn.UserTable.ACCOUNT);

        EntityConfiguration entityConfiguration =
                EntityConfiguration.newConfiguration("user", userEntityColumnMap);
        entityConfigurationMap.put(UserEntity.class, entityConfiguration);

        Map<String, Column> applicationEntityColumnMap = new HashMap<>();
        applicationEntityColumnMap.put("id", SqlColumn.ApplicationTable.ID);
        applicationEntityColumnMap.put("guests", SqlColumn.ApplicationTable.GUESTS);
        applicationEntityColumnMap.put("roomClass", SqlColumn.ApplicationTable.ROOM_CLASS);
        applicationEntityColumnMap.put("checkIn", SqlColumn.ApplicationTable.CHECK_IN);
        applicationEntityColumnMap.put("checkOut", SqlColumn.ApplicationTable.CHECK_OUT);
        applicationEntityColumnMap.put("status", SqlColumn.ApplicationTable.STATUS);
        applicationEntityColumnMap.put("customerId", SqlColumn.ApplicationTable.CUSTOMER_ID);
        applicationEntityColumnMap.put("createdAt", SqlColumn.ApplicationTable.CREATED_AT);

        EntityConfiguration applicationConfiguration =
                EntityConfiguration.newConfiguration("application", applicationEntityColumnMap);
        entityConfigurationMap.put(ApplicationEntity.class, applicationConfiguration);

        Map<String, Column> requestEntityColumnMap = new HashMap<>();
        requestEntityColumnMap.put("id", SqlColumn.RequestTable.ID);
        requestEntityColumnMap.put("roomNumber", SqlColumn.RequestTable.ROOM_NUMBER);
        requestEntityColumnMap.put("customerId", SqlColumn.RequestTable.CUSTOMER_ID);
        requestEntityColumnMap.put("status", SqlColumn.RequestTable.STATUS);
        requestEntityColumnMap.put("checkIn", SqlColumn.RequestTable.CHECK_IN);
        requestEntityColumnMap.put("checkOut", SqlColumn.RequestTable.CHECK_OUT);
        requestEntityColumnMap.put("totalAmount", SqlColumn.RequestTable.TOTAL_AMOUNT);
        requestEntityColumnMap.put("createdAt", SqlColumn.RequestTable.CREATED_AT);

        EntityConfiguration requestConfiguration =
                EntityConfiguration.newConfiguration("request", requestEntityColumnMap);
        entityConfigurationMap.put(RequestEntity.class, requestConfiguration);

        Map<String, Column> reservationEntityColumnMap = new HashMap<>();
        reservationEntityColumnMap.put("id", SqlColumn.ReservationTable.ID);
        reservationEntityColumnMap.put("roomNumber", SqlColumn.ReservationTable.ROOM_NUMBER);
        reservationEntityColumnMap.put("customerId", SqlColumn.ReservationTable.CUSTOMER_ID);
        reservationEntityColumnMap.put("checkIn", SqlColumn.ReservationTable.CHECK_IN);
        reservationEntityColumnMap.put("checkOut", SqlColumn.ReservationTable.CHECK_OUT);
        reservationEntityColumnMap.put("createdAt", SqlColumn.ReservationTable.CREATED_AT);
        reservationEntityColumnMap.put("expiredAt", SqlColumn.ReservationTable.EXPIRED_AT);
        reservationEntityColumnMap.put("payedAt", SqlColumn.ReservationTable.PAYED_AT);
        reservationEntityColumnMap.put("totalAmount", SqlColumn.ReservationTable.TOTAL_AMOUNT);
        reservationEntityColumnMap.put("status", SqlColumn.ReservationTable.STATUS);

        EntityConfiguration reservationConfiguration =
                EntityConfiguration.newConfiguration("reservation", reservationEntityColumnMap);
        entityConfigurationMap.put(ReservationEntity.class, reservationConfiguration);

        Map<String, Column> reservationTokenEntityColumnMap = new HashMap<>();
        reservationTokenEntityColumnMap.put("id", SqlColumn.ReservationTokenTable.ID);
        reservationTokenEntityColumnMap.put("createdAt", SqlColumn.ReservationTokenTable.CREATED_AT);
        reservationTokenEntityColumnMap.put("expiredAt", SqlColumn.ReservationTokenTable.EXPIRED_AT);
        reservationTokenEntityColumnMap.put("confirmedAt", SqlColumn.ReservationTokenTable.CONFIRMED_AT);
        reservationTokenEntityColumnMap.put("reservationId", SqlColumn.ReservationTokenTable.RESERVATION_ID);

        EntityConfiguration reservationToken =
                EntityConfiguration.newConfiguration("reservation_token", reservationTokenEntityColumnMap);
        entityConfigurationMap.put(ReservationTokenEntity.class, reservationToken);

        Map<String, Column> roomEntityColumnMap = new HashMap<>();
        roomEntityColumnMap.put("number", SqlColumn.RoomTable.NUMBER);
        roomEntityColumnMap.put("class", SqlColumn.RoomTable.CLAZZ);
        roomEntityColumnMap.put("description", SqlColumn.RoomTable.DESCRIPTION);
        roomEntityColumnMap.put("price", SqlColumn.RoomTable.PRICE);
        roomEntityColumnMap.put("title", SqlColumn.RoomTable.TITLE);
        roomEntityColumnMap.put("attributes", SqlColumn.RoomTable.ATTRIBUTES);
        roomEntityColumnMap.put("beds", SqlColumn.RoomTable.BEDS);
        roomEntityColumnMap.put("guests", SqlColumn.RoomTable.GUESTS);
        roomEntityColumnMap.put("isUnavailable", SqlColumn.RoomTable.IS_UNAVAILABLE);

        EntityConfiguration roomConfiguration =
                EntityConfiguration.newConfiguration("room", roomEntityColumnMap);
        entityConfigurationMap.put(RoomEntity.class, roomConfiguration);
    }


    public <T> EntityConfiguration valueOf(Class<T> clazz) {
        return entityConfigurationMap.get(clazz);
    }
}
