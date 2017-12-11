package vn.asiantech.internship.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Created at 2017
 * Created by jackty on 05/12/2017.
 */
public class ExerciseManagementActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mExercise1;
    private Button mExercise2;
    private Button mExercise3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        initViews();
        initListeners();
    }

    private void initViews() {
        mExercise1 = findViewById(R.id.btnExercise1);
        mExercise2 = findViewById(R.id.btnExercise2);
        mExercise3 = findViewById(R.id.btnExercise3);
    }

    private void initListeners() {
        mExercise1.setOnClickListener(this);
        mExercise2.setOnClickListener(this);
        mExercise3.setOnClickListener(this);
    }

    /**
     * Onclick Button
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnExercise1:
                startActivity(new Intent(this, SharedPreferencesActivity.class));
                break;
            case R.id.btnExercise2:
                startActivity(new Intent(this, WriteExternalActivity.class));
                break;
            case R.id.btnExercise3:
                startActivity(new Intent(this, SqLiteActivity.class));
                break;

        }
    }
}
