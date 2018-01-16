package vn.asiantech.internship.ui.databind;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityEditUserBinding;


public class EditUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditUserBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_user);

        if (getIntent().getParcelableExtra(User.class.getSimpleName()) != null) {
            Log.d("sssss", "onCreate: ");
            binding.setUser((User) getIntent().getParcelableExtra(User.class.getSimpleName()));
        }
    }
}
