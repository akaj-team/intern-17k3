package vn.asiantech.internship.models;

import android.annotation.SuppressLint;
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

import java.util.Calendar;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.databinding.EditProfileActivity;

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
    public static final int REQUEST_CODE = 1;
    private String name;
    private String email;
    private String birthDate;
    private int genDer;
    private String phone;
    private String imageUrl;

    public ProfileUser(String name, String email, String birthDate, int genDer, String phone, String imageUrl) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.genDer = genDer;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }

    public ProfileUser() {

    }

    private ProfileUser(Parcel in) {
        name = in.readString();
        email = in.readString();
        birthDate = in.readString();
        genDer = in.readInt();
        phone = in.readString();
        imageUrl = in.readString();
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

    public void editProfile(Context context, ProfileUser profileUser) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtra(ProfileUser.class.getSimpleName(), profileUser);
        ((AppCompatActivity) context).startActivityForResult(intent, REQUEST_CODE);
    }

    public void afterTextChange(Editable text, int id) {
        if (id == R.id.edtName) {
            setName(String.valueOf(text));
        } else if (id == R.id.edtEmail) {
            setEmail(String.valueOf(text));
        } else if (id == R.id.edtBirthDate) {
            setBirthDate(String.valueOf(text));
        } else if (id == R.id.spnGender) {
            setGenDer(Integer.parseInt(String.valueOf(text)));
        } else if (id == R.id.edtPhone) {
            setPhone(String.valueOf(text));
        }
    }

    public void updateProfile(Context context, ProfileUser profileUser) {
        if (context instanceof EditProfileActivity) {
            Intent intent = new Intent();
            intent.putExtra(ProfileUser.class.getSimpleName(), profileUser);
            ((EditProfileActivity) context).setResult(REQUEST_CODE, intent);
            ((EditProfileActivity) context).finish();
        }
    }

    @SuppressLint("SetTextI18n")
    public void showDatePicker(final TextView edt) {
        // calendar
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //show current day
        edt.setText(day + "/" + (month + 1) + "/" + year);
        DatePickerDialog datePickerDialog = new DatePickerDialog(edt.getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {
                        setBirthDate(day + "/" + (month + 1) + "/" + year);
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
}
