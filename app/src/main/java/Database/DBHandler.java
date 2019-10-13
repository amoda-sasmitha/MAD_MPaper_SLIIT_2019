package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.User;


public class DBHandler extends SQLiteOpenHelper {

    private final static String DB_NAME = "user_db";
    public DBHandler( Context context ) {
        super(context, DB_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE_USER = "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " ( " +
                                 UserProfile.Users._ID + " INTEGER PRIMARY KEY ," +
                                 UserProfile.Users.COLUMN_USERNAME + " TEXT , " +
                                 UserProfile.Users.COLUMN_DOB + " TEXT ,  " +
                                 UserProfile.Users.COLUMN_GENDER + " TEXT , " +
                                 UserProfile.Users.COLUMN_PASSWORD + " TEXT );";

        db.execSQL(SQL_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addInfo(User user){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put( UserProfile.Users.COLUMN_USERNAME , user.getUsername() );
        values.put( UserProfile.Users.COLUMN_PASSWORD , user.getPassword() );
        values.put( UserProfile.Users.COLUMN_DOB , user.getDOB() );
        values.put( UserProfile.Users.COLUMN_GENDER , user.getGender() );

        long id = db.insert( UserProfile.Users.TABLE_NAME , null , values );
        if( id > 0 ){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<User> readAllInfoAllUsers(){

        SQLiteDatabase db = getReadableDatabase();

        String Projection[] = { UserProfile.Users._ID , UserProfile.Users.COLUMN_USERNAME , UserProfile.Users.COLUMN_PASSWORD , UserProfile.Users.COLUMN_DOB,
                              UserProfile.Users.COLUMN_GENDER};

        Cursor cursor = db.query( UserProfile.Users.TABLE_NAME , Projection , null , null ,  null , null , null );
        ArrayList<User> arrayList = new ArrayList<>();

        while ( cursor.moveToNext() ){

            User user = new User();
            user.setID( cursor.getInt( cursor.getColumnIndexOrThrow(  UserProfile.Users._ID )  ) );
            user.setUsername( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_USERNAME )));
            user.setPassword( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_PASSWORD ))    );
            user.setDOB( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_DOB )));
            user.setGender( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_GENDER )));

            arrayList.add(user);
        }

        return arrayList;
    }

    public  User readAllInfo( int id ){

        SQLiteDatabase db = getReadableDatabase();

        String Projection[] = { UserProfile.Users._ID , UserProfile.Users.COLUMN_USERNAME , UserProfile.Users.COLUMN_PASSWORD , UserProfile.Users.COLUMN_DOB,
                UserProfile.Users.COLUMN_GENDER};

        //String selection = UserProfile.Users._ID + " = ? AND "+ UserProfile.Users.COLUMN_USERNAME + " = ?";
        String selection = UserProfile.Users._ID + " = ? ";

        //String Args[] = { String.valueOf(id) , username  };
        String Args[] = { String.valueOf(id)  };

        //String groupby  =  UserProfile.Users.COLUMN_GENDER;
       // String Orderby = UserProfile.Users.COLUMN_USERNAME+ " DESC";

        Cursor cursor = db.query( UserProfile.Users.TABLE_NAME , Projection , selection , Args ,  null , null , null );

        User user = new User();
        while ( cursor.moveToNext() ){
        user.setID( cursor.getInt( cursor.getColumnIndexOrThrow(  UserProfile.Users._ID )  ) );
            user.setUsername( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_USERNAME )));
            user.setPassword( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_PASSWORD ))    );
            user.setDOB( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_DOB )));
            user.setGender( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_GENDER )));

        }

        return user;
    }

    public  User readAllInfo( String Username ){

        SQLiteDatabase db = getReadableDatabase();

        String Projection[] = { UserProfile.Users._ID , UserProfile.Users.COLUMN_USERNAME , UserProfile.Users.COLUMN_PASSWORD , UserProfile.Users.COLUMN_DOB,
                UserProfile.Users.COLUMN_GENDER};

        //String selection = UserProfile.Users._ID + " = ? AND "+ UserProfile.Users.COLUMN_USERNAME + " = ?";
        String selection = UserProfile.Users.COLUMN_USERNAME + " = ? ";

        //String Args[] = { String.valueOf(id) , username  };
        String Args[] = { Username  };

        //String groupby  =  UserProfile.Users.COLUMN_GENDER;
        // String Orderby = UserProfile.Users.COLUMN_USERNAME+ " DESC";

        Cursor cursor = db.query( UserProfile.Users.TABLE_NAME , Projection , selection , Args ,  null , null , null );

        User user = new User();
        while ( cursor.moveToNext() ){
            user.setID( cursor.getInt( cursor.getColumnIndexOrThrow(  UserProfile.Users._ID )  ) );
            user.setUsername( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_USERNAME )));
            user.setPassword( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_PASSWORD ))    );
            user.setDOB( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_DOB )));
            user.setGender( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_GENDER )));

        }

        return user;
    }

    public  User validateUser( String Username  , String Password ){

        SQLiteDatabase db = getReadableDatabase();

        String Projection[] = { UserProfile.Users._ID , UserProfile.Users.COLUMN_USERNAME , UserProfile.Users.COLUMN_PASSWORD , UserProfile.Users.COLUMN_DOB,
                UserProfile.Users.COLUMN_GENDER};

        String selection = UserProfile.Users.COLUMN_USERNAME + " = ? AND "+ UserProfile.Users.COLUMN_PASSWORD + " = ?";
        //String selection = UserProfile.Users.COLUMN_USERNAME + " = ? ";

        //String Args[] = { String.valueOf(id) , username  };
        String Args[] = { Username , Password };

        //String groupby  =  UserProfile.Users.COLUMN_GENDER;
        // String Orderby = UserProfile.Users.COLUMN_USERNAME+ " DESC";

        Cursor cursor = db.query( UserProfile.Users.TABLE_NAME , Projection , selection , Args ,  null , null , null );

        User user = new User();
        while ( cursor.moveToNext() ){
            user.setID( cursor.getInt( cursor.getColumnIndexOrThrow(  UserProfile.Users._ID )  ) );
            user.setUsername( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_USERNAME )));
            user.setPassword( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_PASSWORD ))    );
            user.setDOB( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_DOB )));
            user.setGender( cursor.getString( cursor.getColumnIndexOrThrow( UserProfile.Users.COLUMN_GENDER )));

        }

        return user;
    }

    public boolean UpdateInfo( User user ){

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put( UserProfile.Users.COLUMN_USERNAME , user.getUsername() );
        values.put( UserProfile.Users.COLUMN_PASSWORD , user.getPassword() );
        values.put( UserProfile.Users.COLUMN_DOB , user.getDOB() );
        values.put( UserProfile.Users.COLUMN_GENDER , user.getGender() );

        String selection = UserProfile.Users._ID + " = ?";
        String Args[] = { String.valueOf( user.getID() )  };

        long id = db.update( UserProfile.Users.TABLE_NAME , values , selection , Args );
        if( id > 0 ){
            return true;
        }else {
            return false;
        }
    }

    public boolean DeleteInfo( int id ){

        SQLiteDatabase db = getReadableDatabase();


        String selection = UserProfile.Users._ID + " = ?";
        String Args[] = { String.valueOf( id )  };

        long result = db.delete( UserProfile.Users.TABLE_NAME , selection , Args);
        if( result > 0 ){
            return true;
        }else {
            return false;
        }
    }

}
