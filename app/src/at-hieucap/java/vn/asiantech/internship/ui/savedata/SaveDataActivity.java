package vn.asiantech.internship.ui.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Create Activity Save Data
 */
public class SaveDataActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnSharePreference;
    private Button mBtnInExternal;
    private Button mBtnDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        initViews();
        addListener();
    }

    private void initViews() {
        mBtnSharePreference = findViewById(R.id.btnSharePreference);
        mBtnInExternal = findViewById(R.id.btnInExternal);
        mBtnDatabase = findViewById(R.id.btnDatabase);
    }

    private void addListener() {
        mBtnSharePreference.setOnClickListener(this);
        mBtnInExternal.setOnClickListener(this);
        mBtnDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSharePreference:
                startActivity(new Intent(this, SharePreferenceActivity.class));
                break;
            case R.id.btnInExternal:
                startActivity(new Intent(this, ExternalStorageActivity.class));
                break;
            case R.id.btnDatabase:
                startActivity(new Intent(this, SaveDatabaseActivity.class));
        }
    }
}
