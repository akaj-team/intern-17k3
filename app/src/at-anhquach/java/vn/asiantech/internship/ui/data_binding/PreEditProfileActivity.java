package vn.asiantech.internship.ui.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityPreEditProfileBinding;
import vn.asiantech.internship.model.User;

/**
 * Created by anh.quach on 1/10/18.
 * Edit Profile Activity
 */
public class PreEditProfileActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPreEditProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_pre_edit_profile);
        User mUser = getIntent().getParcelableExtra(User.class.getSimpleName());
        // setBinding
        if (mUser != null) {
            binding.setUser(mUser);
        }
    }
}
