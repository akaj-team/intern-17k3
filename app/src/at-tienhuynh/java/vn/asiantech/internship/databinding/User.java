package vn.asiantech.internship.databinding;

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
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.R;

/**
 * Created by TienHuynh on 11/01/2018.
 * AsianTech Co., Ltd
 */
public class User extends BaseObservable implements Parcelable {
    public static final int REQUEST_CODE = 1;
    private String name;
    private String email;
    private String birthDay;
    private int gender;
    private String contactNumber;
    private boolean isBtnEnable;
    private String url;

    public User() {
        //get Current Day
        getCurrentDay();
    }

    public User(String name, String email, String birthDay, int gender, String contactNumber, String url) {
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.url = url;

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


    public void setGender(int gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getContactNumber() {
        return contactNumber;
    }


    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        notifyPropertyChanged(BR.contactNumber);
    }

    @Bindable
    public boolean isBtnEnable() {
        return isBtnEnable;
    }

    private void setBtnEnable(boolean btnEnable) {
        isBtnEnable = btnEnable;
        notifyPropertyChanged(BR.btnEnable);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    /**
     * Clear edt when user click button
     */
    public void clearText(int type) {
        //type = 0 is name
        //type = 1 is email
        if (type == 0) {
            setName("");
        } else if (type == 1) {
            setEmail("");
        } else {
            setContactNumber("");
        }

    }

    private void getCurrentDay() {
        // calendar
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        setBirthDay(String.valueOf(day).concat("/").concat(String.valueOf(month + 1)).concat("/").concat(String.valueOf(year)));
    }

    /**
     * show picker
     *
     * @param edt edit text to display calender when user choose day picker
     */
    public void showDatePicker(final TextView edt) {
        // calendar
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //show current day
        edt.setText(edt.getContext().getResources().getString(R.string.edittext_birthday, day, month + 1, year));
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
    public void updateUser(User user, Context context) {
        Intent intent = new Intent(context, PreViewActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        if (context instanceof EditInfoActivity) {
            ((EditInfoActivity) context).setResult(Activity.RESULT_OK, intent);
            ((EditInfoActivity) context).finish();
        }
    }

    /**
     * Edit user when click Edit in Profile Activity
     */
    public void editUser(User user, Context context) {
        Intent intent = new Intent(context, EditInfoActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        ((PreViewActivity) context).startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * set Text Gender in Profile Activity
     */
    public String getValueToGender() {
        if (gender == 0) {
            return "Male";
        } else {
            return "Female";
        }
    }

    /**
     * @param s    s is value of edit text
     * @param type type of editText
     */
    public void afterTextChange(Editable s, int type) {
        // type = 0 is name
        // type = 1 is email
        // type = 2 is birthDay
        if (type == 0) {
            name = s.toString();
        } else if (type == 1) {
            email = s.toString();
        } else {
            contactNumber = s.toString();
        }
        checkEmptyEditText();
    }

    private void checkEmptyEditText() {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(contactNumber)) {
            setBtnEnable(true);
        } else {
            setBtnEnable(false);
        }
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
        parcel.writeString(url);
    }
}
