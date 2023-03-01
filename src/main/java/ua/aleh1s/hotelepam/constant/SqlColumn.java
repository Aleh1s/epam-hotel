package ua.aleh1s.hotelepam.constant;

import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.Column;

import java.sql.Types;

public class SqlColumn {

    private SqlColumn() {throw new RuntimeException("Can't instantiate object of this class");}

    public static class UserTable {
        public static final Column ID = Column.of("id", Types.BIGINT);
        public static final Column EMAIL = Column.of("email", Types.VARCHAR);
        public static final Column FIRST_NAME = Column.of("first_name", Types.VARCHAR);
        public static final Column LAST_NAME = Column.of("last_name", Types.VARCHAR);
        public static final Column PHONE_NUMBER = Column.of("phone_number", Types.VARCHAR);
        public static final Column PASSWORD = Column.of("password", Types.VARCHAR);
        public static final Column LOCALE = Column.of("locale", Types.VARCHAR);
        public static final Column ROLE = Column.of("role", Types.VARCHAR);
        public static final Column ACCOUNT = Column.of("account", Types.NUMERIC);
    }

    public static class RoomTable {
        public static final Column ROOM_NUMBER = Column.of("room_number", Types.INTEGER);
        public static final Column CLASS = Column.of("class", Types.INTEGER);
        public static final Column STATUS = Column.of("status", Types.INTEGER);
        public static final Column DESCRIPTION = Column.of("description", Types.LONGNVARCHAR);
        public static final Column BUSY_UNTIL = Column.of("busy_until", Types.DATE);
        public static final Column PRICE = Column.of("price", Types.NUMERIC);
        public static final Column NAME = Column.of("name", Types.VARCHAR);
        public static final Column ATTRIBUTES = Column.of("attributes", Types.LONGNVARCHAR);
        public static final Column BEDS_NUMBER = Column.of("beds_number", Types.INTEGER);
        public static final Column PERSONS_NUMBER = Column.of("persons_number", Types.INTEGER);
        public static final Column AREA = Column.of("area", Types.INTEGER);
    }

    public static class ApplicationTable {
        public static final Column ID = Column.of("id", Types.BIGINT);
        public static final Column GUESTS = Column.of("guests", Types.INTEGER);
        public static final Column ROOM_CLASS = Column.of("room_class", Types.INTEGER);
        public static final Column CHECK_IN = Column.of("check_in", Types.DATE);
        public static final Column CHECK_OUT = Column.of("check_out", Types.DATE);
        public static final Column STATUS = Column.of("status", Types.INTEGER);
        public static final Column CUSTOMER_ID = Column.of("customer_id", Types.BIGINT);
    }

    //RequestEntity
    public static class RequestTable {
        public static final Column ID = Column.of("id", Types.BIGINT);
        public static final Column CUSTOMER_ID = Column.of("customer_id", Types.BIGINT);
        public static final Column ROOM_NUMBER = Column.of("room_number", Types.INTEGER);
        public static final Column STATUS = Column.of("status", Types.INTEGER);
        public static final Column CHECK_IN = Column.of("check_in", Types.DATE);
        public static final Column CHECK_OUT = Column.of("check_out", Types.DATE);
        public static final Column TOTAL_AMOUNT = Column.of("total_amount", Types.NUMERIC);
    }

    public static class ReservationTable {
        public static final Column ID = Column.of("id", Types.BIGINT);
        public static final Column ROOM_NUMBER = Column.of("room_number", Types.INTEGER);
        public static final Column CUSTOMER_ID = Column.of("customer_id", Types.BIGINT);
        public static final Column CHECK_IN = Column.of("check_in", Types.DATE);
        public static final Column CHECK_OUT = Column.of("check_out", Types.DATE);
        public static final Column CREATED_AT = Column.of("created_at", Types.TIMESTAMP);
        public static final Column EXPIRED_AT = Column.of("expired_at", Types.TIMESTAMP);
        public static final Column PAYED_AT = Column.of("payed_at", Types.TIMESTAMP);
        public static final Column TOTAL_AMOUNT = Column.of("total_amount", Types.NUMERIC);
        public static final Column STATUS = Column.of("status", Types.INTEGER);
    }

    public static class ReservationTokenTable {

        public static final Column ID = Column.of("id", Types.VARCHAR);
        public static final Column CREATED_AT = Column.of("created_at", Types.TIMESTAMP);
        public static final Column EXPIRED_AT = Column.of("expired_at", Types.TIMESTAMP);
        public static final Column CONFIRMED_AT = Column.of("confirmed_at", Types.TIMESTAMP);
        public static final Column RESERVATION_ID = Column.of("reservation_id", Types.BIGINT);
    }
}
