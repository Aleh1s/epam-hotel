package ua.aleh1s.hotelepam.model.constant;

public class SqlQuery {


    private SqlQuery() {
        throw new RuntimeException("Can't instantiate object of this class");
    }

    public static class UserTable {
        public static final String INSERT = "insert into \"user\" (email, first_name, last_name, phone_number, password, locale, role, account) values (?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String SELECT_ALL_BY_ID = "select * from \"user\" where id = ?";
        public static final String SELECT_ALL_BY_EMAIL = "select * from \"user\" where email = ?";
        public static final String SELECT_ALL_BY_PHONE_NUMBER = "select * from \"user\" where phone_number = ?";
    }

    public static class ApplicationTable {
        public static final String INSERT = "insert into \"application\" (number_of_guests, room_class, date_of_entry, date_of_leaving, status, customer_id) values (?, ?, ?, ?, ?, ?)";
        public static final String SELECT_ALL_BY_ID = "select * from \"application\" where id = ?";
        public static final String SELECT_PAGE_BY_STATUS = "select * from \"application\" where status = ? offset ? limit ?";
        public static final String COUNT_ALL_BY_STATUS = "select count(*) from \"application\" where status = ?";
    }

    public static class RoomTable {
        public static final String SELECT_ALL = "select * from \"room\"";
        public static final String SELECT_ALL_BY_ROOM_NUMBER = "select * from \"room\" where room_number = ?";
    }

    public static class ReservationTable {
        public static final String INSERT = "insert into \"reservation\" (room_number, customer_id, date_of_entry, date_of_leaving, created_at, expiration_date, payed_at, total_amount, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String COUNT_AVAILABLE = "select count(*) from \"reservation\" where status != 6";
        public static final String SELECT_ALL_BY_ID = "select * from \"reservation\" where id = ?";
        public static final String COUNT_ALL_BY_STATUS = "select count(*) from \"reservation\" where status = ?";
        public static final String SELECT_ALL_BY_CUSTOMER_ID = "select * from \"reservation\" where customer_id = ?";
        public static final String SELECT_AVAILABLE_PAGE_ORDER_BY_CREATED_AT = "select * from \"reservation\" where status != 6 order by created_at desc offset ? limit ?";
        public static final String SELECT_PAGE_BY_STATUS_ORDER_BY_CREATED_AT = "select * from \"reservation\" where status = ? order by created_at desc offset ? limit ?";
    }

    public static class RequestTable {
        public static final String INSERT = "insert into \"request\" (room_number, customer_id, status, entry_date, leaving_date, total_amount) values (?, ?, ?, ?, ?, ?)";
        public static final String SELECT_ALL_BY_ID = "select * from \"request\" where id = ?";
    }
}
