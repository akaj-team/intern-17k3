package vn.asiantech.internship.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.internship.models.Company;
import vn.asiantech.internship.models.Person;

public class DBManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "phongle";
    private static final String TABLE_PERSON = "Person";
    private static final String COLUMN_PERSON_ID = "Id";
    private static final String COLUMN_PERSON_NAME = "Name";
    private static final String COLUMN_PERSON_AGE = "Age";
    private static final String TABLE_COMPANY = "Company";
    private static final String COLUMN_COMPANY_ID = "Id";
    private static final String COLUMN_COMPANY_NAME = "Name";
    private static final String COLUMN_COMPANY_SLOGAN = "Slogan";
    private static final String TABLE_EMPLOYEE = "Employee";
    private static final String COLUMN_EMPLOYEE_ID_PERSON = "Employee_Id_Person";
    private static final String COLUMN_EMPLOYEE_ID_COMPANY = "Employee_Id_Company";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTablePerson = "CREATE TABLE " + TABLE_PERSON
                + " (" + COLUMN_PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PERSON_NAME + " TEXT, "
                + COLUMN_PERSON_AGE + " INTEGER )";
        String createTableCompany = "CREATE TABLE " + TABLE_COMPANY
                + " (" + COLUMN_COMPANY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COMPANY_NAME + " TEXT, "
                + COLUMN_COMPANY_SLOGAN + " TEXT )";
        String createTableEmployee = "CREATE TABLE " + TABLE_EMPLOYEE
                + "(" + COLUMN_EMPLOYEE_ID_PERSON + " INTEGER NOT NULL,"
                + COLUMN_EMPLOYEE_ID_COMPANY + " INTEGER NOT NULL, "
                + "FOREIGN KEY (" + COLUMN_EMPLOYEE_ID_PERSON + ") REFERENCES " + TABLE_PERSON + "(" + COLUMN_PERSON_ID + "),"
                + "FOREIGN KEY (" + COLUMN_EMPLOYEE_ID_COMPANY + ") REFERENCES " + TABLE_COMPANY + "(" + COLUMN_COMPANY_ID + "),"
                + "PRIMARY KEY (" + COLUMN_EMPLOYEE_ID_PERSON + "," + COLUMN_EMPLOYEE_ID_COMPANY + "))";
        db.execSQL(createTablePerson);
        db.execSQL(createTableCompany);
        db.execSQL(createTableEmployee);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PERSON_NAME, person.getName());
        contentValues.put(COLUMN_PERSON_AGE, person.getAge());
        db.insert(TABLE_PERSON, null, contentValues);
        db.close();
    }

    public List<Person> getPerson() {
        List<Person> personList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + TABLE_PERSON;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setAge(cursor.getInt(2));
                personList.add(person);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return personList;
    }

    public void addCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMPANY_NAME, company.getName());
        contentValues.put(COLUMN_COMPANY_SLOGAN, company.getSlogan());
        db.insert(TABLE_COMPANY, null, contentValues);
        db.close();
    }

    public List<Company> getCompany() {
        List<Company> companyList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_COMPANY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Company company = new Company();
                company.setId(Integer.parseInt(cursor.getString(0)));
                company.setName(cursor.getString(1));
                company.setSlogan(cursor.getString(2));
                companyList.add(company);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return companyList;
    }

    public Company getCompanyById(int id) {
        Company company = new Company();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_COMPANY + " WHERE " + COLUMN_COMPANY_ID + " =?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor != null) {
            cursor.moveToFirst();
            company = new Company(id, cursor.getString(1), cursor.getString(2));
            cursor.close();
        }
        return company;
    }

    public List<Company> getCompanyByPersonId(int id) {
        List<Company> companyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT c." + COLUMN_COMPANY_NAME + ", c." + COLUMN_COMPANY_SLOGAN + " FROM " +TABLE_COMPANY
                + " AS c INNER JOIN " + TABLE_EMPLOYEE + " AS e ON e." + COLUMN_EMPLOYEE_ID_COMPANY + " = c." + COLUMN_COMPANY_ID
                + " WHERE e." + COLUMN_EMPLOYEE_ID_PERSON + " =?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            do {
                Company company = new Company(cursor.getString(0), cursor.getString(1));
                companyList.add(company);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return companyList;
    }

    public void addEmployee(Person person, Company company) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMPLOYEE_ID_PERSON, person.getId());
        contentValues.put(COLUMN_EMPLOYEE_ID_COMPANY, company.getId());
        db.insert(TABLE_EMPLOYEE, null, contentValues);
        db.close();
    }
}

