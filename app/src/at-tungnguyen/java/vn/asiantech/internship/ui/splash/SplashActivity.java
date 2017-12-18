package vn.asiantech.internship.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.drawer.DrawerActivity;
import vn.asiantech.internship.ui.caculatorview.CalculatorActivity;
import vn.asiantech.internship.ui.login.MainActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;
import vn.asiantech.internship.viewpager.ui.ViewPagerActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnCalculator;
    private Button mBtnRecyclerView;
    private Button mBtnDrawerlayout;
    private Button mBtnViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnCalculator = findViewById(R.id.btnCaculator);
        mBtnRecyclerView = findViewById(R.id.btnRecyclerView);
        mBtnDrawerlayout = findViewById(R.id.btnDrawerlayout);
        mBtnViewPager = findViewById(R.id.btnViewPager);


    }

    private void initListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnDrawerlayout.setOnClickListener(this);
        mBtnViewPager.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.btnCaculator:
                Intent i2 = new Intent(SplashActivity.this, CalculatorActivity.class);
                startActivity(i2);
                break;
            case R.id.btnRecyclerView:
                Intent i3 = new Intent(SplashActivity.this, RecyclerViewActivity.class);
                startActivity(i3);
                break;
            case R.id.btnDrawerlayout:
                Intent i4 = new Intent(SplashActivity.this, DrawerActivity.class);
                startActivity(i4);
                break;
            case R.id.btnViewPager:
                Intent i5 = new Intent(SplashActivity.this, ViewPagerActivity.class);
                startActivity(i5);
                break;
        }
    }
}
