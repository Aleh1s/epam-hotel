package ua.aleh1s.hotelepam.model.constant;

public class SqlQuery {

    // UserEntity
    public static final String USER_SELECT_BY_LOGIN = "select * from \"user\" where login = ?";
    public static final String USER_SELECT_BY_ID = "select * from \"user\" where id = ?";
    public static final String USER_SELECT_ALL = "select * from \"user\"";
    public static final String USER_DELETE_BY_ID = "delete from \"user\" where id = ?";
    public static final String USER_INSERT = "insert into \"user\" (login, password, timezone, locale, role) values (?, ?, ?, ?, ?)";
}
