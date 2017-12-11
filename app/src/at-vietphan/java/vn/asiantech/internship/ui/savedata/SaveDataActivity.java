package vn.asiantech.internship.ui.savedata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Created by vietphan on 07/12/2017
 * SaveDataActivity
 */
public class SaveDataActivity extends Activity implements View.OnClickListener {
    private Button mBtnSharePreference;
    private Button mBtnExternalStorage;
    private Button mBtnSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnSharePreference = findViewById(R.id.btnSharePreference);
        mBtnExternalStorage = findViewById(R.id.btnExternalStorage);
        mBtnSQLite = findViewById(R.id.btnSQLlite);
    }

    private void initListener() {
        mBtnSharePreference.setOnClickListener(this);
        mBtnExternalStorage.setOnClickListener(this);
        mBtnSQLite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSharePreference:
                startActivity(new Intent(this, SharePreferenceActivity.class));
                break;
            case R.id.btnExternalStorage:
                startActivity(new Intent(this, ExternalStorageActivity.class));
                break;
            case R.id.btnSQLlite:
                startActivity(new Intent(this, SQLiteActivity.class));
                break;
        }
    }
}
