package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {
    private EditText mEdtNumOne;
    private EditText mEdtNumTwo;
    private TextView mTvResult;
    private TextView mTvCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mEdtNumOne = findViewById(R.id.edtNumOne);
        mEdtNumTwo = findViewById(R.id.edtNumTwo);
        mTvResult = findViewById(R.id.tvResult);
        mTvCalculator = findViewById(R.id.tvCalculator);
        Button mBtnSummation = findViewById(R.id.btnSummation);
        Button mBtnSubtraction = findViewById(R.id.btnSubtraction);
        Button mBtnMultiplication = findViewById(R.id.btnMultiplication);
        Button mBtnDivision = findViewById(R.id.btnDivision);
        mBtnSummation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEdtNumOne.getText().length() == 0 || mEdtNumTwo.getText().length() == 0) {
                    Toast.makeText(CalculatorActivity.this, "Input all", Toast.LENGTH_SHORT).show();
                } else {
                    mTvCalculator.setText("+");
                    float a = summation(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                    mTvResult.setText("" + a);
                }
            }
        });

        mBtnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEdtNumOne.getText().length() == 0 || mEdtNumTwo.getText().length() == 0) {
                    Toast.makeText(CalculatorActivity.this, "Input all", Toast.LENGTH_SHORT).show();
                } else {
                    mTvCalculator.setText("-");
                    float a = subtraction(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                    mTvResult.setText("" + a);
                }
            }
        });

        mBtnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEdtNumOne.getText().length() == 0 || mEdtNumTwo.getText().length() == 0) {
                    Toast.makeText(CalculatorActivity.this, "Input all", Toast.LENGTH_SHORT).show();
                } else {
                    mTvCalculator.setText("*");
                    float a = multiplication(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                    mTvResult.setText("" + a);
                }
            }
        });

        mBtnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEdtNumOne.getText().length() == 0 || mEdtNumTwo.getText().length() == 0) {
                    Toast.makeText(CalculatorActivity.this, "Input all", Toast.LENGTH_SHORT).show();
                } else {
                    if (Integer.parseInt(mEdtNumTwo.getText().toString()) == 0) {
                        Toast.makeText(CalculatorActivity.this, "Num two isn't 0", Toast.LENGTH_SHORT).show();
                    } else {
                        mTvCalculator.setText("/");
                        float a = division(Float.parseFloat(mEdtNumOne.getText().toString()), Float.parseFloat(mEdtNumTwo.getText().toString()));
                        mTvResult.setText("" + a);
                    }
                }
            }
        });

    }

    private float summation(float a, float b) {
        return a + b;
    }

    private float subtraction(float a, float b) {
        return a - b;
    }

    private float multiplication(float a, float b) {
        return a * b;
    }

    private float division(float a, float b) {
        return a / b;
    }
}
