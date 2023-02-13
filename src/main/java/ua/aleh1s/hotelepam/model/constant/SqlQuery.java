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
    public static final String USER_INSERT = "insert into \"user\" (email, first_name, last_name, phone_number, password, locale, role) values (?, ?, ?, ?, ?, ?, ?)";

    // ApplicationEntity
    public static final String APPLICATION_INSERT = "insert into \"application\" (number_of_guests, room_class, date_of_entry, date_of_leaving, status, customer_id) values (?, ?, ?, ?, ?, ?)";
    public static final String APPLICATION_SELECT_BY_ID = "select * from \"application\" where id = ?";

    // RoomEntity
    public static final String ROOM_SELECT_BY_ROOM_NUMBER = "select * from \"room\" where room_number = ?";

    // ReservationEntity
    public static final String RESERVATION_INSERT = "insert into \"reservation\" (room_number, customer_id, date_of_entry, date_of_leaving, created_at, expiration_date, payed_at, total_amount) values (?, ?, ?, ?, ?, ?, ?, ?)";

    // RequestEntity
    public static final String REQUEST_INSERT = "insert into \"request\" (room_number, customer_id, status, entry_date, leaving_date, total_amount) values (?, ?, ?, ?, ?, ?)";

}
