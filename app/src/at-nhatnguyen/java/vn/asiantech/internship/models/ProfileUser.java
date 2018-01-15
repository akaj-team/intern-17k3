package vn.asiantech.internship.models;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.ui.databinding.ProfileActivity;

/**
 * profile user
 * Created by hoangnhat on 12/01/2018.
 */

public class ProfileUser extends BaseObservable implements Parcelable {
    public static final Creator<ProfileUser> CREATOR = new Creator<ProfileUser>() {
        @Override
        public ProfileUser createFromParcel(Parcel in) {
            return new ProfileUser(in);
        }

        @Override
        public ProfileUser[] newArray(int size) {
            return new ProfileUser[size];
        }
    };
    private String name;
    private String email;
    private String birthDate;
    public int gender;
    private String phoneNumber;
    private boolean isTextEmpty;


    public ProfileUser(String name, String email, int gender, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        updateBirthDate(year, month, day);
        checkEmpty();
    }

    protected ProfileUser(Parcel in) {
        name = in.readString();
        email = in.readString();
        birthDate = in.readString();
        gender = in.readInt();
        phoneNumber = in.readString();
    }

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

    public void setGender(int gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    public void setSelectedGender(int position){
        setGender(position);
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    @Bindable
    public boolean isTextEmpty() {
        return isTextEmpty;
    }

    public void setTextEmpty(boolean textEmpty) {
        isTextEmpty = textEmpty;
        notifyPropertyChanged(BR.textEmpty);
    }

    public void cleanName() {
        setName("");
    }

    public void cleanEmail() {
        setEmail("");
    }

    public void cleanPhoneNumber() {
        setPhoneNumber("");
    }

    public String displayGender() {
        if (getGender() == 0) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public void showDialog(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                updateBirthDate(year, month, dayOfMonth);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void updateBirthDate(int year, int month, int day) {
        setBirthDate(String.valueOf(year).concat("-").concat(String.valueOf(month + 1).concat("-").concat(String.valueOf(day))));
    }

    public void updateProfile(Context context, ProfileUser profileUser) {
        setName(name);
        setEmail(email);
        setBirthDate(birthDate);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("profile", profileUser);
        context.startActivity(intent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(birthDate);
        dest.writeInt(gender);
        dest.writeString(phoneNumber);
    }

    public void checkEmpty() {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phoneNumber)) {
            setTextEmpty(false);
        } else {
            setTextEmpty(true);
        }
    }

    public void afterTextChange(Editable s, int typeItem) {
        if (typeItem == 0) {
            name = s.toString();
        } else if (typeItem == 1) {
            email = s.toString();
        } else if (typeItem == 2) {
            birthDate = s.toString();
        } else if (typeItem == 3) {
            phoneNumber = s.toString();
        }
        checkEmpty();
    }
}
