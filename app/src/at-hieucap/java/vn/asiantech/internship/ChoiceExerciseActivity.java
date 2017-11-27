package vn.asiantech.internship;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import vn.asiantech.internship.calculator.CalculatorActivity;

/**
 * Created by tiboo on 27/11/2017.
 */

public class ChoiceExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnBt1;
    private Button mBtnBt2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_exercise);
        mBtnBt1 = findViewById(R.id.btnBt1);
        mBtnBt2 = findViewById(R.id.btnBt2);
        mBtnBt1.setOnClickListener(this);
        mBtnBt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnBt1:
//                Intent intent_bt1 =new Intent(this, );
//                this.startActivity(intent_bt1);
//                break;
            case R.id.btnBt2:
                Intent intent_bt2 = new Intent(this, CalculatorActivity.class);
                this.startActivity(intent_bt2);
                break;
        }
    }
}
