package ua.aleh1s.hotelepam.model.constant;

public class SqlFieldName {


    private SqlFieldName() {throw new RuntimeException("Can't instantiate object of this class");}

    // UserEntity
    public static final String USER_ID = "id";
    public static final String USER_EMAIL = "email";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_PASSWORD = "password";
    public static final String USER_LOCALE = "locale";
    public static final String USER_ROLE = "role";
    public static final String USER_ACCOUNT = "account";

    // RoomEntity
    public static final String ROOM_ROOM_NUMBER = "room_number";
    public static final String ROOM_CLASS = "class";
    public static final String ROOM_STATUS = "status";
    public static final String ROOM_DESCRIPTION = "description";
    public static final String ROOM_BUSY_UNTIL = "busy_until";
    public static final String ROOM_PRICE = "price";
    public static final String ROOM_NAME = "name";
    public static final String ROOM_ATTRIBUTES = "attributes";
    public static final String ROOM_BEDS_NUMBER = "beds_number";
    public static final String ROOM_PERSONS_NUMBER = "persons_number";
    public static final String ROOM_AREA = "area";

    //ApplicationEntity
    public static final String APPLICATION_ID = "id";
    public static final String APPLICATION_NUMBER_OF_GUESTS = "number_of_guests";
    public static final String APPLICATION_ROOM_CLASS = "room_class";
    public static final String APPLICATION_ENTRY_DATE = "date_of_entry";
    public static final String APPLICATION_LEAVING_DATE = "date_of_leaving";
    public static final String APPLICATION_STATUS = "status";
    public static final String APPLICATION_CUSTOMER_ID = "customer_id";

    //RequestEntity
    public static final String REQUEST_ID = "id";
    public static final String REQUEST_CUSTOMER_ID = "customer_id";
    public static final String REQUEST_ROOM_NUMBER = "room_number";
    public static final String REQUEST_STATUS = "status";
    public static final String REQUEST_ENTRY_DATE = "entry_date";
    public static final String REQUEST_LEAVING_DATE = "leaving_date";
    public static final String REQUEST_TOTAL_AMOUNT = "total_amount";

    public static final String RESERVATION_ID = "id";
    public static final String RESERVATION_ROOM_NUMBER = "room_number";
    public static final String RESERVATION_CUSTOMER_ID = "customer_id";
    public static final String RESERVATION_ENTRY_DATE = "date_of_entry";
    public static final String RESERVATION_LEAVING_DATE = "date_of_leaving";
    public static final String RESERVATION_CREATED_AT = "created_at";
    public static final String RESERVATION_EXPIRED_AT = "expiration_date";
    public static final String RESERVATION_PAYED_AT = "payed_at";
    public static final String RESERVATION_TOTAL_AMOUNT = "total_amount";
    public static final String RESERVATION_STATUS = "status";


}
