package vn.asiantech.internship.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import vn.asiantech.internship.BR;

/**
 * Created by TienHuynh on 11/01/2018.
 * AsianTech Co., Ltd
 */
public class User extends BaseObservable {
    public String name;
    public String email;
    public String birthDay;
    public int gender;
    public String contactNumber;

    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getEmail() {
        return email;
    }

    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public String getBirthDay() {
        return birthDay;
    }

    @Bindable
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
        notifyPropertyChanged(BR.birthDay);
    }

    public int getGender() {
        return gender;
    }

    @Bindable
    public void setGender(int gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Bindable
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        notifyPropertyChanged(BR.contactNumber);
    }
}
