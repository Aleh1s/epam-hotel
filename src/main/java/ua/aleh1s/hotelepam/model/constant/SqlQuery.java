package ua.aleh1s.hotelepam.model.constant;

public class SqlQuery {

    private SqlQuery() {throw new RuntimeException("Can't instantiate object of this class");}

    // UserEntity
    public static final String USER_SELECT_BY_EMAIL = "select * from \"user\" where email = ?";
    public static final String USER_SELECT_BY_ID = "select * from \"user\" where id = ?";
    public static final String USER_SELECT_ALL = "select * from \"user\"";
    public static final String USER_DELETE_BY_ID = "delete from \"user\" where id = ?";
    public static final String USER_INSERT = "insert into \"user\" (email, first_name, last_name, phone_number, password, timezone, locale, role) values (?, ?, ?, ?, ?)";
}
