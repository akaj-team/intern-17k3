package vn.asiantech.internship.ui.databind;

import android.app.DatePickerDialog;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import vn.asiantech.internship.BR;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 12/01/2018.
 */

public class User extends BaseObservable {
    private String userName;
    private String email;
    private String birthDate;
    private int gender;
    private String contact;

    public User() {
    }

    public User(String userName, String email, String birthDate, int gender, String contact) {
        this.userName = userName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.contact = contact;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String mUserName) {
        this.userName = mUserName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        notifyPropertyChanged(BR.birthDate);
    }

    @Bindable
    public int getGender() {
        return gender;
    }

    public void setmGender(int mGender) {
        this.gender = mGender;
    }

    @Bindable
    public String getmContact() {
        return contact;
    }

    public void setmContact(String mContact) {
        this.contact = mContact;
    }

    public void onChangeNameClick(Editable edtName) {
        setUserName(String.valueOf(edtName.toString()));
        Log.d("sssss", "onChangeNameClick:" + userName);
    }

    public void afterTextChanged(Editable s) {
        userName = s.toString();
        Log.d("sssss", "afterTextChanged: " + userName);
    }

    private void updateBirthDate(int date, int month, int year) {
        setBirthDate(String.valueOf(date).concat("-").concat(String.valueOf(month + 1).concat("-").concat(String.valueOf(year))));
    }

    public void showDialog(final View view) {
        Calendar calender = Calendar.getInstance();
        int date = calender.get(Calendar.DAY_OF_MONTH);
        int month = calender.get(Calendar.MONTH);
        int year = calender.get(Calendar.YEAR);

        DatePickerDialog mDatePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dateOfMonth) {
                updateBirthDate(year, month, dateOfMonth);
            }
        }, year, month, date);
        mDatePickerDialog.show();
    }

    public void cleanProfileName(Editable s) {
        setUserName(" ");
    }

    public void afterChangeText(Editable edtAfter, int type) {
        if (type == 1) {
            userName = edtAfter.toString();
        } else if (type == 2) {
            email = edtAfter.toString();
        } else if (type == 3) {
            birthDate = edtAfter.toString();
        } else if (type == 5) {
            contact = edtAfter.toString();
        }
    }
    public void chooiseSpinerGender(View view,int type){

    }
}
