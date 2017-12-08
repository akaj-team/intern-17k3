package vn.asiantech.internship.ui.savedata.ex3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    private static final  int DATABASE_VERSION=1;



    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
