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
        ActivityEditInfoBinding mActivityEditInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_info);
        User user = new User();
        user.setUrl("https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg");
        mActivityEditInfoBinding.setUser(user);
    }
}
