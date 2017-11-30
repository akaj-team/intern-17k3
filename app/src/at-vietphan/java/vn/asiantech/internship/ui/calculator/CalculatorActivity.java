package vn.asiantech.internship.ui.calculator;

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

import vn.asiantech.internship.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtNumberA;
    private EditText mEdtNumberB;
    private Button mBtnPlus;
    private Button mBtnMinus;
    private Button mBtnMultiplication;
    private Button mBtnDivision;
    private TextView mTvCalculation;
    private TextView mTvResult;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No-op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (TextUtils.isEmpty(mEdtNumberA.getText()) || TextUtils.isEmpty(mEdtNumberB.getText())) {
                isEnableBtn(false);
                Toast.makeText(CalculatorActivity.this, R.string.validate_input_data, Toast.LENGTH_LONG).show();
            } else {
                isEnableBtn(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // No-op
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initViews();
        setTextWatcher();
        initListener();
    }

    private void initViews() {
        mEdtNumberA = findViewById(R.id.edtNumberA);
        mEdtNumberB = findViewById(R.id.edtNumberB);
        mBtnPlus = findViewById(R.id.btnPlus);
        mBtnMinus = findViewById(R.id.btnMinus);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
        mTvCalculation = findViewById(R.id.tvCalculation);
        mTvResult = findViewById(R.id.tvResult);
    }

    private void initListener() {
        mBtnPlus.setOnClickListener(this);
        mBtnMinus.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    private void setTextWatcher() {
        mEdtNumberA.addTextChangedListener(textWatcher);
        mEdtNumberB.addTextChangedListener(textWatcher);
    }

    private void isEnableBtn(Boolean isCheck) {
        mBtnPlus.setEnabled(isCheck);
        mBtnMinus.setEnabled(isCheck);
        mBtnMultiplication.setEnabled(isCheck);
        mBtnDivision.setEnabled(isCheck);
    }

    @Override
    public void onClick(View view) {
        double numberA = Double.parseDouble(mEdtNumberA.getText().toString().trim());
        double numberB = Double.parseDouble(mEdtNumberB.getText().toString().trim());
        switch (view.getId()) {
            case R.id.btnPlus:
                mTvCalculation.setText(R.string.plus);
                mTvResult.setText(String.valueOf(plus(numberA, numberB)));
                break;
            case R.id.btnMinus:
                mTvCalculation.setText(R.string.minus);
                mTvResult.setText(String.valueOf(minus(numberA, numberB)));
                break;
            case R.id.btnMultiplication:
                mTvCalculation.setText(R.string.mul);
                mTvResult.setText(String.valueOf(multiplication(numberA, numberB)));
                break;
            case R.id.btnDivision:
                mTvCalculation.setText(R.string.div);
                if (numberB == 0) {
                    mTvResult.setText(R.string.result_null);
                    Toast.makeText(CalculatorActivity.this, R.string.validate_number_0, Toast.LENGTH_SHORT).show();
                } else {
                    mTvResult.setText(String.valueOf(division(numberA, numberB)));
                }
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
        return a / b;
    }
}
