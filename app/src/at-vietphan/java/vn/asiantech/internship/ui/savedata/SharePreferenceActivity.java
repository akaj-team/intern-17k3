package vn.asiantech.internship.ui.savedata;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.asiantech.internship.R;

/**
 * Created by vietphan on 07/12/2017
 * SharePreferenceActivity
 */
public class SharePreferenceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String NAME_KEY = "NAME_KEY";
    private SharedPreferences mSharedPreferences;
    private EditText mEdtName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        initViews();
        mSharedPreferences = getSharedPreferences(getString(R.string.preferences_my_key), MODE_PRIVATE);
        mEdtName.setText(mSharedPreferences.getString(NAME_KEY, ""));
    }

    private void initViews() {
        Button btnSave = findViewById(R.id.btnSave);
        mEdtName = findViewById(R.id.edtName);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                mSharedPreferences = getSharedPreferences(getString(R.string.preferences_my_key), MODE_PRIVATE);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(NAME_KEY, mEdtName.getText().toString());
                editor.apply();
                Toast.makeText(this, NAME_KEY, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
