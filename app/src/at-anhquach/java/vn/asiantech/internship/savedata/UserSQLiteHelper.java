package vn.asiantech.internship.savedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import vn.asiantech.internship.model.Company;
import vn.asiantech.internship.model.User;

/**
 * Class create and handle database
 */
public class UserSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AnhQuach";
    private static final String TABLE_USER = "users";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_COMPANY = "company";
    private static final String TABLE_EMPLOYEE = "employee";

    private static final String ID_USER = "id";
    private static final String ID_EMPLOYEE = "id";
    private static final String ID_COMPANY = "id";
    private static final String NAME_USER = "name";
    private static final String NAME_COMPANY = "name";
    private static final String AGE = "age";
    private static final String SLOGAN = "sologan";
    private static final String ID_EMPLOYEE_USER = "id_user";
    private static final String ID_EMPLOYEE_COMPANY = "id_company";
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

    UserSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMPANY);
        sqLiteDatabase.execSQL(CREATE_TABLE_EMPLOYEE);
        initData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
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

    ArrayList<User> getListUser() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_USER, new String[]{ID_USER, NAME_USER, AGE}, null, null, null, null, null);
        cursor.moveToFirst();
        User user;
        ArrayList<User> userList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(ID_USER)));
            user.setName(cursor.getString(cursor.getColumnIndex(NAME_USER)));
            user.setAge(cursor.getInt(cursor.getColumnIndex(AGE)));
            userList.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return userList;
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

    Company getCompanyByUserId(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + TABLE_COMPANY + "." + ID_COMPANY + "," + NAME_COMPANY + "," + SLOGAN + " FROM " + TABLE_COMPANY + ", " + TABLE_EMPLOYEE
                + " WHERE " + TABLE_COMPANY + "." + ID_COMPANY + " = " + TABLE_EMPLOYEE + "." + ID_EMPLOYEE + " AND " + ID_EMPLOYEE_USER + " = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Company company = null;
        if (cursor != null) {
            cursor.moveToNext();
            company = new Company();
            company.setId(cursor.getInt(cursor.getColumnIndex(ID_COMPANY)));
            company.setName(cursor.getString(cursor.getColumnIndex(NAME_COMPANY)));
            company.setSlogan(cursor.getString(cursor.getColumnIndex(SLOGAN)));
            cursor.close();
        }
        return company;
    }
}
