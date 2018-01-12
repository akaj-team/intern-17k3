package vn.asiantech.internship.ui.datablind;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.util.Log;

import vn.asiantech.internship.BR;

/**
 * Author Asian Tech Inc.
 * Created by tungnguyen on 12/01/2018.
 */

public class User extends BaseObservable {
    private String mUserName;
    private String mEmail;
    private String mBirthDate;
    private int mGender;
    private String mContact;

    public User() {
    }

    public User(String mUserName, String mEmail, String mBirthDate, int mGender, String mContact) {
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mBirthDate = mBirthDate;
        this.mGender = mGender;
        this.mContact = mContact;
    }
    @Bindable
    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
        notifyPropertyChanged(BR.user);
    }
    @Bindable
    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
        notifyPropertyChanged(BR.mEmail);
    }
    @Bindable
    public String getmBirthDate() {
        return mBirthDate;
    }
    public void setmBirthDate(String mBirthDate) {
        this.mBirthDate = mBirthDate;
    }
    @Bindable
    public int getmGender() {
        return mGender;
    }

    public void setmGender(int mGender) {
        this.mGender = mGender;
    }
    @Bindable
    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
    }
    public void onChangeNameClick(Editable edtName)  {
        setmUserName(String.valueOf(edtName.toString()));
//        setmEmail(String.valueOf(edtEmail.toString()));
//        setmContact(String.valueOf(edtContact.toString()));
        Log.d("sssss", "onChangeNameClick:"+mUserName);
    }
}
