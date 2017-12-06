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

    public UsersDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
