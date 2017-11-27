package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CaculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtNumberA;
    private EditText mEdtNumberB;
    private Button mBtnSum;
    private Button mBtnSubtraction;
    private Button mBtnMultiplication;
    private Button mBtnDivision;
    private TextView mTvResult;
    private TextView mTvCaculation;
    private Caculation mCaculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculator_);
        innitsViewandClick();
    }

    private void innitsViewandClick() {
        mEdtNumberA = findViewById(R.id.edtNumberA);
        mEdtNumberB = findViewById(R.id.edtNumberB);
        mBtnSum = findViewById(R.id.btnSum);
        mBtnSubtraction = findViewById(R.id.btnSubtraction);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
        mTvResult = findViewById(R.id.tvResult);
        mTvCaculation = findViewById(R.id.tvCalculation);
        mCaculation = new Caculation();
        mBtnSum.setOnClickListener(this);
        mBtnSubtraction.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    @Override
    // Onclick Button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSum:
                getCaculator(mBtnSum);
                mCaculation.sum(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                break;
            case R.id.btnSubtraction:
                getCaculator(mBtnSubtraction);
                mCaculation.subTraction(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                break;
            case R.id.btnMultiplication:
                getCaculator(mBtnMultiplication);
                mCaculation.mulTiplication(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                break;
            case R.id.btnDivision:
                getCaculator(mBtnDivision);
                mCaculation.diVision(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                break;
        }
    }

    private void getCaculator(Button btnCaculator) {
        mTvCaculation.setText(btnCaculator.getText().toString());
    }
}