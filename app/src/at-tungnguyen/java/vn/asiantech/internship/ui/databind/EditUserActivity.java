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
        ActivityEditUserBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_user);
        User user = new User();
        user.setUrl("https://i2-prod.mirror.co.uk/incoming/article9967270.ece/ALTERNATES/s615b/FC-Barcelona-v-RC-Celta-de-Vigo-La-Liga.jpg");
        /**
         * Get Data from Preview
         */
        if (getIntent().getParcelableExtra(User.class.getSimpleName()) != null) {
            binding.setUser((User) getIntent().getParcelableExtra(User.class.getSimpleName()));
        }
    }
}
