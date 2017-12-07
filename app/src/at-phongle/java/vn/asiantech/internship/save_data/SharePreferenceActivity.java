package vn.asiantech.internship.save_data;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

public class SharePreferenceActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText mEdtInputSharePrefer;
    private Button mBtnClickSharePrefer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        initViews();
        initListener();
        readPreference();
    }

    private void initViews() {
        mEdtInputSharePrefer = findViewById(R.id.edtInputExternalStorage);
        mBtnClickSharePrefer = findViewById(R.id.btnClickExternalStorage);
    }

    private void initListener() {
        mBtnClickSharePrefer.setOnClickListener(this);
        mEdtInputSharePrefer.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        readPreference();
    }

    private void savePreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("SharePrefer", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = mEdtInputSharePrefer.getText().toString();
        editor.putString("input", text);
        editor.apply();
    }

    private void readPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("SharePrefer", MODE_PRIVATE);
        mEdtInputSharePrefer.setText(sharedPreferences.getString("input", "not input text"));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        savePreference();
    }
}
