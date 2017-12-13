package vn.asiantech.internship.image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import vn.asiantech.internship.R;

/**
 * Drawable NinePath with EditText
 */
public class NinePathActivity extends AppCompatActivity {
    private EditText mEdtNinePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_path);
        initViews();
    }

    private void initViews() {
        mEdtNinePath = findViewById(R.id.edtNinePath);
    }
}
