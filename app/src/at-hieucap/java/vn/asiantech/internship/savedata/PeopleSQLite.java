package vn.asiantech.internship.savedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import vn.asiantech.internship.model.Company;
import vn.asiantech.internship.model.People;

public class PeopleSQLite extends SQLiteOpenHelper {
    private static final String DATABASE = "company";
    // Table name
    private static final String TABLE_NAME_PEOPLE = "People";
    private static final String TABLE_NAME_COMPANY = "Company";
    private static final String TABLE_NAME_EMPLOYEE = "Employee";
    // Row in table User
    private static final String PEOPLE_ID = "IdPe";
    private static final String PEOPLE_NAME = "Name";
    private static final String PEOPLE_AGE = "Age";
    // Row in table Company
    private static final String COMPANY_ID = "IdCom";
    private static final String COMPANY_NAME = "NameCom";
    private static final String COMPANY_SLOGAN = "Slogan";
    // Row in table Employee
    private static final String EMPLOYEE_ID = "IdEm";
    private static final String EMPLOYEE_US = "IdUs";
    private static final String EMPLOYEE_COM = "IdCom";
    // Select data by this field
    private static final String SELECT_DATA = "Select * From ";
    private static final String COMPARE = " = '";

    private static final String CREATE_TABLE_PEOPLE = "CREATE TABLE "
            + TABLE_NAME_PEOPLE + "( "
            + PEOPLE_ID + " INTEGER PRIMARY KEY,"
            + PEOPLE_NAME + " TEXT NOT NULL,"
            + PEOPLE_AGE + " INTEGER NOT NULL)";
    private static final String CREATE_TABLE_COMPANY = "CREATE TABLE "
            + TABLE_NAME_COMPANY + "( "
            + COMPANY_ID + " INTEGER PRIMARY KEY,"
            + COMPANY_NAME + " TEXT NOT NULL,"
            + COMPANY_SLOGAN + " TEXT NOT NULL)";
    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE "
            + TABLE_NAME_EMPLOYEE + "( "
            + EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EMPLOYEE_US + " INTEGER NOT NULL, "
            + EMPLOYEE_COM + " INTEGER NOT NULL, "
            + "FOREIGN KEY (" + EMPLOYEE_US + ") REFERENCES "
            + TABLE_NAME_PEOPLE + "(" + PEOPLE_ID + "), "
            + "FOREIGN KEY (" + EMPLOYEE_COM + ") REFERENCES "
            + TABLE_NAME_COMPANY + "(" + COMPANY_ID + "))";

    private static final int DATABASE_VERSION = 1;

    PeopleSQLite(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PEOPLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMPANY);
        sqLiteDatabase.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EMPLOYEE);
        onCreate(sqLiteDatabase);
    }

    void insertUSER(int id, String name, int age) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PEOPLE_ID, id);
        contentValues.put(PEOPLE_NAME, name);
        contentValues.put(PEOPLE_AGE, age);
        sqLiteDatabase.insert(TABLE_NAME_PEOPLE, null, contentValues);
        sqLiteDatabase.close();
    }

    void insertCompany(int id, String name, String slogan) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COMPANY_ID, id);
        contentValues.put(COMPANY_NAME, name);
        contentValues.put(COMPANY_SLOGAN, slogan);
        sqLiteDatabase.insert(TABLE_NAME_COMPANY, null, contentValues);
        sqLiteDatabase.close();
    }

    void insertEmployee(int idUs, int idCom) {
        if (!checkExistsEmployee(idUs, idCom)) {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(EMPLOYEE_US, idUs);
            contentValues.put(EMPLOYEE_COM, idCom);
            sqLiteDatabase.insert(TABLE_NAME_EMPLOYEE, null, contentValues);
            sqLiteDatabase.close();
            contentValues.clear();
        }
    }

    ArrayList<People> getUserCompany() {
        ArrayList<People> userCompanies = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_DATA + TABLE_NAME_PEOPLE, null);
        cursor.moveToFirst();
        People people;
        while (!cursor.isAfterLast()) {
            people = new People();
            people.setIDPeople(cursor.getInt(cursor.getColumnIndex(PEOPLE_ID)));
            people.setNamePeople(cursor.getString(cursor.getColumnIndex(PEOPLE_NAME)));
            people.setAgePeople(cursor.getInt(cursor.getColumnIndex(PEOPLE_AGE)));
            userCompanies.add(people);
            cursor.moveToNext();
        }
        cursor.close();
        return userCompanies;
    }

    Company getCompany(int idCom) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_DATA + TABLE_NAME_COMPANY
                + " where " + COMPANY_ID + COMPARE + idCom + "'", null);
        cursor.moveToFirst();
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

    int getIdCompany(int idPeople) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_DATA + TABLE_NAME_EMPLOYEE
                + " where " + EMPLOYEE_US + COMPARE + idPeople + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() == 1) {
            int idCompany = cursor.getInt(cursor.getColumnIndex(EMPLOYEE_COM));
            cursor.close();
            return idCompany;
        } else {
            cursor.close();
            return 0;
        }
    }

    private boolean checkExistsEmployee(int idUs, int idCom) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_DATA + TABLE_NAME_EMPLOYEE
                + " where " + EMPLOYEE_US + COMPARE + idUs + "'"
                + " and " + EMPLOYEE_COM + COMPARE + idCom + "'", null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }
}
