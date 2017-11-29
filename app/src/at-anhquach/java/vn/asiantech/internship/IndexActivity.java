package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by anh.quach on 11/28/17.
 */

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button mBtnIssue2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mBtnIssue2 = findViewById(R.id.btnIssue2);
        mBtnIssue2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIssue2:
                Intent intentMain = new Intent(IndexActivity.this,
                        CalculatorViewActivity.class);
                startActivity(intentMain);
        }
    }
}
