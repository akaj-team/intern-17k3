package vn.asiantech.internship.ui.databind;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityPreviewUserBinding;

public class PreviewUserActivity extends AppCompatActivity {
    private ActivityPreviewUserBinding mBinding;
    static final int EDIT_USER_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_preview_user);
        User user = new User();
        user.setUserName("TUngkute");
        user.setEmail("Thanhtunga0000");
        user.setContact("01626061362");
        user.setGender(0);
        user.setAvatar("https://i2-prod.mirror.co.uk/incoming/article9967270.ece/ALTERNATES/s615b/FC-Barcelona-v-RC-Celta-de-Vigo-La-Liga.jpg");
        user.setBirthDate("02/10/1997");
        mBinding.setUser(user);
    }

    /**
     * Get Data from EditText
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_USER_REQUEST_CODE && data != null) {
            mBinding.setUser((User) data.getParcelableExtra(User.class.getSimpleName()));
        }
    }
}
