package ua.aleh1s.hotelepam.constant;

public class SqlField {

    private SqlField() {throw new RuntimeException("Can't instantiate object of this class");}

    public static class UserTable {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String PASSWORD = "password";
        public static final String LOCALE = "locale";
        public static final String ROLE = "role";
        public static final String ACCOUNT = "account";
    }

    public static class RoomTable {
        public static final String ROOM_NUMBER = "room_number";
        public static final String CLASS = "class";
        public static final String STATUS = "status";
        public static final String DESCRIPTION = "description";
        public static final String BUSY_UNTIL = "busy_until";
        public static final String PRICE = "price";
        public static final String NAME = "name";
        public static final String ATTRIBUTES = "attributes";
        public static final String BEDS_NUMBER = "beds_number";
        public static final String PERSONS_NUMBER = "persons_number";
        public static final String AREA = "area";
    }

    public static class ApplicationTable {
        public static final String ID = "id";
        public static final String NUMBER_OF_GUESTS = "number_of_guests";
        public static final String ROOM_CLASS = "room_class";
        public static final String ENTRY_DATE = "date_of_entry";
        public static final String LEAVING_DATE = "date_of_leaving";
        public static final String STATUS = "status";
        public static final String CUSTOMER_ID = "customer_id";
    }

    //RequestEntity
    public static class RequestTable {
        public static final String ID = "id";
        public static final String CUSTOMER_ID = "customer_id";
        public static final String ROOM_NUMBER = "room_number";
        public static final String STATUS = "status";
        public static final String ENTRY_DATE = "entry_date";
        public static final String LEAVING_DATE = "leaving_date";
        public static final String TOTAL_AMOUNT = "total_amount";
    }

    public static class ReservationTable {
        public static final String ID = "id";
        public static final String ROOM_NUMBER = "room_number";
        public static final String CUSTOMER_ID = "customer_id";
        public static final String ENTRY_DATE = "date_of_entry";
        public static final String LEAVING_DATE = "date_of_leaving";
        public static final String CREATED_AT = "created_at";
        public static final String EXPIRED_AT = "expiration_date";
        public static final String PAYED_AT = "payed_at";
        public static final String TOTAL_AMOUNT = "total_amount";
        public static final String STATUS = "status";
    }
}
