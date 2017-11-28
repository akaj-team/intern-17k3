package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private static final String NOTIFY_NUMBER_TWO = "Second number can't 0";
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
        initItem();
        initListener();
    }

    private void initItem() {
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

    private void setEnabledButton(Boolean mBoolean) {
        mBtnSummation.setEnabled(mBoolean);
        mBtnSubtraction.setEnabled(mBoolean);
        mBtnMultiplication.setEnabled(mBoolean);
        mBtnDivision.setEnabled(mBoolean);
    }

    @Override
    public void onClick(View view) {
        float a;
        switch (view.getId()) {
            case R.id.btnSummation:
                mTvCalculator.setText(R.string.summation);
                a = summation(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                mTvResult.setText(String.valueOf(a));
                break;
            case R.id.btnSubtraction:
                mTvCalculator.setText(R.string.subtraction);
                a = subtraction(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                mTvResult.setText(String.valueOf(a));
                break;
            case R.id.btnMultiplication:
                mTvCalculator.setText(R.string.multiplication);
                a = multiplication(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                mTvResult.setText(String.valueOf(a));
                break;
            case R.id.btnDivision:
                if (Integer.parseInt(mEdtNumTwo.getText().toString()) == 0) {
                    showToastNotify(NOTIFY_NUMBER_TWO);
                } else {
                    mTvCalculator.setText(R.string.division);
                    a = division(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                    mTvResult.setText(String.valueOf(a));
                }
        }
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
        if (mEdtNumOne.getText().length() == 0 || mEdtNumTwo.getText().length() == 0) {
            setEnabledButton(false);
        } else {
            setEnabledButton(true);
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

    private void showToastNotify(String notify) {
        Toast.makeText(CalculatorActivity.this, notify, Toast.LENGTH_SHORT).show();
    }
}
