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
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.databinding.EditProfileActivity;
import vn.asiantech.internship.ui.databinding.PreviewProfileActivity;

/**
 * Created by vietphan on 15/01/2017.
 * Class ProfileUser
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
    private boolean enableSubmit;
    private String name;
    private String email;
    private String phone;
    private String imageUrl;
    private String birthDate;
    private int genDer;

    public ProfileUser() {
        //No-op
    }

    private ProfileUser(Parcel in) {
        enableSubmit = in.readByte() != 0;
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        imageUrl = in.readString();
        birthDate = in.readString();
        genDer = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (enableSubmit ? 1 : 0));
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(imageUrl);
        dest.writeString(birthDate);
        dest.writeInt(genDer);
    }

    @Bindable
    public boolean isEnableSubmit() {
        return enableSubmit;
    }

    public void setEnableSubmit(boolean enableSubmit) {
        this.enableSubmit = enableSubmit;
        notifyPropertyChanged(BR.enableSubmit);
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
    public int getGenDer() {
        return genDer;
    }

    public void setGenDer(int genDer) {
        this.genDer = genDer;
        notifyPropertyChanged(BR.genDer);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    public String onShowGender(Context context) {
        String genders[] = context.getResources().getStringArray(R.array.gender);
        return genders[genDer];
    }

    public void onEditProfileClick(Context context) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtra(ProfileUser.class.getSimpleName(), this);
        ((AppCompatActivity) context).startActivityForResult(intent, PreviewProfileActivity.EDIT_USER_REQUEST_CODE);
    }

    public void onUpdateProfileClick(Context context) {
        if (context instanceof EditProfileActivity) {
            Intent intent = new Intent();
            intent.putExtra(ProfileUser.class.getSimpleName(), this);
            ((EditProfileActivity) context).setResult(Activity.RESULT_OK, intent);
            ((EditProfileActivity) context).finish();
        }
    }

    public void afterTextChange(Editable text, TypeItem type) {
        switch (type) {
            case NAME:
                setName(String.valueOf(text));
                break;
            case EMAIL:
                setEmail(String.valueOf(text));
                break;
            case PHONE:
                setPhone(String.valueOf(text));
        }
        setEnableSubmit(!(TextUtils.isEmpty(name.trim()) || TextUtils.isEmpty(email.trim()) || TextUtils.isEmpty(phone.trim())));
    }

    public void onShowDatePickerDialogClick(final TextView edt) {
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        try {
            calendar.setTime(dateFormat.parse(getBirthDate()));
        } catch (ParseException e) {
            e.getMessage();
        }
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(edt.getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar.set(year, month, day, 0, 0, 0);
                        setBirthDate(dateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public enum TypeItem {
        NAME,
        EMAIL,
        PHONE
    }
}
