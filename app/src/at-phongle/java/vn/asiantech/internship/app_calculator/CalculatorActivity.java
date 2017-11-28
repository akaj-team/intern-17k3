package vn.asiantech.internship.app_calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText mEdtNumberA;
    private EditText mEdtNumberB;
    private TextView mTvOperator;
    private TextView mTvResult;
    private Button mBtnAdd;
    private Button mBtnSub;
    private Button mBtnMul;
    private Button mBtnDiv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);
        initViews();
        mEdtNumberA.addTextChangedListener(this);
        mEdtNumberB.addTextChangedListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMul.setOnClickListener(this);
        mBtnDiv.setOnClickListener(this);
    }

    private void initViews() {
        mEdtNumberA = findViewById(R.id.edtNumberA);
        mEdtNumberB = findViewById(R.id.edtNumberB);
        mBtnAdd = findViewById(R.id.btAdd);
        mBtnSub = findViewById(R.id.btSub);
        mBtnMul = findViewById(R.id.btMul);
        mBtnDiv = findViewById(R.id.btDiv);
        mTvOperator = findViewById(R.id.tvOperator);
        mTvResult = findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdd:
                mTvOperator.setText("+");
                if (Float.isInfinite(operator(mEdtNumberA, mEdtNumberB, '+'))) {
                    mTvResult.setText("Số quá giới hạn cho phép !");
                } else {
                    mTvResult.setText(String.valueOf(operator(mEdtNumberA, mEdtNumberB, '+')));
                }
                break;
            case R.id.btSub:
                mTvOperator.setText("-");
                if (Float.isInfinite(operator(mEdtNumberA, mEdtNumberB, '-'))) {
                    mTvResult.setText("Số quá giới hạn cho phép !");
                } else {
                    mTvResult.setText(String.valueOf(operator(mEdtNumberA, mEdtNumberB, '-')));
                }
                break;
            case R.id.btMul:
                mTvOperator.setText("*");
                if (Float.isInfinite(operator(mEdtNumberA, mEdtNumberB, '*'))) {
                    mTvResult.setText("Số quá giới hạn cho phép !");
                } else {
                    mTvResult.setText(String.valueOf(operator(mEdtNumberA, mEdtNumberB, '*')));
                }
                break;
            case R.id.btDiv:
                mTvOperator.setText("/");
                if (Float.isInfinite(operator(mEdtNumberA, mEdtNumberB, '/'))) {
                    mTvResult.setText("Số quá giới hạn cho phép !");
                } else {
                    mTvResult.setText(String.valueOf(operator(mEdtNumberA, mEdtNumberB, '/')));
                }
                break;
        }
    }

    private void setButtonEnable() {
        mBtnAdd.setEnabled(checkInvalidNumber(mEdtNumberA, mEdtNumberB));
        mBtnSub.setEnabled(checkInvalidNumber(mEdtNumberA, mEdtNumberB));
        mBtnMul.setEnabled(checkInvalidNumber(mEdtNumberA, mEdtNumberB));
        mBtnDiv.setEnabled(checkInvalidNumber(mEdtNumberA, mEdtNumberB));
        mBtnDiv.setEnabled(checkZeroNumber(mEdtNumberB));
    }

    private float operator(EditText edtA, EditText edtB, char operator) {
        float numA = Float.parseFloat(edtA.getText().toString());
        float numB = Float.parseFloat(edtB.getText().toString());
        switch (operator) {
            case '+':
                return numA + numB;
            case '-':
                return numA - numB;
            case '*':
                return numA * numB;
            case '/':
                return numA / numB;
            default:
                return 0;
        }
    }

    private boolean checkInvalidNumber(EditText edtA, EditText edtB) {
        try {
            Float.parseFloat(edtA.getText().toString());
            Float.parseFloat(edtB.getText().toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean checkZeroNumber(EditText edt) {
        try {
            Float num = Float.parseFloat(edt.getText().toString());
            return num != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        setButtonEnable();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setButtonEnable();
    }

    @Override
    public void afterTextChanged(Editable s) {
        setButtonEnable();
    }
}
