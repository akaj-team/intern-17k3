package vn.asiantech.internship.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;

/**
 * DataBinding Activity to Edit Profile
 */
public class EditInfoActivity extends AppCompatActivity {

    private ActivityEditInfoBinding mActivityEditInfoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view Data Binding
        mActivityEditInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_info);
        mActivityEditInfoBinding.setUser(new User("tien", "ma", "222", 1, "213123"));
    }
}
