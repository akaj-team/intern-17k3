package vn.asiantech.internship.ui.savedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hoangnhat on 06/12/2017.
 */

public class UserCompanySQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "company";
    //Table name
    private static final String TABLE_NAME_USER = "User";
    private static final String TABLE_NAME_COMPANY = "Company";
    private static final String TABLE_NAME_EMPLOYEE = "Employee";
    //Row in table User
    private static final String USER_ID = "Id";
    private static final String USER_NAME = "Name";
    private static final String USER_AGE = "Age";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_NAME_USER + "( "
            + USER_ID + " INTEGER PRIMARY KEY,"
            + USER_NAME + " TEXT NOT NULL,"
            + USER_AGE + " TEXT NOT NULL)";

    //Version
    private static final int DATABASE_VERSION = 1;

    UserCompanySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void insertUSER(int id, String name, int age) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, id);
        contentValues.put(USER_NAME, name);
        contentValues.put(USER_AGE, age);
        sqLiteDatabase.insert(TABLE_NAME_USER,null,contentValues);
    }
}
