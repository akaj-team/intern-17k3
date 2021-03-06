package vn.asiantech.internship.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

/**
 * DataBinding Activity to Edit Profile
 */
public class EditInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view Data Binding
        ActivityEditInfoBinding activityEditInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_info);
        User user = getIntent().getParcelableExtra(User.class.getSimpleName());
        if (user != null) {
            activityEditInfoBinding.setUser(user);
        }
    }
}
