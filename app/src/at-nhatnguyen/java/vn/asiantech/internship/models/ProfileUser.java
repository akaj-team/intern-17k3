package vn.asiantech.internship.models;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.databinding.DataBindingActivity;
import vn.asiantech.internship.ui.databinding.EnumType;
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
    private int gender;
    private String urlAvatar;
    private String name;
    private String email;
    private String birthDate;
    private String phoneNumber;
    private boolean enableSubmitBtn;

    private ProfileUser(Parcel in) {
        name = in.readString();
        email = in.readString();
        birthDate = in.readString();
        gender = in.readInt();
        phoneNumber = in.readString();
        urlAvatar = in.readString();
    }

    public ProfileUser(String name, String email, int gender, String phoneNumber, String urlAvatar) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.urlAvatar = urlAvatar;

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        calendar.set(year, month, day);
        setBirthDate(String.valueOf(format.format(calendar.getTime())));
        updateStatusOfSubmitBtn();
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

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    @Bindable
    public boolean isEnableSubmitBtn() {
        return enableSubmitBtn;
    }

    public void setEnableSubmitBtn(boolean enableSubmitBtn) {
        this.enableSubmitBtn = enableSubmitBtn;
        notifyPropertyChanged(BR.enableSubmitBtn);
    }

    @Bindable
    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
        notifyPropertyChanged(BR.urlAvatar);
    }

    public void onCleanNameClick() {
        setName("");
    }

    public void onCleanEmailClick() {
        setEmail("");
    }

    public void onCleanPhoneNumberClick() {
        setPhoneNumber("");
    }

    public String displayGender(Context context) {
        String genders[] = context.getResources().getStringArray(R.array.spinner_gender);
        return genders[gender];
    }

    public void onShowDatePickerDialogClick(View v) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                setBirthDate(String.valueOf(format.format(calendar.getTime())));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void onUpdateProfileClick(Context context) {
        if (context instanceof DataBindingActivity) {
            Intent intent = new Intent();
            intent.putExtra(ProfileUser.class.getSimpleName(), this);
            ((DataBindingActivity) context).setResult(Activity.RESULT_OK, intent);
            ((DataBindingActivity) context).finish();
        }
    }

    public void onEditProfileClick(Context context) {
        Intent intent = new Intent(context, DataBindingActivity.class);
        intent.putExtra(ProfileUser.class.getSimpleName(), this);
        ((AppCompatActivity) context).startActivityForResult(intent, ProfileActivity.EDIT_PROFILE_REQUEST_CODE);
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
        dest.writeString(urlAvatar);
    }

    public void updateStatusOfSubmitBtn() {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phoneNumber)) {
            setEnableSubmitBtn(false);
        } else {
            setEnableSubmitBtn(true);
        }
    }

    public void afterTextChange(Editable s, int typeItem) {
        if (typeItem == EnumType.NAME) {
            setName(s.toString());
        } else if (typeItem == EnumType.EMAIL) {
            setEmail(s.toString());
        } else if (typeItem == EnumType.BIRTHDAY) {
            setBirthDate(s.toString());
        } else if (typeItem == EnumType.CONTACT_NUMBER) {
            setPhoneNumber(s.toString());
        }
        updateStatusOfSubmitBtn();
    }
}
