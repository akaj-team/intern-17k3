package vn.asiantech.internship.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.model.User;

/**
 * Created by anh.quach on 1/10/18.
 * Edit Profile Activity
 */
public class EditProfileActivity extends AppCompatActivity {
    public static final int FULLNAME = 0;
    public static final int EMAIL = 1;
    public static final int BIRTHDAY = 2;
    public static final int CONTACTNUMBER = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        User user = getIntent().getParcelableExtra(User.class.getSimpleName());
        if (user != null) {
            binding.setUser(user);
        }
    }
}
