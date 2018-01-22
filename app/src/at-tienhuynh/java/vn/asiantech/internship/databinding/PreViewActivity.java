package vn.asiantech.internship.databinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

public class PreViewActivity extends AppCompatActivity {
    public static final int EDIT_USER_REQUEST_CODE = 1;

    private ActivityPreviewBinding activityPreViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPreViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_preview);
        // set hard data
        User user = new User();
        user.setName("JackMa");
        user.setEmail("jack@gmail.com");
        user.setGender(0);
        user.setContactNumber("09071319233");
        user.setUrl("https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg");
        activityPreViewBinding.setUser(user);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            activityPreViewBinding.setUser((User) data.getParcelableExtra(User.class.getSimpleName()));
        }
    }
}
