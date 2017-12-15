package vn.asiantech.internship.ui.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import vn.asiantech.internship.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText mEdtFirstTerm;
    private EditText mEdtSecondTerm;
    private TextView mTvOperation;
    private TextView mTvResult;
    private Button mBtnSum;
    private Button mBtnSub;
    private Button mBtnMul;
    private Button mBtnDivision;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initViews();
        addListener();
    }

    private void addListener() {
        mBtnSum.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMul.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
        mEdtFirstTerm.addTextChangedListener(this);
        mEdtSecondTerm.addTextChangedListener(this);
    }

    private void initViews() {
        mBtnSum = findViewById(R.id.btnSum);
        mBtnSub = findViewById(R.id.btnSub);
        mBtnMul = findViewById(R.id.btnMul);
        mBtnDivision = findViewById(R.id.btnDivision);
        mEdtFirstTerm = findViewById(R.id.edtFirstTerm);
        mEdtSecondTerm = findViewById(R.id.edtSecondTerm);
        mTvOperation = findViewById(R.id.tvOperation);
        mTvResult = findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(mEdtFirstTerm.getText().toString()) && TextUtils.isEmpty(mEdtSecondTerm.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.event_two_term_is_empty, Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(mEdtFirstTerm.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.event_first_term_is_empty, Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(mEdtSecondTerm.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.event_second_term_is_empty, Toast.LENGTH_SHORT).show();

        } else {
            switch (v.getId()) {
                case R.id.btnSum:
                    mTvOperation.setText(R.string.sum);
                    BigDecimal bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    BigDecimal bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.add(bdSecondTerm)));
                    break;
                case R.id.btnSub:
                    mTvOperation.setText(R.string.sub);
                    bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.subtract(bdSecondTerm)));
                    break;
                case R.id.btnMul:
                    mTvOperation.setText(R.string.mul);
                    bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.multiply(bdSecondTerm)));
                    break;
                case R.id.btnDivision:
                    if ((mEdtSecondTerm).getText().toString().equals(getApplicationContext().getText(R.string.value_0))) {
                        mTvResult.setText(R.string.text_null);
                        Toast.makeText(getApplicationContext(), R.string.event_second_term_is_value_0, Toast.LENGTH_SHORT).show();

                    } else {
                        mTvOperation.setText(R.string.division);
                        bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                        bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                        mTvResult.setText(String.valueOf(bdFirstTerm.divide((bdSecondTerm), 4, BigDecimal.ROUND_HALF_UP)));
                    }
                    break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mBtnSum.isEnabled()&&mBtnSub.isEnabled()&&mBtnSum.isEnabled()&&mBtnDivision.isEnabled()) {
            mTvResult.setText(R.string.text_null);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
