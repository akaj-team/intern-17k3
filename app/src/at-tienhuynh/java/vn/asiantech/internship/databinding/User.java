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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.R;

/**
 * Created by TienHuynh on 11/01/2018.
 * AsianTech Co., Ltd
 */
public class User extends BaseObservable implements Parcelable {
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

    private String name;
    private String email;
    private String birthDay;
    private int gender;
    private String contactNumber;
    private boolean enableEditBtn;
    private String url;

    public User() {
        //no-opp
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

    public void setBirthDay(String birthDay) {
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
    public boolean isEnableEditBtn() {
        return enableEditBtn;
    }

    public void setEnableEditBtn(boolean enableEditBtn) {
        this.enableEditBtn = enableEditBtn;
        notifyPropertyChanged(BR.enableEditBtn);
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
    public void clearText(EnumType num) {
        if (num == EnumType.NAME) {
            setName("");
        } else if (num == EnumType.EMAIL) {
            setEmail("");
        } else {
            setContactNumber("");
        }
    }

    /**
     * show picker
     */
    public void showDatePicker(Context context) {
        // calendar
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        try {
            calendar.setTime(dateFormat.parse(getBirthDay()));
        } catch (ParseException e) {
            e.getMessage();
        }
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar.set(year, month, day, 0, 0, 0);
                        setBirthDay(dateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    /**
     * Update User Profile
     */
    public void onUpdateUserClick(Context context) {
        Intent intent = new Intent(context, PreViewActivity.class);
        intent.putExtra(User.class.getSimpleName(), this);
        if (context instanceof EditInfoActivity) {
            ((EditInfoActivity) context).setResult(Activity.RESULT_OK, intent);
            ((EditInfoActivity) context).finish();
        }
    }

    /**
     * Edit user when click Edit in Profile Activity
     */
    public void onEditUserClick(Context context) {
        Intent intent = new Intent(context, EditInfoActivity.class);
        intent.putExtra(User.class.getSimpleName(), this);
        if (context instanceof PreViewActivity) {
            ((PreViewActivity) context).startActivityForResult(intent, PreViewActivity.EDIT_USER_REQUEST_CODE);
        }
    }

    /**
     * set Text Gender in Profile Activity
     */
    public String getValueToGender(Context context) {
        String genders[] = context.getResources().getStringArray(R.array.gender_array);
        return genders[gender];
    }

    /**
     * @param s   s is value of edit text
     * @param num type of editText
     */
    public void afterTextChange(Editable s, EnumType num) {
        if (num == EnumType.NAME) {
            name = s.toString();
        } else if (num == EnumType.EMAIL) {
            email = s.toString();
        } else {
            contactNumber = s.toString();
        }
        changeStatusUpdateButton();
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

    private void changeStatusUpdateButton() {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(contactNumber)) {
            setEnableEditBtn(true);
        } else {
            setEnableEditBtn(false);
        }
    }
}
