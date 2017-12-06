package vn.asiantech.internship.savedata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

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
                saveData();
            }
        });
    }

    private void initView() {
        mEdtSaveData = findViewById(R.id.edtSaveData);
        mBtnSaveData = findViewById(R.id.btnSaveData);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SAVE_DATA", mEdtSaveData.getText().toString());
        editor.apply();
    }

    private void displayData() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_DATA", Context.MODE_PRIVATE);
        String textDislayed = sharedPreferences.getString("SAVE_DATA", "");
        mEdtSaveData.setText(textDislayed);
    }
}