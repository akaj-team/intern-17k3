package vn.asiantech.internship.saveData;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 12/12/2017.
 */

public class SharePreferenceActivity extends AppCompatActivity{
    private EditText mEdtText;
    private Button mBtnClick;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        mBtnClick =findViewById(R.id.btnClick);
        mEdtText = findViewById(R.id.edtTextSharePreference);

        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
