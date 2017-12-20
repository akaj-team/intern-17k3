package vn.asiantech.internship.threadandhandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.threadandhandler.countdowtimer.ui.CountDownTimerActivity;
import vn.asiantech.internship.threadandhandler.dowloadimage.ui.TabManagementActivity;

/**
 * Created at 2017
 * Created by jackty on 20/12/2017.
 */
public class SplashThreadAndHandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnExercise1;
    private Button mBtnExercise2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_thread_and_handler);
        initViews();
        initListeners();
    }

    /**
     * Init Views
     */
    private void initViews() {
        mBtnExercise1 = findViewById(R.id.btnThreadExercise1);
        mBtnExercise2 = findViewById(R.id.btnThreadExercise2);
    }

    /**
     * Init Listeners
     */
    private void initListeners() {
        mBtnExercise1.setOnClickListener(this);
        mBtnExercise2.setOnClickListener(this);
    }

    /**
     * Onclick Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnThreadExercise1:
                startActivity(new Intent(SplashThreadAndHandlerActivity.this, TabManagementActivity.class));
                break;
            case R.id.btnThreadExercise2:
                startActivity(new Intent(SplashThreadAndHandlerActivity.this, CountDownTimerActivity.class));
                break;
        }
    }
}
