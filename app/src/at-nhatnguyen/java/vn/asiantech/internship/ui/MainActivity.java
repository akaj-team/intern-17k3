package vn.asiantech.internship.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.drawerlayout.DrawerActivity;
import vn.asiantech.internship.ui.recyclerview.CommentActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mBtnRecyclerView;
    private Button mBtnCalculator;
    private Button mBtnDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mBtnRecyclerView = findViewById(R.id.btnRecyclerView);
        mBtnCalculator = findViewById(R.id.btnCalculator);
        mBtnDrawerLayout = findViewById(R.id.btnDrawerLayout);
    }

    private void initListener() {
        mBtnRecyclerView.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
        mBtnDrawerLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecyclerView:
                startActivity(new Intent(this, CommentActivity.class));
                break;
            case R.id.btnCalculator:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case R.id.btnDrawerLayout:
                startActivity(new Intent(this, DrawerActivity.class));
                break;
        }
    }
}
