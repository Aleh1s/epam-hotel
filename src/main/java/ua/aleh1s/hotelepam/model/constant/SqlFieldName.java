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
}
