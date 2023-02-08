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
    public static final String USER_TIMEZONE = "timezone";
    public static final String USER_LOCALE = "locale";
    public static final String USER_ROLE = "role";

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

}
