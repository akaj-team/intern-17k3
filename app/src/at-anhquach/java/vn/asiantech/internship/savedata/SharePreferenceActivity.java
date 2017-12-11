package vn.asiantech.internship.savedata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

/*
 * Class save and display data by share Preference
 */
public class SharePreferenceActivity extends AppCompatActivity {
    EditText mEdtSaveData;
    Button mBtnSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        initView();
        displayData();
        mBtnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.key_save_data), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.key_save_data), mEdtSaveData.getText().toString());
                editor.apply();
            }
        });
    }

    private void initView() {
        mEdtSaveData = findViewById(R.id.edtSaveData);
        mBtnSaveData = findViewById(R.id.btnSaveData);
    }

    private void displayData() {
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.key_save_data), Context.MODE_PRIVATE);
        String textDislayed = sharedPreferences.getString(getResources().getString(R.string.key_save_data), "");
        mEdtSaveData.setText(textDislayed);
    }
}
