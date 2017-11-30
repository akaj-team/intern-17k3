package vn.asiantech.internship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText medtNumberA;
    private EditText medtNumberB;
    private Button mbtnPlus;
    private Button mbtnSub;
    private Button mbtnMul;
    private Button mbtnDivi;
    private TextView mtvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initView();
        initListener();
        setTextWatcher();
        checkButton();
    }

    private void initView() {
        medtNumberA = findViewById(R.id.edtNumA);
        medtNumberB = findViewById(R.id.edtNumB);
        mbtnPlus = findViewById(R.id.btnPlus);
        mbtnSub = findViewById(R.id.btnSub);
        mbtnMul = findViewById(R.id.btnMultip);
        mbtnDivi = findViewById(R.id.btnDivision);
        mtvResult = findViewById(R.id.tvResult);
    }

    private void initListener() {
        mbtnPlus.setOnClickListener(this);
        mbtnSub.setOnClickListener(this);
        mbtnMul.setOnClickListener(this);
        mbtnDivi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        double numberA = Double.parseDouble(medtNumberA.getText().toString().trim());
        double numberB = Double.parseDouble(medtNumberB.getText().toString().trim());
        double result;
        switch (view.getId()) {
            case R.id.btnPlus:
                result = plus(numberA, numberB);
                mtvResult.setText(String.valueOf(result));
                break;
            case R.id.btnSub:
                result = minus(numberA, numberB);
                mtvResult.setText(String.valueOf(result));
                break;
            case R.id.btnMultip:
                result = multiplication(numberA, numberB);
                mtvResult.setText(String.valueOf(result));
                break;
            case R.id.btnDivision:
                result = division(numberA, numberB);
                mtvResult.setText(String.valueOf(result));
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

    private void setTextWatcher() {
        medtNumberA.addTextChangedListener(new TextWatcher() {
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
                checkButton();
            }
        });
        medtNumberB.addTextChangedListener(new TextWatcher() {
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
                checkButton();
            }
        });

    }

    private void checkButton() {
        if (!TextUtils.isEmpty(medtNumberA.getText().toString()) && !TextUtils.isEmpty(medtNumberB.getText().toString())) {
            mbtnPlus.setEnabled(true);
            mbtnSub.setEnabled(true);
            mbtnMul.setEnabled(true);
            mbtnDivi.setEnabled(true);

        } else {
            mbtnPlus.setEnabled(false);
            mbtnSub.setEnabled(false);
            mbtnMul.setEnabled(false);
            mbtnDivi.setEnabled(false);
        }
    }
}
