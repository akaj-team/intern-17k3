package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText mEdtNumOne;
    private EditText mEdtNumTwo;
    private TextView mTvResult;
    private TextView mTvCalculator;
    private Button mBtnSummation;
    private Button mBtnSubtraction;
    private Button mBtnMultiplication;
    private Button mBtnDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initViews();
        initListener();
    }

    private void initViews() {
        mEdtNumOne = findViewById(R.id.edtNumOne);
        mEdtNumTwo = findViewById(R.id.edtNumTwo);
        mTvResult = findViewById(R.id.tvResult);
        mTvCalculator = findViewById(R.id.tvCalculator);
        mBtnSummation = findViewById(R.id.btnSummation);
        mBtnSubtraction = findViewById(R.id.btnSubtraction);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
    }

    private void initListener() {
        mEdtNumOne.addTextChangedListener(this);
        mEdtNumTwo.addTextChangedListener(this);
        mBtnSummation.setOnClickListener(this);
        mBtnSubtraction.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    private void setEnableButton(boolean isCheck) {
        mBtnSummation.setEnabled(isCheck);
        mBtnSubtraction.setEnabled(isCheck);
        mBtnMultiplication.setEnabled(isCheck);
        mBtnDivision.setEnabled(isCheck);
    }

    @Override
    public void onClick(View view) {
        float result=0;
        switch (view.getId()) {
            case R.id.btnSummation:
                mTvCalculator.setText(R.string.summation);
                result = summation(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                break;
            case R.id.btnSubtraction:
                mTvCalculator.setText(R.string.subtraction);
                result = subtraction(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                break;
            case R.id.btnMultiplication:
                mTvCalculator.setText(R.string.multiplication);
                result = multiplication(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                break;
            case R.id.btnDivision:
                if (Integer.parseInt(mEdtNumTwo.getText().toString()) == 0) {
                    mTvResult.setText(R.string.division_0);
                    showToastNotifyNumberTwo();
                } else {
                    mTvCalculator.setText(R.string.division);
                    result = division(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                }
                break;
        }
        mTvResult.setText(String.valueOf(result));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //NO-OP
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //NO-OP
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(mEdtNumOne.getText().toString()) ||TextUtils.isEmpty(mEdtNumTwo.getText().toString())) {
            setEnableButton(false);
        } else {
            setEnableButton(true);
        }
    }

    private float summation(float a, float b) {
        return a + b;
    }

    private float subtraction(float a, float b) {
        return a - b;
    }

    private float multiplication(float a, float b) {
        return a * b;
    }

    private float division(float a, float b) {
        return a / b;
    }

    private void showToastNotifyNumberTwo() {
        Toast.makeText(CalculatorActivity.this, R.string.notify_numtwo_0, Toast.LENGTH_SHORT).show();
    }
}
