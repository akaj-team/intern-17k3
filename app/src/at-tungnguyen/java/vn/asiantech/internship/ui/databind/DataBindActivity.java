package vn.asiantech.internship.ui.databind;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityDataBlindBinding;

public class DataBindActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBlindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_blind);
        binding.setUser(new User("Tung","Tung2","02/10/1997",0,"01666666666"));

    }


}
