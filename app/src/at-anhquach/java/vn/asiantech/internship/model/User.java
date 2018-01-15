package vn.asiantech.internship.model;

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

import java.util.Calendar;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.data_binding.EditProfileActivity;
import vn.asiantech.internship.ui.data_binding.PreEditProfileActivity;

/**
 * Define class User
 */
public class User extends BaseObservable implements Parcelable {

    private int id;
    private String fullname;
    private String birthday;
    private String email;
    private String gender;
    private String contactnumber;
    private int age;
    private String usename;
    private String password;
    private String imgUrl;

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
        imgUrl = in.readString();
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        setImgUrl(imgUrl);
        setFullname(fullname);
        setBirthday(birthday);
        setEmail(email);
        setGender(gender);
        setContactnumber(contactnumber);
        Intent intent = new Intent(context, PreEditProfileActivity.class);
        intent.putExtra(User.class.getSimpleName(), this);
        context.startActivity(intent);
    }

    public void intentActivityEdit(Context context) {
        setImgUrl(imgUrl);
        setFullname(fullname);
        setBirthday(birthday);
        setEmail(email);
        setGender(gender);
        setContactnumber(contactnumber);
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtra(User.class.getSimpleName(), this);
        context.startActivity(intent);
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
                        edt.setText(context.getResources().getString(R.string.edittext_birthday, day, month + 1, year));
                    }
                }, year, month, day);
        datePickerDialog.show();
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
        } else gender = context.getResources().getStringArray(R.array.gender_arrays)[0];
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
        dest.writeString(imgUrl);
    }
}
