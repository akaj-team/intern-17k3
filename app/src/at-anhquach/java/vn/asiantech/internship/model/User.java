package vn.asiantech.internship.model;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.data_binding.EditProfileActivity;
import vn.asiantech.internship.ui.data_binding.PreEditProfileActivity;

/**
 * Define class User
 */
public class User extends BaseObservable {
    private int id;
    private String fullname;
    private String birthday;
    private String email;
    private String gender;
    private String contactnumber;
    private int age;
    private String usename;
    private String password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    @Bindable
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    @Bindable
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    @Bindable
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;

    }

    @Bindable
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    @Bindable
    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void clearEditText(EditText edt) {
        edt.setText("");
    }

    public void startActivityPreEdit(Context context) {
        Intent intent = new Intent(context, PreEditProfileActivity.class);
        context.startActivity(intent);
    }

    public void startActivityEdit(Context context) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        context.startActivity(intent);
    }

    public void showDatePicker(final EditText edt, final Context context) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {
                        edt.setText(context.getResources().getString(R.string.edittext_birthday, day, month + 1, year));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}
