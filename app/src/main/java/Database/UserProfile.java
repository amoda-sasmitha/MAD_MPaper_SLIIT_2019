package Database;

import android.provider.BaseColumns;

public final class UserProfile  {

    private UserProfile() { }

    protected static class Users implements BaseColumns {

        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_USERNAME = "userName";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_DOB  = "dateOfBirth";
        public static final String COLUMN_GENDER  = "Gender";

    }
}
