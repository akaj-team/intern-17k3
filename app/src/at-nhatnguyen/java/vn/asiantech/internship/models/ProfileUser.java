package vn.asiantech.internship.models;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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
    public String name;
    public String email;
    public String birthDate;
    public int gender;
    public String phoneNumber;

    public ProfileUser(String name, String email, String birthDate, int gender, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
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
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.name);
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

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    public void cleanName(EditText edtName) {
        edtName.setText("");
    }

    public void cleanEmail(EditText edtEmail) {
        edtEmail.setText("");
    }

    public void cleanPhoneNumber(EditText edtPhoneNumber) {
        edtPhoneNumber.setText("");
    }

    public void updateBirthDate(EditText edtBirthDate, int year, int month, int day) {
        edtBirthDate.setText(String.valueOf(year).concat("-").concat(String.valueOf(month + 1).concat("-").concat(String.valueOf(day))));
    }

    public void showDialog(final EditText edtBirthDate) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(edtBirthDate.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                updateBirthDate(edtBirthDate, year, month, dayOfMonth);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void updateProfile(Button btnUpdate, EditText name, EditText email, EditText birthDate, EditText phoneNumber, ProfileUser profileUser) {
        setName(name.getText().toString());
        setEmail(email.getText().toString());
        setBirthDate(birthDate.getText().toString());
        setPhoneNumber(phoneNumber.getText().toString());
        Intent intent = new Intent(btnUpdate.getContext(), ProfileActivity.class);
        intent.putExtra("profile", profileUser);
        btnUpdate.getContext().startActivity(intent);
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
}
