package vn.asiantech.internship.ui.savedata.ex3.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.ui.savedata.ex3.model.Company;
import vn.asiantech.internship.ui.savedata.ex3.model.Employee;
import vn.asiantech.internship.ui.savedata.ex3.model.User;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 08/12/2017.
 */

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_USER = "User";
    private static final String TABLE_COMPANY = "Company";
    private static final String TABLE_EMPLOYEE = "Employee";
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_AGE = "age";
    private static final String COMPANY_ID = "id";
    private static final String COMPANY_NAME = "name";
    private static final String COMPANY_SLOGAN = "slogan";
    private static final String EMPLOYEE_ID = "id";
    private static final String EMPLOYEE_ID_USER = "id_user";
    private static final String EMPLOYEE_ID_COMPANY = "id_company";

    private static final int DATABASE_VERSION = 1;
    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "( "
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " TEXT NOT NULL,"
            + USER_AGE + " INTEGER NOT NULL);";

    String CREATE_TABLE_COMPANY = "CREATE TABLE " + TABLE_COMPANY + "( "
            + COMPANY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COMPANY_NAME + " TEXT ,"
            + COMPANY_SLOGAN + " TEXT );";

    String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + TABLE_EMPLOYEE + "( "
            + EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EMPLOYEE_ID_USER + " INTEGER,"
            + EMPLOYEE_ID_COMPANY + " INTEGER );"
            + " FOREIGN KEY (" + EMPLOYEE_ID_USER + ") REFERENCES " + CREATE_TABLE_USER + "(" + USER_ID + "));"
            + " FOREIGN KEY (" + EMPLOYEE_ID_COMPANY + ") REFERENCES " + CREATE_TABLE_COMPANY + "(" + COMPANY_ID + "));";

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMPANY);
        sqLiteDatabase.execSQL(CREATE_TABLE_EMPLOYEE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getId());
        values.put(USER_NAME, user.getName());
        values.put(USER_AGE, user.getAge());
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_ID, company.getId());
        values.put(COMPANY_NAME, company.getName());
        values.put(COMPANY_SLOGAN, company.getSlogan());
        db.insert(TABLE_COMPANY, null, values);
        db.close();
    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_ID, employee.getId());
        values.put(EMPLOYEE_ID_USER, employee.getIDUser());
        values.put(EMPLOYEE_ID_COMPANY, employee.getIDCompany());
        db.insert(TABLE_EMPLOYEE, null, values);
        db.close();
    }

    public ArrayList<User> getUser() {
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setAge(cursor.getInt(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return (ArrayList<User>) userList;
    }

    public Company getCompany(int idUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_COMPANY + " INNER JOIN " + TABLE_EMPLOYEE + " ON " + TABLE_COMPANY + "." + COMPANY_ID + " = " + TABLE_EMPLOYEE + "." + EMPLOYEE_ID_COMPANY
                + " WHERE " + EMPLOYEE_ID_USER + " = " + idUser;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor != null)
            cursor.moveToFirst();

        Company company = new Company(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return company;
    }

}