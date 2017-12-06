package vn.asiantech.internship.ui.savedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import vn.asiantech.internship.models.Company;
import vn.asiantech.internship.models.UserCompany;

/**
 * Created by hoangnhat on 06/12/2017.
 * Create database
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
    //Row in table Company
    private static final String COMPANY_ID = "IdCom";
    private static final String COMPANY_NAME = "NameCom";
    private static final String COMPANY_SLOGAN = "Slogan";


    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_NAME_USER + "( "
            + USER_ID + " INTEGER PRIMARY KEY,"
            + USER_NAME + " TEXT NOT NULL,"
            + USER_AGE + " INTEGER NOT NULL)";
    private static final String CREATE_TABLE_COMPANY = "CREATE TABLE " + TABLE_NAME_COMPANY + "( "
            + COMPANY_ID + " INTEGER PRIMARY KEY,"
            + COMPANY_NAME + " TEXT NOT NULL,"
            + COMPANY_SLOGAN + " TEXT NOT NULL)";

    //Version
    private static final int DATABASE_VERSION = 1;

    UserCompanySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMPANY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TO Do
    }

    void insertUSER(int id, String name, int age) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, id);
        contentValues.put(USER_NAME, name);
        contentValues.put(USER_AGE, age);
        sqLiteDatabase.insert(TABLE_NAME_USER, null, contentValues);
    }

    void insertCompany(int id, String name, String slogan) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COMPANY_ID, id);
        contentValues.put(COMPANY_NAME, name);
        contentValues.put(COMPANY_SLOGAN, slogan);
        sqLiteDatabase.insert(TABLE_NAME_COMPANY, null, contentValues);
    }

    ArrayList<UserCompany> getUserCompany() {
        ArrayList<UserCompany> userCompanies = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_USER, null);
        cursor.moveToFirst();
        UserCompany userCompany;
        while (!cursor.isAfterLast()) {
            userCompany = new UserCompany();
            userCompany.setIDUser(cursor.getInt(cursor.getColumnIndex(USER_ID)));
            userCompany.setNameUser(cursor.getString(cursor.getColumnIndex(USER_NAME)));
            userCompany.setAgeUser(cursor.getInt(cursor.getColumnIndex(USER_AGE)));
            userCompanies.add(userCompany);
            cursor.moveToNext();
        }
        cursor.close();
        return userCompanies;
    }

    Company getCompany(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_COMPANY + " where " + COMPANY_ID + " = '" + id + "'", null);
        if (cursor.getCount() == 1) {
            Company company = new Company();
            company.setIdCompany(cursor.getInt(cursor.getColumnIndex(COMPANY_ID)));
            company.setNameCompany(cursor.getString(cursor.getColumnIndex(COMPANY_NAME)));
            company.setSloganCompany(cursor.getString(cursor.getColumnIndex(COMPANY_SLOGAN)));
            cursor.close();
            return company;
        } else {
            cursor.close();
            return null;
        }

    }
}
