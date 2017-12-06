package vn.asiantech.internship.savedata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

public class SharedPreferencesActivity extends AppCompatActivity {
    public static final String MY_PREFS = "MyPrefsFile";
    public static final String KEY_PREFS = "values";
    private EditText mEdtValue;
    private Button mBtnSaveSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        initViews();
        getData();
        mBtnSaveSharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSharedPreferencesEditor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
                mSharedPreferencesEditor.putString(KEY_PREFS, mEdtValue.getText().toString());
                mSharedPreferencesEditor.apply();
                startActivity(new Intent(SharedPreferencesActivity.this, SaveDataActivity.class));
            }
        });
    }

    private void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        String getTextData = sharedPreferences.getString(KEY_PREFS, null);
        if (getTextData != null) {
            mEdtValue.setText(getTextData);
        }
    }

    private void initViews() {
        mEdtValue = findViewById(R.id.edtValue);
        mBtnSaveSharedPreferences = findViewById(R.id.btnSaveSharedPreferences);
    }
}
