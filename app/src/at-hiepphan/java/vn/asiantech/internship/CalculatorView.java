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

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorView extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtFirstNumber;
    private EditText mEdtSecondNumber;
    private Button mBtnAddition;
    private Button mBtnSubtraction;
    private Button mBtnMultiplication;
    private Button mBtnDivision;
    private TextView mTvOperator;
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_view);
        initView();
        onClickListener();
        checkInputData();
    }

    private void initView() {
        mEdtFirstNumber = findViewById(R.id.edtFirstNumber);
        mEdtSecondNumber = findViewById(R.id.edtSecondNumber);
        mBtnAddition = findViewById(R.id.btnAddition);
        mBtnSubtraction = findViewById(R.id.btnSubtraction);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
        mTvOperator = findViewById(R.id.tvOperator);
        mTvResult = findViewById(R.id.tvResult);
    }

    private void onClickListener() {
        mBtnAddition.setOnClickListener(this);
        mBtnSubtraction.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (checkInput()) {
            BigDecimal firstNumber = new BigDecimal(mEdtFirstNumber.getText().toString());
            BigDecimal secondNumber = new BigDecimal(mEdtSecondNumber.getText().toString());
            switch (view.getId()) {
                case R.id.btnAddition:
                    mTvOperator.setText(this.getString(R.string.textview_text_addition));
                    addition(firstNumber, secondNumber);
                    break;
                case R.id.btnSubtraction:
                    mTvOperator.setText(this.getString(R.string.textview_text_subtraction));
                    subtraction(firstNumber, secondNumber);
                    break;
                case R.id.btnMultiplication:
                    mTvOperator.setText(this.getString(R.string.textview_text_multiplication));
                    multiplication(firstNumber, secondNumber);
                    break;
                case R.id.btnDivision:
                    mTvOperator.setText(this.getString(R.string.textview_text_division));
                    division(firstNumber, secondNumber);
                    break;
            }
        }
    }

    private void checkInputData() {
        mEdtFirstNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkClickButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op
            }
        });

        mEdtSecondNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkClickButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op
            }
        });
    }

    private void addition(BigDecimal firstNumber, BigDecimal secondNumber) {
        mTvResult.setText(String.valueOf(firstNumber.add(secondNumber)));
    }

    private void subtraction(BigDecimal firstNumber, BigDecimal secondNumber) {
        mTvResult.setText(String.valueOf(firstNumber.subtract(secondNumber)));
    }

    private void multiplication(BigDecimal firstNumber, BigDecimal secondNumber) {
        mTvResult.setText(String.valueOf(firstNumber.multiply(secondNumber)));
    }

    private void division(BigDecimal firstNumber, BigDecimal secondNumber) {
        if (secondNumber.compareTo(BigDecimal.ZERO) == 0) {
            Toast.makeText(this, this.getString(R.string.toast_text_error_zero), Toast.LENGTH_LONG).show();
            return;
        }
        BigDecimal resultOne = firstNumber.divide(secondNumber, 0, RoundingMode.DOWN);
        BigDecimal resultTwo = firstNumber.divide(secondNumber, 5, RoundingMode.DOWN);
        if (resultOne.compareTo(resultTwo) == 0) {
            mTvResult.setText(String.valueOf(resultOne));
        } else {
            mTvResult.setText(String.valueOf(resultTwo));
        }
    }

    private boolean checkInput() {
        if (mEdtFirstNumber.length() == 0 || mEdtSecondNumber.length() == 0) {
            Toast.makeText(this, this.getString(R.string.toast_text_notification_empty), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void checkClickButton() {
        if (!TextUtils.isEmpty(mEdtFirstNumber.getText().toString()) || !TextUtils.isEmpty(mEdtSecondNumber.getText().toString())) {
            mBtnAddition.setEnabled(true);
            mBtnSubtraction.setEnabled(true);
            mBtnMultiplication.setEnabled(true);
            mBtnDivision.setEnabled(true);
        } else {
            mBtnAddition.setEnabled(false);
            mBtnSubtraction.setEnabled(false);
            mBtnMultiplication.setEnabled(false);
            mBtnDivision.setEnabled(false);
        }
    }
}
