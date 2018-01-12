package vn.asiantech.internship.databinding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.asiantech.internship.R;

public class PreViewActivity extends AppCompatActivity {
    User user = new User();
    String name, mail, day, phone;
    int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_view);
        getDataBundle();
    }

    /**
     * onclick button edit profile
     *
     * @param view view
     */
    public void editProfile(View view) {
        startActivity(new Intent(PreViewActivity.this, EditInfoActivity.class));
    }

    private void getDataBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            user = bundle.getParcelable("user");
            if (user != null) {
                name = user.getName();
            }
        }
    }
}
