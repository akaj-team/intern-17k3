package vn.asiantech.internship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.ui.asynchronous.activitys.AsynchronousActivity;
import vn.asiantech.internship.ui.calculator.CalculatorActivity;
import vn.asiantech.internship.ui.canvas.CanvasActivity;
import vn.asiantech.internship.ui.databinding.PreviewProfileActivity;
import vn.asiantech.internship.ui.drawerlayout.DrawerActivity;
import vn.asiantech.internship.ui.loadimage.ImageLoaderActivity;
import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;
import vn.asiantech.internship.ui.savedata.SaveDataActivity;
import vn.asiantech.internship.ui.unittest.UnitTestActivity;
import vn.asiantech.internship.ui.viewpager.service.MusicActivity;

/**
 * Created by vietphan on 07/12/2017
 * ExerciseActivity
 */
public class ExerciseActivity extends Activity implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnRecyclerView;
    private Button mBtnCalculator;
    private Button mBtnDrawerLayout;
    private Button mBtnSaveData;
    private Button mBtnLoadImage;
    private Button mBtnViewPager;
    private Button mBtnAsynchronous;
    private Button mBtnCanvas;
    private Button mBtnUnitTest;
    private Button mBtnDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);
        initViews();
        initListener();
    }

    private void initViews() {
        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnRecyclerView = findViewById(R.id.btnRecyclerView);
        mBtnCalculator = findViewById(R.id.btnCalculator);
        mBtnDrawerLayout = findViewById(R.id.btnDrawerLayout);
        mBtnSaveData = findViewById(R.id.btnSaveData);
        mBtnLoadImage = findViewById(R.id.btnLoadImage);
        mBtnViewPager = findViewById(R.id.btnViewPager);
        mBtnAsynchronous = findViewById(R.id.btnAsynchronous);
        mBtnCanvas = findViewById(R.id.btnCanvas);
        mBtnUnitTest = findViewById(R.id.btnUnitTest);
        mBtnDataBinding = findViewById(R.id.btnDataBinding);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
        mBtnCalculator.setOnClickListener(this);
        mBtnDrawerLayout.setOnClickListener(this);
        mBtnSaveData.setOnClickListener(this);
        mBtnLoadImage.setOnClickListener(this);
        mBtnViewPager.setOnClickListener(this);
        mBtnAsynchronous.setOnClickListener(this);
        mBtnCanvas.setOnClickListener(this);
        mBtnUnitTest.setOnClickListener(this);
        mBtnDataBinding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnRecyclerView:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btnCalculator:
                startActivity(new Intent(this, CalculatorActivity.class));
                break;
            case R.id.btnDrawerLayout:
                startActivity(new Intent(this, DrawerActivity.class));
                break;
            case R.id.btnSaveData:
                startActivity(new Intent(this, SaveDataActivity.class));
                break;
            case R.id.btnLoadImage:
                startActivity(new Intent(this, ImageLoaderActivity.class));
                break;
            case R.id.btnViewPager:
                startActivity(new Intent(this, MusicActivity.class));
                break;
            case R.id.btnAsynchronous:
                startActivity(new Intent(this, AsynchronousActivity.class));
                break;
            case R.id.btnCanvas:
                startActivity(new Intent(this, CanvasActivity.class));
                break;
            case R.id.btnUnitTest:
                startActivity(new Intent(this, UnitTestActivity.class));
                break;
            case R.id.btnDataBinding:
                startActivity(new Intent(this, PreviewProfileActivity.class));
        }
    }
}
