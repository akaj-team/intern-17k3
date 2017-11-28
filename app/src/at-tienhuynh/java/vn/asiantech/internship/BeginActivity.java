package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BeginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnIsuses1;
    private Button mBtnIsuses2;
    private Button mBtnIsuses3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        initViews();
    }

    private void initViews() {
        mBtnIsuses1 = findViewById(R.id.btnIsuses1);
        mBtnIsuses2 = findViewById(R.id.btnIsuses2);
        mBtnIsuses3 = findViewById(R.id.btnIsuses3);
        mBtnIsuses1.setOnClickListener(this);
        mBtnIsuses2.setOnClickListener(this);
        mBtnIsuses3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIsuses1:
                break;
            case R.id.btnIsuses2:
                break;
            case R.id.btnIsuses3:
                startActivity(new Intent(this,RecyclerViewActivity.class));
                break;
        }
    }
}
