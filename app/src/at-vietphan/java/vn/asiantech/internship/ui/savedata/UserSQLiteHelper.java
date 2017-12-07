package vn.asiantech.internship.ui.savedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import vn.asiantech.internship.models.Company;
import vn.asiantech.internship.models.User;

/**
 * Created by vietphan on 07/12/2017
 * UserSQLiteHelper
 */
public class UserSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vietphan";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String ID_USER = "id";
    private static final String NAME_USER = "name";
    private static final String AGE = "age";
    private static final String TABLE_COMPANY = "company";
    private static final String ID_COMPANY = "id";
    private static final String NAME_COMPANY = "name";
    private static final String SLOGAN = "slogan";
    private static final String TABLE_EMPLOYEE = "employee";
    private static final String ID_EMPLOYEE = "id";
    private static final String ID_EMPLOYEE_USER = "idUser";
    private static final String ID_EMPLOYEE_COMPANY = "idCompany";
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
    private String sql;

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

    private void initData(SQLiteDatabase sqLiteDatabase) {
        insertTableUser(sqLiteDatabase, "Viet Phan", 21);
        insertTableUser(sqLiteDatabase, "Trang Le", 22);
        insertTableUser(sqLiteDatabase, "Lan Nguyen", 20);
        insertTableCompany(sqLiteDatabase, "Asian Tech", "Recoding History");
        insertTableCompany(sqLiteDatabase, "Huong Viet", "Recoding History");
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

    ArrayList<User> getListUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{ID_USER, NAME_USER, AGE}, null, null, null, null, null);
        int colID = cursor.getColumnIndex(ID_USER);
        int colName = cursor.getColumnIndex(NAME_USER);
        int colAge = cursor.getColumnIndex(AGE);
        ArrayList<User> userList = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            User user = new User(cursor.getInt(colID), cursor.getString(colName), cursor.getInt(colAge));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }

    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        sql = "SELECT * FROM " + TABLE_USER + " WHERE " + ID_USER + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(ID_USER)));
        user.setName(cursor.getString(cursor.getColumnIndex(NAME_USER)));
        user.setAge(cursor.getInt(cursor.getColumnIndex(AGE)));
        cursor.close();
        return user;
    }

    Company getCompanyByIdUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        sql = "SELECT * FROM " + TABLE_USER + " INNER JOIN " + TABLE_EMPLOYEE + " ON " + TABLE_USER + "." + ID_USER + " = " + TABLE_EMPLOYEE + "." + ID_EMPLOYEE_USER
                + " INNER JOIN " + TABLE_COMPANY + " ON " + TABLE_EMPLOYEE + "." + ID_EMPLOYEE_COMPANY + " = " + TABLE_COMPANY + "." + ID_COMPANY
                + " WHERE " + TABLE_USER + "." + ID_USER + " = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Company company = new Company();
        company.setId(cursor.getInt(cursor.getColumnIndex(ID_COMPANY)));
        company.setName(cursor.getString(cursor.getColumnIndex(NAME_COMPANY)));
        company.setSlogan(cursor.getString(cursor.getColumnIndex(SLOGAN)));
        cursor.close();
        return company;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(sqLiteDatabase);
    }
}
