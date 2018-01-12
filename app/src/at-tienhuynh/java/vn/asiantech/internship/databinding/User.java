package vn.asiantech.internship.databinding;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.R;

/**
 * Created by TienHuynh on 11/01/2018.
 * AsianTech Co., Ltd
 */
public class User extends BaseObservable implements Parcelable {
    public String name;
    public String email;
    public String birthDay;
    public int gender;
    public String contactNumber;

    public User() {
        //No-opp
    }

    public User(String name, String email, String birthDay, int gender, String contactNumber) {
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.gender = gender;
        this.contactNumber = contactNumber;
    }

    /**
     * Read parcel to send object
     *
     * @param in in
     */
    public User(Parcel in) {
        name = in.readString();
        email = in.readString();
        birthDay = in.readString();
        gender = in.readInt();
        contactNumber = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Bindable
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
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
    public String getBirthDay() {
        return birthDay;
    }


    private void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
        notifyPropertyChanged(BR.birthDay);
    }

    @Bindable
    public int getGender() {
        return gender;
    }


    private void setGender(int gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getContactNumber() {
        return contactNumber;
    }


    private void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        notifyPropertyChanged(BR.contactNumber);
    }

    /**
     * Clear edt when user click button
     *
     * @param edt edit text to clear
     */
    public void clearText(EditText edt) {
        edt.setText("");
    }

    /**
     * show picker
     *
     * @param edt edit text to display calender when user choose day picker
     */
    public void showDatePicker(final EditText edt) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(edt.getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {
                        setBirthDay(edt.getContext().getResources().getString(R.string.edittext_birthday, day, month + 1, year));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    /**
     * Update User Profile
     */
    public void updateUser(User user, Context context, Editable name, Editable mail, Editable date, Editable contactNumber) {
        setName(String.valueOf(name));
        setEmail(String.valueOf(mail));
        setBirthDay(String.valueOf(date));
        setContactNumber(String.valueOf(contactNumber));
        goToActivity(context, user);
    }

    public void goToActivity(Context context, User user) {
        Intent intent = new Intent(context, PreViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * Item Selected Spinner
     */
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        setGender(i);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(birthDay);
        parcel.writeInt(gender);
        parcel.writeString(contactNumber);
    }
}
