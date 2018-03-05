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
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.R;

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
    private String avatar;
    private boolean enableEditBtn;

    public User() {
    }

    protected User(Parcel in) {
        userName = in.readString();
        email = in.readString();
        birthDate = in.readString();
        gender = in.readInt();
        contact = in.readString();
        avatar = in.readString();
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
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
        notifyPropertyChanged(BR.contact);
    }

    /**
     * Check Empty Edit text
     */
    @Bindable
    public boolean  isEnableEditBtn() {
        return enableEditBtn;
    }

    private void setEnableEditBtn(boolean enableEditBtn) {
        this.enableEditBtn = enableEditBtn;
        notifyPropertyChanged(BR.enableEditBtn);
    }

    /**
     * Show Dialog DataTimePicker
     */
    public void onShowDatePickerClick(final View view) {
        final Calendar calender = Calendar.getInstance();
        final SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        try {
            calender.setTime(format.parse(getBirthDate()));
        } catch (ParseException e) {
            Log.e("Tag", e + "");
        }
        int date = calender.get(Calendar.DAY_OF_MONTH);
        int month = calender.get(Calendar.MONTH);
        int year = calender.get(Calendar.YEAR);
        DatePickerDialog mDatePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dateOfMonth) {
                setBirthDate(String.valueOf(format.format(calender.getTime())));
            }
        }, year, month, date);
        mDatePickerDialog.show();
    }

    public void onCleanProfileNameClick() {
        setUserName("");
    }

    public void onCleanEmailClick() {
        setEmail("");
    }

    public void onCleanContact() {
        setContact("");
    }

    /**
     * Set AfterChangeText Edit text
     * set Aft
     */
    public void afterChangeText(Editable edtAfter, TypeItem type) {
        switch (type) {
            case NAME:
                setUserName(String.valueOf(edtAfter));
                break;
            case EMAIL:
                setEmail(String.valueOf(edtAfter));
                break;
            case BIRTHDAY:
                setBirthDate(String.valueOf(edtAfter));
                break;
            case CONTACT:
                setContact(String.valueOf(edtAfter));
                break;
        }
        changeStatusUpdateButton();
    }

    /**
     * Send Data EditUser to PreViewUser
     */
    public void onPreviewUserClick(User user, Context context) {
        Intent intent = new Intent(context, PreviewUserActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        ((EditUserActivity) context).setResult(Activity.RESULT_OK, intent);
        ((EditUserActivity) context).finish();
    }

    /**
     * Send Data PreViewUser to EditUser
     */
    public void onEditUserClick(User user, Context context) {
        Intent intent = new Intent(context, EditUserActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        if (context instanceof PreviewUserActivity) {
            ((PreviewUserActivity) context).startActivityForResult(intent, PreviewUserActivity.EDIT_USER_REQUEST_CODE);
        }
    }

    /**
     * Select Item Gender
     */
    public String getValueGender(Context context) {
        return context.getResources().getStringArray(R.array.gender_array)[gender];
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
        parcel.writeString(avatar);
    }

    public enum TypeItem {
        NAME,
        EMAIL,
        BIRTHDAY,
        CONTACT
    }

    /**
     * Check Empty username , email , contact
     */
    private void changeStatusUpdateButton() {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(contact)) {
            setEnableEditBtn(false);
        } else {
            setEnableEditBtn(true);
        }
    }
}
