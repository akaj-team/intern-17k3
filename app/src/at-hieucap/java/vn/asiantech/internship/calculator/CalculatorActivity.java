package vn.asiantech.internship.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;
import vn.asiantech.internship.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSum:
                if (checkAllTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "First term and second term no empty !", Toast.LENGTH_LONG).show();
                } else if (checkFirstTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "First term no empty !", Toast.LENGTH_LONG).show();
                } else if (checkSecondTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "Second term no empty !", Toast.LENGTH_LONG).show();
                } else {
                    mTvOperation.setText("+");
                    BigDecimal bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    BigDecimal bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.add(bdSecondTerm)));
                }
                break;
            case R.id.btnSub:
                if (checkAllTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "First term and second term no empty !", Toast.LENGTH_SHORT).show();
                } else if (checkFirstTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "First term no empty !", Toast.LENGTH_SHORT).show();
                } else if (checkSecondTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "Second term no empty !", Toast.LENGTH_SHORT).show();
                } else {
                    mTvOperation.setText("-");
                    BigDecimal bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    BigDecimal bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.subtract(bdSecondTerm)));
                }
                break;
            case R.id.btnMul:
                if (checkAllTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "First term and second term no empty !", Toast.LENGTH_SHORT).show();
                } else if (checkFirstTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "First term no empty !", Toast.LENGTH_SHORT).show();
                } else if (checkSecondTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "Second term no empty !", Toast.LENGTH_SHORT).show();
                } else {
                    mTvOperation.setText("*");
                    BigDecimal bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                    BigDecimal bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                    mTvResult.setText(String.valueOf(bdFirstTerm.multiply(bdSecondTerm)));
                }
                break;
            case R.id.btnDivision:
                if (checkAllTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "First term and second term no empty !", Toast.LENGTH_SHORT).show();
                } else if (checkFirstTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "First term no empty !", Toast.LENGTH_SHORT).show();
                } else if (checkSecondTermIsEmpty(mEdtFirstTerm, mEdtSecondTerm)) {
                    Toast.makeText(CalculatorActivity.this, "Second term no empty !", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkSecondTerm(mEdtSecondTerm)) {
                        Toast.makeText(CalculatorActivity.this, "Second term no value = 0 !", Toast.LENGTH_SHORT).show();
                    } else {
                        mTvOperation.setText("/");
                        BigDecimal bdFirstTerm = new BigDecimal(mEdtFirstTerm.getText().toString());
                        BigDecimal bdSecondTerm = new BigDecimal(mEdtSecondTerm.getText().toString());
                        mTvResult.setText(String.valueOf(bdFirstTerm.divide((bdSecondTerm), 4, BigDecimal.ROUND_HALF_UP)));
                    }
                }
                break;
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
}
