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
        initView();
        checkAllInputData();
        implementClick();

    }

    private void initView() {
        mBtnPlus = findViewById(R.id.btnPlus);
        mBtnMinus = findViewById(R.id.btnMinus);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
        mEdtNumberA = findViewById(R.id.edtNumberA);
        mEdtNumberB = findViewById(R.id.edtNumberB);
        mTvCalculation = findViewById(R.id.tvCalculation);
        mTvResult = findViewById(R.id.tvResult);
    }

    private void implementClick() {
        mBtnPlus.setOnClickListener(this);
        mBtnMinus.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    private void checkAllInputData() {
        checkEdtInputData(mEdtNumberA);
        checkEdtInputData(mEdtNumberB);
    }

    private void checkEdtInputData(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
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
                    mBtnPlus.setEnabled(false);
                    mBtnMinus.setEnabled(false);
                    mBtnMultiplication.setEnabled(false);
                    mBtnDivision.setEnabled(false);
                    Toast.makeText(CalculatorActivity.this, "Number A or Number B is Empty !", Toast.LENGTH_LONG).show();
                } else {
                    mBtnPlus.setEnabled(true);
                    mBtnMinus.setEnabled(true);
                    mBtnMultiplication.setEnabled(true);
                    mBtnDivision.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        double result;
        switch (view.getId()) {
            case R.id.btnPlus:
                mTvCalculation.setText("+");
                result = plus(Double.parseDouble(mEdtNumberA.getText().toString()), Double.parseDouble(mEdtNumberB.getText().toString()));
                mTvResult.setText(String.valueOf(result));
                break;
            case R.id.btnMinus:
                mTvCalculation.setText("-");
                result = minus(Double.parseDouble(mEdtNumberA.getText().toString()), Double.parseDouble(mEdtNumberB.getText().toString()));
                mTvResult.setText(String.valueOf(result));
                break;
            case R.id.btnMultiplication:
                mTvCalculation.setText("*");
                result = multiplication(Double.parseDouble(mEdtNumberA.getText().toString()), Double.parseDouble(mEdtNumberB.getText().toString()));
                mTvResult.setText(String.valueOf(result));
                break;
            case R.id.btnDivision:
                if (Integer.parseInt(mEdtNumberB.getText().toString()) == 0) {
                    mTvResult.setText("");
                    Toast.makeText(CalculatorActivity.this, "Number B other number 0", Toast.LENGTH_SHORT).show();
                } else {
                    mTvCalculation.setText("/");
                    result = division(Double.parseDouble(mEdtNumberA.getText().toString()), Double.parseDouble(mEdtNumberB.getText().toString()));
                    mTvResult.setText(String.valueOf(result));
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

