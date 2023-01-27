package ua.aleh1s.hotelepam.model.constant;

public class SqlFieldName {

    private SqlFieldName() {throw new RuntimeException("Can't instantiate object of this class");}

    // UserEntity
    public final static String USER_ID = "id";
    public final static String USER_EMAIL = "email";
    public final static String USER_PASSWORD = "password";
    public final static String USER_TIMEZONE = "timezone";
    public final static String USER_LOCALE = "locale";
    public final static String USER_ROLE = "role";

    // CustomerEntity
    public final static String CUSTOMER_ID = "id";
    public final static String CUSTOMER_FIRST_NAME = "first_name";
    public final static String CUSTOMER_LAST_NAME = "last_name";
    public final static String CUSTOMER_PHONE_NUMBER = "phone_number";
    public final static String CUSTOMER_USER_ID = "user_id";
}
