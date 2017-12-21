package vn.asiantech.internship.ui.savedata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

/**
 * Create Share Preference Activity
 */
public class SharePreferenceActivity extends AppCompatActivity {
    private EditText mEdtTextSave;
    private Button mBtnClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        initViews();
        displayData();
        addListener();
    }

    private void addListener() {
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.text_save), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.text_save), mEdtTextSave.getText().toString());
                editor.apply();
            }
        });
    }

    private void displayData() {
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.text_save), Context.MODE_PRIVATE);
        String textResult = sharedPreferences.getString(getResources().getString(R.string.text_save), "");
        mEdtTextSave.setText(textResult);
    }

    private void initViews() {
        mBtnClick = findViewById(R.id.btnClick);
        mEdtTextSave = findViewById(R.id.edtTextSharePreference);
    }
}
