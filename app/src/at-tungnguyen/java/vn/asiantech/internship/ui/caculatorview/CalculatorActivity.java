package vn.asiantech.internship.ui.caculatorview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.asiantech.internship.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdtNumberA;
    private EditText mEdtNumberB;
    private Button mBtnPlus;
    private Button mBtnSub;
    private Button mBtnMul;
    private Button mBtnDivi;
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initViews();
        initListener();
        setTextWatcher();
    }

    private void initViews() {
        mEdtNumberA = findViewById(R.id.edtNumA);
        mEdtNumberB = findViewById(R.id.edtNumB);
        mBtnPlus = findViewById(R.id.btnPlus);
        mBtnSub = findViewById(R.id.btnSub);
        mBtnMul = findViewById(R.id.btnMultip);
        mBtnDivi = findViewById(R.id.btnDivision);
        mTvResult = findViewById(R.id.tvResult);
    }

    private void initListener() {
        mBtnPlus.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMul.setOnClickListener(this);
        mBtnDivi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        double numberA = Double.parseDouble(mEdtNumberA.getText().toString().trim());
        double numberB = Double.parseDouble(mEdtNumberB.getText().toString().trim());
        double result;
        switch (view.getId()) {
            case R.id.btnPlus:
                result = plus(numberA, numberB);
                mTvResult.setText(String.valueOf(result));
                break;
            case R.id.btnSub:
                result = minus(numberA, numberB);
                mTvResult.setText(String.valueOf(result));
                break;
            case R.id.btnMultip:
                result = multiplication(numberA, numberB);
                mTvResult.setText(String.valueOf(result));
                break;
            case R.id.btnDivision:
                result = division(numberA, numberB);
                mTvResult.setText(String.valueOf(result));
                break;
        }
    }

    private double plus(double a, double b) {
        return a + b;
    }

    private double minus(double a, double b) {
        return a - b;
    }

    private double multiplication(double a, double b) {
        return a * b;
    }

    private double division(double a, double b) {
        if (b == 0) {
            Toast.makeText(this, R.string.tv_number_not_0, Toast.LENGTH_SHORT).show();
            return 0;
        }
        return a / b;
    }

    private void setTextWatcher() {
        mEdtNumberA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEnableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op
            }
        });
        mEdtNumberB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEnableButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op
            }
        });

    }

    private void checkEnableButton() {
        if (!TextUtils.isEmpty(mEdtNumberA.getText().toString()) && !TextUtils.isEmpty(mEdtNumberB.getText().toString())) {
            mBtnPlus.setEnabled(true);
            mBtnSub.setEnabled(true);
            mBtnMul.setEnabled(true);
            mBtnDivi.setEnabled(true);
        } else {
            mBtnPlus.setEnabled(false);
            mBtnSub.setEnabled(false);
            mBtnMul.setEnabled(false);
            mBtnDivi.setEnabled(false);
        }
    }
}
