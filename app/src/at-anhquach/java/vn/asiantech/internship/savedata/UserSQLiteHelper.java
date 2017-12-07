package vn.asiantech.internship.savedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AnhQuach";
    public static final String TABLE_USER = "users";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_COMPANY = "company";
    public static final String TABLE_EMPLOYEE = "employee";

    public static final String ID_USER = "id";
    public static final String ID_EMPLOYEE = "id";
    public static final String ID_COMPANY = "id";
    public static final String NAME_USER = "name";
    public static final String NAME_COMPANY = "name";
    public static final String AGE = "age";
    public static final String SLOGAN = "sologan";
    public static final String ID_EMPLOYEE_USER = "id_user";
    public static final String ID_EMPLOYEE_COMPANY = "id_company";
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER
            + "(" + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_USER + " TEXT,"
            + AGE + " TEXT)";
    private static final String CREATE_TABLE_COMPANY = "CREATE TABLE " + TABLE_COMPANY
            + "(" + ID_COMPANY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_COMPANY + " TEXT,"
            + SLOGAN + " TEXT)";
    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + TABLE_EMPLOYEE
            + "(" + ID_EMPLOYEE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_EMPLOYEE_USER + " INTEGER,"
            + ID_EMPLOYEE_COMPANY + " INTEGER,"
            + " FOREIGN KEY (" + ID_EMPLOYEE_USER + ") REFERENCES " + TABLE_USER + "(" + ID_USER + "),"
            + " FOREIGN KEY (" + ID_EMPLOYEE_COMPANY + ") REFERENCES " + TABLE_COMPANY + "(" + ID_COMPANY + "))";
    UserSQLiteHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMPANY);
        sqLiteDatabase.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(sqLiteDatabase);
    }
    private void initData(SQLiteDatabase sqLiteDatabase) {
        insertTableUser(sqLiteDatabase, "Ngoc Anh", 21);
        insertTableUser(sqLiteDatabase, "Viet Phan", 22);
        insertTableUser(sqLiteDatabase, "Tien Huynh", 20);
        insertTableCompany(sqLiteDatabase, "Asian Tech", "Recoding History");
        insertTableCompany(sqLiteDatabase, "Gameloft", "Join in game");
        insertTableCompany(sqLiteDatabase, "FPT Software", "No Slogan");
        insertTableEmployee(sqLiteDatabase, 1, 1);
        insertTableEmployee(sqLiteDatabase, 2, 2);
        insertTableEmployee(sqLiteDatabase, 3, 3);
    }

    private void insertTableUser(SQLiteDatabase db, String name, int age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_USER, name);
        contentValues.put(AGE, age);
        db.insert(TABLE_USER, null, contentValues);
    }

    private void insertTableCompany(SQLiteDatabase sqLiteDatabase, String name, String slogan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COMPANY, name);
        contentValues.put(SLOGAN, slogan);
        sqLiteDatabase.insert(TABLE_COMPANY, null, contentValues);
    }

    private void insertTableEmployee(SQLiteDatabase sqLiteDatabase, int idUser, int idCompany) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_EMPLOYEE_USER, idUser);
        contentValues.put(ID_EMPLOYEE_COMPANY, idCompany);
        sqLiteDatabase.insert(TABLE_EMPLOYEE, null, contentValues);
    }
}
