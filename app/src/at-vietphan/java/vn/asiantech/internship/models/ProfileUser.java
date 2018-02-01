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
import android.widget.DatePicker;
import android.widget.TextView;

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
    public String name;
    public String email;
    public String birthDate;
    public int genDer;
    public String phone;
    public String imageUrl;

    private ProfileUser(Parcel in) {
        name = in.readString();
        email = in.readString();
        birthDate = in.readString();
        genDer = in.readInt();
        phone = in.readString();
        imageUrl = in.readString();
    }

    public ProfileUser() {
        //No-op
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

    public void afterTextChange(Editable text, int index) {
        if (index == TypeItem.NAME.ordinal()) {
            setName(String.valueOf(text));
        } else if (index == TypeItem.EMAIL.ordinal()) {
            setEmail(String.valueOf(text));
        } else if (index == TypeItem.BIRTHDAY.ordinal()) {
            setBirthDate(String.valueOf(text));
        } else if (index == TypeItem.PHONE.ordinal()) {
            setPhone(String.valueOf(text));
        }
    }

    public void onShowDatePickerDialogClick(final TextView edt) {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(edt.getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar.set(year, month, day, 0, 0, 0);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                        String strDate = dateFormat.format(calendar.getTime());
                        setBirthDate(strDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(birthDate);
        parcel.writeInt(genDer);
        parcel.writeString(phone);
        parcel.writeString(imageUrl);
    }

    public enum TypeItem {
        NAME,
        EMAIL,
        BIRTHDAY,
        PHONE
    }
}
