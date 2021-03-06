package vn.asiantech.internship.ui.savedata;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

/**
 * DownloadActivity save data by SharePreference
 */
public class SharePreferenceActivity extends AppCompatActivity {
    private Button mBtnSave;
    private EditText mEdtSharePreference;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        initView();
        initListener();
    }

    private void initView() {
        mBtnSave = findViewById(R.id.btnSaveSP);
        mEdtSharePreference = findViewById(R.id.edtSharePreference);
        mSharedPreferences = getSharedPreferences("Data_SharePre", MODE_PRIVATE);
        mEdtSharePreference.setHint(mSharedPreferences.getString("text", "Input"));
    }

    private void initListener() {
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editorEdit = mSharedPreferences.edit();
                editorEdit.putString("text", mEdtSharePreference.getText().toString());
                editorEdit.apply();
            }
        });
    }
}
