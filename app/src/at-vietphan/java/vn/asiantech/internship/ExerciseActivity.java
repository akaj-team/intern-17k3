package vn.asiantech.internship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.ui.login.LoginActivity;
import vn.asiantech.internship.ui.recyclerview.RecyclerViewActivity;

public class ExerciseActivity extends Activity implements View.OnClickListener{
    private Button mBtnLogin;
    private Button mBtnRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);
        initViews();
        initListener();
    }
    private void initViews(){
        mBtnLogin = findViewById(R.id.btnLogin);
        mBtnRecyclerView = findViewById(R.id.btnRecyclerView);
    }
    private void initListener(){
        mBtnLogin.setOnClickListener(this);
        mBtnRecyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnRecyclerView:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
        }
    }
}