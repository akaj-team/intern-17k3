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

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtNumberA;
    private EditText mEdtNumberB;
    private Button mBtnPlus;
    private Button mBtnMinus;
    private Button mBtnMultiplication;
    private Button mBtnDivision;
    private TextView mTvCalculation;
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initViews();
        setTextWatcher();
        implementClicks();
    }

    private void initViews() {
        mBtnPlus = findViewById(R.id.btnPlus);
        mBtnMinus = findViewById(R.id.btnMinus);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
        mEdtNumberA = findViewById(R.id.edtNumberA);
        mEdtNumberB = findViewById(R.id.edtNumberB);
        mTvCalculation = findViewById(R.id.tvCalculation);
        mTvResult = findViewById(R.id.tvResult);
    }

    private void implementClicks() {
        mBtnPlus.setOnClickListener(this);
        mBtnMinus.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    private void setTextWatcher() {
        mEdtNumberA.addTextChangedListener(textWatcher);
        mEdtNumberB.addTextChangedListener(textWatcher);
    }

    private void isEnableBtn(Boolean aBoolean) {
        mBtnPlus.setEnabled(aBoolean);
        mBtnMinus.setEnabled(aBoolean);
        mBtnMultiplication.setEnabled(aBoolean);
        mBtnDivision.setEnabled(aBoolean);
    }

    @Override
    public void onClick(View view) {
        double result = 0;
        double numberA = Double.parseDouble(mEdtNumberA.getText().toString().trim());
        double numberB = Double.parseDouble(mEdtNumberB.getText().toString().trim());
        switch (view.getId()) {
            case R.id.btnPlus:
                mTvCalculation.setText("+");
                result = plus(numberA, numberB);
                convertResult(result);
                break;
            case R.id.btnMinus:
                mTvCalculation.setText("-");
                result = minus(numberA, numberB);
                convertResult(result);
                break;
            case R.id.btnMultiplication:
                mTvCalculation.setText("*");
                result = multiplication(numberA, numberB);
                convertResult(result);
                break;
            case R.id.btnDivision:
                if (Double.parseDouble(mEdtNumberB.getText().toString().trim()) == 0) {
                    mTvCalculation.setText("");
                    mTvResult.setText("");
                    Toast.makeText(CalculatorActivity.this, R.string.validate_number_0, Toast.LENGTH_SHORT).show();
                } else {
                    mTvCalculation.setText("/");
                    result = division(numberA, numberB);
                    convertResult(result);
                }
                break;
        }

    }

    private void convertResult(double result) {
        int temp = (int) result;
        if (result == temp) {
            mTvResult.setText(String.valueOf(temp));
        } else {
            mTvResult.setText(String.valueOf(result));
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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No-op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No-op
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(mEdtNumberA.getText()) || TextUtils.isEmpty(mEdtNumberB.getText())) {
                isEnableBtn(false);
                Toast.makeText(CalculatorActivity.this, R.string.validate_input_data, Toast.LENGTH_LONG).show();
            } else {
                isEnableBtn(true);
            }
        }
    };
}
