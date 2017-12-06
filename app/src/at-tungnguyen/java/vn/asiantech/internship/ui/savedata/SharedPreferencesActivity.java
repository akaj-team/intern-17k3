package vn.asiantech.internship.ui.savedata;

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
    private EditText mEdtName;
    private Button mBtnResult;
    public String maxDead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        initViews();
        initEvent();
        Display();
    }

    private void initViews() {
        mEdtName = findViewById(R.id.edtName);
        mBtnResult = findViewById(R.id.btnResult);
    }

    private void initEvent() {
        mBtnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData();
            }
        });
    }

    private void Display() {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        String getData = sharedPreferences.getString("Save", null);
        mEdtName.setText(getData);
    }

    private void SaveData() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
        editor.putString("Save", mEdtName.getText().toString());
        editor.apply();

    }
}
