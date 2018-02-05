package vn.asiantech.internship.ui.databind;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityEditUserBinding;


public class EditUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User mUser;
        ActivityEditUserBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_user);
         // Get Data from Preview
        mUser = getIntent().getParcelableExtra(User.class.getSimpleName());
        if (mUser != null) {
            mUser.setAvatar("https://i2-prod.mirror.co.uk/incoming/article9967270.ece/ALTERNATES/s615b/FC-Barcelona-v-RC-Celta-de-Vigo-La-Liga.jpg");
            mBinding.setUser(mUser);
        }
    }
}
