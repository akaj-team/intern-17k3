package vn.asiantech.internship.model;

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
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import vn.asiantech.internship.BR;
import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.EditProfileActivity;
import vn.asiantech.internship.databinding.PreEditProfileActivity;

/**
 * Define class User
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

    public int id;
    public String fullName;
    public String birthday;
    public String email;
    public int gender;
    public String contactNumber;
    public int age;
    private String usename;
    public String password;
    private String avatar;
    private boolean enable;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readInt();
        fullName = in.readString();
        birthday = in.readString();
        email = in.readString();
        gender = in.readInt();
        contactNumber = in.readString();
        age = in.readInt();
        usename = in.readString();
        password = in.readString();
        avatar = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullName;
    }

    @Bindable
    public void setFullname(String fullname) {
        this.fullName = fullname;
        notifyPropertyChanged(BR.fullname);
    }

    @Bindable
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        notifyPropertyChanged(BR.birthday);
    }

    public String getEmail() {
        return email;
    }

    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getContactNumber() {
        return contactNumber;
    }

    @Bindable
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        notifyPropertyChanged(BR.contactNumber);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public boolean isEnable() {
        return enable;
    }

    private void setEnable(boolean enable) {
        this.enable = enable;
        notifyPropertyChanged(BR.enable);
    }

    public void onClearDataClick(EditText edt) {
        edt.setText("");
    }

    public void afterTextChange(Editable text, int type) {
        switch (type) {
            case EditProfileActivity.FULLNAME:
                fullName = text.toString();
                break;
            case EditProfileActivity.EMAIL:
                email = text.toString();
                break;
            case EditProfileActivity.BIRTHDAY:
                birthday = text.toString();
                break;
            case EditProfileActivity.CONTACTNUMBER:
                contactNumber = text.toString();
        }
        changeStatusUpdateButton();
    }

    public void onPreviewProfileClick(Context context) {
        setAvatar(avatar);
        setFullname(fullName);
        setBirthday(birthday);
        setEmail(email);
        setGender(gender);
        setContactNumber(contactNumber);
        Intent intent = new Intent(context, PreEditProfileActivity.class);
        intent.putExtra(User.class.getSimpleName(), this);
        if (context instanceof EditProfileActivity) {
            ((EditProfileActivity) context).setResult(Activity.RESULT_OK, intent);
            ((EditProfileActivity) context).finish();
        }
    }

    public void onEditProfileClick(Context context) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        setAvatar(avatar);
        setFullname(fullName);
        setBirthday(birthday);
        setEmail(email);
        setGender(gender);
        setContactNumber(contactNumber);
        intent.putExtra(User.class.getSimpleName(), this);
        ((PreEditProfileActivity) context).startActivityForResult(intent, PreEditProfileActivity.EDIT_USER_REQUEST_CODE);
    }

    public void onShowDatePickerClick(Context context) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {
                        c.set(year, month, day);
                        setBirthday(formatBirthday(c));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private String formatBirthday(Calendar calendar) {
        SimpleDateFormat resultFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return resultFormat.format(calendar.getTime());
    }

    private void changeStatusUpdateButton() {
        if (!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(birthday) && !TextUtils.isEmpty(contactNumber)) {
            setEnable(true);
        } else {
            setEnable(false);
        }
    }

    public String setStrGender(Context context) {
        return context.getResources().getStringArray(R.array.gender_arrays)[gender];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fullName);
        dest.writeString(birthday);
        dest.writeString(email);
        dest.writeInt(gender);
        dest.writeString(contactNumber);
        dest.writeInt(age);
        dest.writeString(usename);
        dest.writeString(password);
        dest.writeString(avatar);
    }
}
