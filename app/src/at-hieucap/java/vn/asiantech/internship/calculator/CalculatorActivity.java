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
import android.widget.Toast;
import java.math.BigDecimal;
import vn.asiantech.internship.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText mEdtFirstTerm;
    private EditText mEdtSecondTerm;
    private TextView mTvOperation;
    private TextView mTvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Button mBtnSum = findViewById(R.id.btnSum);
        Button mBtnSub = findViewById(R.id.btnSub);
        Button mBtnMul = findViewById(R.id.btnMul);
        Button mBtnDivision = findViewById(R.id.btnDivision);
        mEdtFirstTerm = findViewById(R.id.edtFirstTerm);
        mEdtSecondTerm = findViewById(R.id.edtSecondTerm);
        mTvOperation = findViewById(R.id.tvOperation);
        mTvResult = findViewById(R.id.tvResult);

        mBtnSum.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMul.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
        mEdtFirstTerm.addTextChangedListener(this);
        mEdtSecondTerm.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (checkAllTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
            Toast.makeText(CalculatorActivity.this, "First term and second term no empty !", Toast.LENGTH_LONG).show();
        } else if (checkFirstTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
            Toast.makeText(CalculatorActivity.this, "First term no empty !", Toast.LENGTH_LONG).show();
        } else if (checkSecondTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
            Toast.makeText(CalculatorActivity.this, "Second term no empty !", Toast.LENGTH_LONG).show();
        } else {
            switch (v.getId()) {
                case R.id.btnSum:
                    mTvOperation.setText("+");
                    BigDecimal bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    BigDecimal bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.add(bdSecondTerm)));
                    break;
                case R.id.btnSub:
                    mTvOperation.setText("-");
                    bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.subtract(bdSecondTerm)));
                    break;
                case R.id.btnMul:
                    mTvOperation.setText("*");
                    bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.multiply(bdSecondTerm)));
                    break;
                case R.id.btnDivision:
                    if (checkSecondTerm(mEdtSecondTerm)) {
                        mTvResult.setText(" ");
                        Toast.makeText(CalculatorActivity.this, "Second term no value = 0 !", Toast.LENGTH_SHORT).show();
                    } else {
                        mTvOperation.setText("/");
                        bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                        bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                        mTvResult.setText(String.valueOf(bdFirstTerm.divide((bdSecondTerm), 4, BigDecimal.ROUND_HALF_UP)));
                    }
                    break;
            }
        }
    }

    private boolean checkAllTermIsEmpty(EditText edtFirstTerm, EditText edtSecondTerm) {
        return ((edtFirstTerm.getText().toString().trim().length() == 0) && (edtSecondTerm.getText().toString().trim().length() == 0));
    }

    private boolean checkFirstTermIsEmpty(EditText edtFirstTerm, EditText edtSecondTerm) {
        return ((edtFirstTerm.getText().toString().trim().length() == 0) && (edtSecondTerm.getText().toString().trim().length() != 0));
    }

    private boolean checkSecondTermIsEmpty(EditText edtFirstTerm, EditText edtSecondTerm) {
        return ((edtFirstTerm.getText().toString().trim().length() != 0) && (edtSecondTerm.getText().toString().trim().length() == 0));
    }

    private boolean checkSecondTerm(EditText edtSecondTerm) {
        return ((edtSecondTerm).getText().toString().equals("0"));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mTvResult.setText(" ");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}