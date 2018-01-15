package vn.asiantech.internship.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

public class PreViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vn.asiantech.internship.databinding.ActivityPreViewBinding activityPreViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_pre_view);
        // get data from EditText Activity
        // if don't have bundle is set hard data
        Bundle bundle = getIntent().getExtras();
        // setBinding
        if (bundle != null) {
            activityPreViewBinding.setUser((User) bundle.getParcelable(User.class.getSimpleName()));
        } else {
            // set hard data
            User user = new User();
            user.setName("JackMa");
            user.setEmail("jack@gmail.com");
            user.setGender(0);
            user.setContactNumber("09071319233");
            user.setUrl("https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg");
            activityPreViewBinding.setUser(user);
        }
    }
}
