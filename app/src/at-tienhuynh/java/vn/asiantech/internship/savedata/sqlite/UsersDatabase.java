package vn.asiantech.internship.savedata.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.savedata.models.Company;
import vn.asiantech.internship.savedata.models.Employee;
import vn.asiantech.internship.savedata.models.Users;

/**
 * Created at 2017
 * Created by jackty on 06/12/2017.
 */
public class UsersDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tienhuynh3";
    private static final String TABLE_USER = "users";
    private static final String TABLE_COMPANY = "Company";
    private static final String TABLE_EMPLOYEE = "employee";
    private static final String KEY_USERS_ID = "id";
    private static final String KEY_USERS_NAME = "name";
    private static final String KEY_USERS_AGE = "age";
    private static final String KEY_COMPANY_ID = "id";
    private static final String KEY_COMPANY_NAME = "name";
    private static final String KEY_COMPANY_SLOGAN = "slogan";
    private static final String KEY_EMPLOYEE_ID = "id";
    private static final String KEY_EMPLOYEEY_ID_USER = "id_user";
    private static final String KEY_EMPLOYEE_ID_COMPANY = "id_company";

    public UsersDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_USERS = "create table " + TABLE_USER + "(" + KEY_USERS_ID + " integer primary key autoincrement, "
                + KEY_USERS_NAME + " text, "
                + KEY_USERS_AGE + " integer" + ")";

        String CREATE_TABLE_COMPANY = "create table " + TABLE_COMPANY + "(" + KEY_COMPANY_ID + " integer primary key autoincrement, "
                + KEY_USERS_NAME + " text, " + KEY_COMPANY_SLOGAN + " text" + " )";

        String CREATE_TABLE_EMPLOYEE = "create table " + TABLE_EMPLOYEE + "(" + KEY_EMPLOYEE_ID + " integer primary key autoincrement, "
                + KEY_EMPLOYEEY_ID_USER + " integer, " + KEY_EMPLOYEE_ID_COMPANY + " integer, " + " foreign key ("
                + KEY_EMPLOYEEY_ID_USER + ") references " + TABLE_USER + "(" + KEY_USERS_ID + "), " + " foreign key ("
                + KEY_EMPLOYEE_ID_COMPANY + ") references " + TABLE_COMPANY + "(" + KEY_COMPANY_ID + ")" + " )";

        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
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

    /**
     * Add Users List
     */
    public void addUsers(Users users) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERS_NAME, users.getName());
        values.put(KEY_USERS_AGE, users.getAge());
        sqLiteDatabase.insert(TABLE_USER, null, values);
        sqLiteDatabase.close();
    }

    /**
     * Add Company List
     */
    public void addCompany(Company company) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_NAME, company.getName());
        values.put(KEY_COMPANY_SLOGAN, company.getSlogan());
        sqLiteDatabase.insert(TABLE_COMPANY, null, values);
        sqLiteDatabase.close();
    }

    /**
     * Add Employee List
     */
    public void addEmployee(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMPLOYEEY_ID_USER, employee.getIdUser());
        values.put(KEY_EMPLOYEE_ID_COMPANY, employee.getIdCompany());
        sqLiteDatabase.insert(TABLE_EMPLOYEE, null, values);
        sqLiteDatabase.close();
    }

    /**
     * Get All Users
     */
    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        String selectAllUsers = "select * from " + TABLE_USER;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectAllUsers, null);
        if (cursor.moveToFirst()) {
            do {
                Users users = new Users();
                users.setId(cursor.getInt(0));
                users.setName(cursor.getString(1));
                users.setAge(cursor.getInt(2));
                usersList.add(users);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usersList;
    }

    /**
     * Get Single Company
     */
    public Company getCompany(int id_user) {
        String selectCompany = "select * from " + TABLE_COMPANY + " where " + KEY_COMPANY_ID +
                " in (select " + KEY_EMPLOYEE_ID_COMPANY + " from employee where id_user =" + id_user + ")";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectCompany, null);
        Company company = new Company();
        if (cursor != null) {
            cursor.moveToFirst();
            company.setId(cursor.getInt(0));
            company.setName(cursor.getString(1));
            company.setSlogan(cursor.getString(2));
            cursor.close();
        }
        return company;
    }
}
