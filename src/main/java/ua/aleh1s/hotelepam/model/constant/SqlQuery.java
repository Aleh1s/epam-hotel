package ua.aleh1s.hotelepam.model.constant;

public class SqlQuery {



    private SqlQuery() {
        throw new RuntimeException("Can't instantiate object of this class");
    }

    // UserEntity
    public static final String USER_SELECT_BY_ID = "select * from \"user\" where id = ?";
    public static final String USER_SELECT_BY_EMAIL = "select * from \"user\" where email = ?";
    public static final String USER_SELECT_BY_PHONE_NUMBER = "select * from \"user\" where phone_number = ?";
    public static final String USER_DELETE_BY_ID = "delete from \"user\" where id = ?";
    public static final String USER_INSERT = "insert into \"user\" (email, first_name, last_name, phone_number, password, locale, role, account) values (?, ?, ?, ?, ?, ?, ?, ?)";

    // ApplicationEntity
    public static final String APPLICATION_INSERT = "insert into \"application\" (number_of_guests, room_class, date_of_entry, date_of_leaving, status, customer_id) values (?, ?, ?, ?, ?, ?)";
    public static final String APPLICATION_SELECT_BY_ID = "select * from \"application\" where id = ?";

    // RoomEntity
    public static final String ROOM_SELECT_BY_ROOM_NUMBER = "select * from \"room\" where room_number = ?";
    public static final String ROOM_SELECT_ALL_PAGEABLE = "select * from room offset ? limit ?";
    public static final String ROOM_COUNT_ALL = "select count(*) from room";
    public static final String ROOM_SELECT_ALL = "select * from room";



    // ReservationEntity
    public static final String RESERVATION_INSERT = "insert into \"reservation\" (room_number, customer_id, date_of_entry, date_of_leaving, created_at, expiration_date, payed_at, total_amount, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String RESERVATION_SELECT_BY_ROOM_NUMBER_AND_STATUS = "select * from \"reservation\" where room_number = ? and status = ?";
    public static final String RESERVATION_SELECT_BY_ID = "select * from \"reservation\" where id = ?";
    public static final String RESERVATION_SELECT_ALL_BY_CUSTOMER_ID = "select * from \"reservation\" where customer_id = ?";
    public static final String SELECT_COUNT_BY_CUSTOMER_ID = "select count(*) from \"reservation\" where customer_id = ?";
    public static final String RESERVATION_SELECT_PAGE_BY_STATUS_ORDER_BY_CREATED_AT = "select * from \"reservation\" where status = ? order by created_at desc offset ? limit ?";
    public static final String RESERVATION_SELECT_COUNT_BY_STATUS = "select count(*) from \"reservation\" where status = ?";
    public static final String RESERVATION_SELECT_PAGE_ORDER_BY_CREATED_AT = "select * from \"reservation\" where status != 6 order by created_at desc offset ? limit ?";
    public static final String RESERVATION_COUNT_ALL = "select count(*) from \"reservation\" where status != 6";


    // RequestEntity
    public static final String REQUEST_INSERT = "insert into \"request\" (room_number, customer_id, status, entry_date, leaving_date, total_amount) values (?, ?, ?, ?, ?, ?)";

}
