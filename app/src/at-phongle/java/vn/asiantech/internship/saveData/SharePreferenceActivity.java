package vn.asiantech.internship.saveData;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 11/12/2560.
 * SharePreferenceActivity
 */
public class SharePreferenceActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtInputSharePrefer;
    private Button mBtnClickSharePrefer;
    private static String SHARE_PREFERENCE_KEY = "input";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        initViews();
        initListener();
        readPreference();
    }

    private void initViews() {
        mBtnClickSharePrefer = findViewById(R.id.btnClickSharePreference);
        mEdtInputSharePrefer = findViewById(R.id.edtSharePreference);
    }

    private void initListener() {
        mBtnClickSharePrefer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        savePreference();
    }

    private void savePreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("SharePrefer", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = mEdtInputSharePrefer.getText().toString();
        editor.putString(SHARE_PREFERENCE_KEY, text);
        editor.apply();
    }

    private void readPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("SharePrefer", MODE_PRIVATE);
        mEdtInputSharePrefer.setText(sharedPreferences.getString(SHARE_PREFERENCE_KEY, "not input text"));
    }
}
