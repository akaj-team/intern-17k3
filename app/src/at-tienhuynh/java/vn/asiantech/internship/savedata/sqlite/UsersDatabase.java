package vn.asiantech.internship.savedata.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created at 2017
 * Created by jackty on 06/12/2017.
 */

public class UsersDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersManager";
    private static final String TABLE_USER = "users";
    private static final String TABLE_COMPANY = "company";
    private static final String KEY_USERS_ID = "id";
    private static final String KEY_USERS_NAME = "name";
    private static final String KEY_USERS_AGE = "age";
    private static final String KEY_COMPANY_ID = "id";
    private static final String KEY_COMPANY_ID_USER = "id_user";
    private static final String KEY_COMPANY_NAME = "name";
    private static final String KEY_COMPANY_SLOGAN = "slogan";

    public UsersDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_USERS = "create table " + TABLE_USER + "(" + KEY_USERS_ID + " integer primary key autoincrement, "
                + KEY_COMPANY_ID_USER + " integer, " + KEY_USERS_AGE + " integer, " + " foreign key"
                + KEY_USERS_ID + " references" + TABLE_COMPANY + "(" + KEY_COMPANY_ID_USER + ")" + " )";

        String CREATE_TABLE_COMPANY = "create table " + TABLE_COMPANY + "(" + KEY_COMPANY_ID + " integer primary key autoincrement, "
                + KEY_USERS_NAME + " text, " + KEY_COMPANY_NAME + " text, " + KEY_COMPANY_SLOGAN + " text" + " )";
        sqLiteDatabase.execSQL(CREATE_TABLE_COMPANY);
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }
}
