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
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.EditProfileActivity;
import vn.asiantech.internship.databinding.PreEditProfileActivity;

/**
 * Define class User
 */
public class User extends BaseObservable implements Parcelable {
    public static final int EDIT_USER_REQUEST_CODE = 1;

    private int id;
    private String fullname;
    private String birthday;
    private String email;
    private String gender;
    private String contactnumber;
    private int age;
    private String usename;
    private String password;
    private String avatar;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readInt();
        fullname = in.readString();
        birthday = in.readString();
        email = in.readString();
        gender = in.readString();
        contactnumber = in.readString();
        age = in.readInt();
        usename = in.readString();
        password = in.readString();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    @Bindable
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    @Bindable
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    @Bindable
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;

    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Bindable
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    @Bindable
    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void clearEditText(EditText edt) {
        edt.setText("");
    }

    public void afterTextChange(Editable text, int type, ImageView img) {
        switch (type) {
            case 0:
                fullname = text.toString();
                checkInputInfo(img);
                break;
            case 1:
                email = text.toString();
                checkInputInfo(img);
                break;
            case 2:
                birthday = text.toString();
                checkInputInfo(img);
                break;
            case 3:
                contactnumber = text.toString();
                checkInputInfo(img);
                break;
        }
    }

    public void intentActivityPreEdit(Context context) {
        setAvatar(avatar);
        setFullname(fullname);
        setBirthday(birthday);
        setEmail(email);
        setGender(gender);
        setContactnumber(contactnumber);
        Intent intent = new Intent(context, PreEditProfileActivity.class);
        intent.putExtra(User.class.getSimpleName(), this);
        if (context instanceof EditProfileActivity) {
            ((EditProfileActivity) context).setResult(Activity.RESULT_OK, intent);
            ((EditProfileActivity) context).finish();
        }
    }

    public void intentActivityEdit(Context context) {
        setAvatar(avatar);
        setFullname(fullname);
        setBirthday(birthday);
        setEmail(email);
        setGender(gender);
        setContactnumber(contactnumber);
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtra(User.class.getSimpleName(), this);
        ((PreEditProfileActivity) context).startActivityForResult(intent, EDIT_USER_REQUEST_CODE);
    }

    public void showDatePicker(final EditText edt, final Context context) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {
                        edt.setText(formatBirthday(c));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private String formatBirthday(Calendar calendar) {
        SimpleDateFormat resultFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return resultFormat.format(calendar.getTime());
    }

    private void checkInputInfo(ImageView img) {
        if (!TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(birthday) && !TextUtils.isEmpty(contactnumber)) {
            img.setSelected(true);
            img.setEnabled(true);
        } else {
            img.setSelected(false);
            img.setEnabled(false);
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id, Context context) {
        if (pos == 1) {
            gender = context.getResources().getStringArray(R.array.gender_arrays)[pos];
        } else {
            gender = context.getResources().getStringArray(R.array.gender_arrays)[0];
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fullname);
        dest.writeString(birthday);
        dest.writeString(email);
        dest.writeString(gender);
        dest.writeString(contactnumber);
        dest.writeInt(age);
        dest.writeString(usename);
        dest.writeString(password);
        dest.writeString(avatar);
    }
}
