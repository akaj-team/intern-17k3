package vn.asiantech.internship.ui.databind;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityPriviewUserBinding;

public class PriviewUserActivity extends AppCompatActivity {
    ActivityPriviewUserBinding activityPriviewUserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPriviewUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_priview_user);
        User user = new User();
        user.setUserName("TUngkute");
        user.setEmail("Thanhtunga0000");
        user.setContact("01626061362");
        user.setGender(0);
        user.setUrl("https://i2-prod.mirror.co.uk/incoming/article9967270.ece/ALTERNATES/s615b/FC-Barcelona-v-RC-Celta-de-Vigo-La-Liga.jpg");
        activityPriviewUserBinding.setUser(user);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == User.REQUEST_CODE_EDIT && data != null) {
            activityPriviewUserBinding.setUser((User) data.getParcelableExtra(User.class.getSimpleName()));
        }
    }
}
