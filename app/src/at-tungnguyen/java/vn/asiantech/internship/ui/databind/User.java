package vn.asiantech.internship.ui.databind;

import android.app.DatePickerDialog;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

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
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public void cleanProfileName(Editable edtName) {
        setUserName(" ");
    }

    public void cleanEmail(Editable edtEmail) {
        setEmail(" ");
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
        Log.d("ssss", "afterChangeText: " + userName + email + birthDate + contact);
    }

    public void setSpinerGender(TextView v) {

    }

    public void onItemSelected(int position) {
        Log.d("bbbb", "onItemSelected: " + position);
    }

    @InverseBindingAdapter(attribute = "selection", event = "selectionAttrChanged")
    public static String getSelectedValue(AdapterView view) {
        return (String) view.getSelectedItem();
    }

    @BindingAdapter(value = {"selection", "selectionAttrChanged", "adapter"}, requireAll = false)
    public static void setAdapter(AdapterView view, String newSelection, final InverseBindingListener bindingListener, ArrayAdapter adapter) {
        view.setAdapter(adapter);
        view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bindingListener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });
        if (newSelection != null) {
            int pos = ((ArrayAdapter) view.getAdapter()).getPosition(newSelection);
            view.setSelection(pos);
        }
    }
}
