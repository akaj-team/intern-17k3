package vn.asiantech.internship.ui.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityEditProfileBinding;
import vn.asiantech.internship.model.User;

/**
 * Created by anh.quach on 1/10/18.
 * Edit Profile Activity
 */
public class EditProfileActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        User user = new User();
        binding.setUser(user);
    }
}
