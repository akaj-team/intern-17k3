package vn.asiantech.internship.calculator;

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
        addListener();
    }

    private void initViews() {
        mEdtNumberA = findViewById(R.id.edtNumberA);
        mEdtNumberB = findViewById(R.id.edtNumberB);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnSub = findViewById(R.id.btnSub);
        mBtnMul = findViewById(R.id.btnMul);
        mBtnDiv = findViewById(R.id.btnDiv);
        mTvOperator = findViewById(R.id.tvOperator);
        mTvResult = findViewById(R.id.tvResult);
    }

    private void addListener() {
        mEdtNumberA.addTextChangedListener(this);
        mEdtNumberB.addTextChangedListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMul.setOnClickListener(this);
        mBtnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Float numA = Float.parseFloat(mEdtNumberA.getText().toString());
        Float numB = Float.parseFloat(mEdtNumberB.getText().toString());
        switch (v.getId()) {
            case R.id.btnAdd:
                mTvOperator.setText(getString(R.string.btn_add));
                if (Float.isInfinite(operator(numA, numB, '+'))) {
                    mTvResult.setText(R.string.tv_fail);
                } else {
                    mTvResult.setText(String.valueOf(operator(numA, numB, '+')));
                }
                break;
            case R.id.btnSub:
                mTvOperator.setText(getString(R.string.btn_sub));
                if (Float.isInfinite(operator(numA, numB, '-'))) {
                    mTvResult.setText(R.string.tv_fail);
                } else {
                    mTvResult.setText(String.valueOf(operator(numA, numB, '-')));
                }
                break;
            case R.id.btnMul:
                mTvOperator.setText(getString(R.string.btn_mul));
                if (Float.isInfinite(operator(numA, numB, '*'))) {
                    mTvResult.setText(R.string.tv_fail);
                } else {
                    mTvResult.setText(String.valueOf(operator(numA, numB, '*')));
                }
                break;
            case R.id.btnDiv:
                mTvOperator.setText(getString(R.string.btn_div));
                if (Float.isInfinite(operator(numA, numB, '/'))) {
                    mTvResult.setText(R.string.tv_fail);
                } else {
                    mTvResult.setText(String.valueOf(operator(numA, numB, '/')));
                }
                break;
        }
    }

    private void setButtonEnable() {
        String numA = mEdtNumberA.getText().toString();
        String numB = mEdtNumberB.getText().toString();
        mBtnAdd.setEnabled(!numA.isEmpty() && !numB.isEmpty());
        mBtnSub.setEnabled(!numA.isEmpty() && !numB.isEmpty());
        mBtnMul.setEnabled(!numA.isEmpty() && !numB.isEmpty());
        mBtnDiv.setEnabled(!numA.isEmpty() && !numB.isEmpty() && checkZeroNumber(numB));
    }

    private float operator(Float numA, Float numB, char operator) {
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

    private boolean checkZeroNumber(String edt) {
        try {
            Float num = Float.parseFloat(edt);
            return num != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setButtonEnable();
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
