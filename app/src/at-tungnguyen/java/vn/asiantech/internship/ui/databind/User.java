package vn.asiantech.internship.ui.databind;

import android.app.Activity;
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

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 12/01/2018.
 */

public class User extends BaseObservable implements Parcelable {
    private String userName;
    private String email;
    private String birthDate;
    private int gender;
    private String contact;
    private String url;
    private boolean isCheckEmpty = true;

    public static final int REQUEST_CODE_EDIT = 123;

    public User() {
    }

    public User(String userName, String email, String birthDate, int gender, String contact,String url) {
        this.userName = userName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.contact = contact;
        this.url = url;
    }

    protected User(Parcel in) {
        userName = in.readString();
        email = in.readString();
        birthDate = in.readString();
        gender = in.readInt();
        contact = in.readString();
        url = in.readString();
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

    public void setGender(int gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }
    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
        notifyPropertyChanged(BR.contact);
    }

    @Bindable
    public boolean isCheckEmpty() {
        return isCheckEmpty;
    }

    private void setCheckEmpty(boolean checkEmpty) {
        isCheckEmpty = checkEmpty;
        notifyPropertyChanged(BR.checkEmpty);

    }

    public void afterTextChanged(Editable s) {
        userName = s.toString();
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

    public void cleanProfileName() {
        setUserName("");
    }

    public void cleanEmail() {
        setEmail("");
    }

    public void cleanContact() {
        setContact("");
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
        checkEmptyUtil();
    }

    public void setPriviewUser(User user, Context context) {
        Intent intent = new Intent(context, PriviewUserActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        ((EditUserActivity) context).setResult(Activity.RESULT_OK, intent);
        ((EditUserActivity) context).finish();

    }

    public void editUser(User user, Context context) {
        Intent intent = new Intent(context, EditUserActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        ((PriviewUserActivity) context).startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    public String selectGender() {
        if (gender == 0) {
            return "Male";
        } else
            return "Female";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(email);
        parcel.writeString(birthDate);
        parcel.writeInt(gender);
        parcel.writeString(contact);
        parcel.writeString(url);
    }

    public void checkEmptyUtil() {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(contact)) {
            setCheckEmpty(false);
        } else {
            setCheckEmpty(true);
        }
    }
}
