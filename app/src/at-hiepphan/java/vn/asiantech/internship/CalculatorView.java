package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorView extends AppCompatActivity {
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
        mEdtFirstNumber = findViewById(R.id.edtFirstNumber);
        mEdtSecondNumber = findViewById(R.id.edtSecondNumber);
        mBtnAddition = findViewById(R.id.btnAddition);
        mBtnSubtraction = findViewById(R.id.btnSubtraction);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
        mTvOperator = findViewById(R.id.tvOperator);
        mTvResult = findViewById(R.id.tvResult);

        mBtnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvResult.setText("");
                mTvOperator.setText("+");
                if (checkInput()) {
                    final float firstNumber = Float.parseFloat(mEdtFirstNumber.getText().toString());
                    final float secondNumber = Float.parseFloat(mEdtSecondNumber.getText().toString());
                    addition(firstNumber, secondNumber);
                }
            }
        });

        mBtnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvResult.setText("");
                mTvOperator.setText("-");
                if (checkInput()) {
                    final float firstNumber = Float.parseFloat(mEdtFirstNumber.getText().toString());
                    final float secondNumber = Float.parseFloat(mEdtSecondNumber.getText().toString());
                    subtraction(firstNumber, secondNumber);
                }
            }
        });

        mBtnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvResult.setText("");
                mTvOperator.setText("*");
                if (checkInput()) {
                    final float firstNumber = Float.parseFloat(mEdtFirstNumber.getText().toString());
                    final float secondNumber = Float.parseFloat(mEdtSecondNumber.getText().toString());
                    multiplication(firstNumber, secondNumber);
                }
            }
        });

        mBtnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvResult.setText("");
                mTvOperator.setText("/");
                if (checkInput()) {
                    final float firstNumber = Float.parseFloat(mEdtFirstNumber.getText().toString());
                    final float secondNumber = Float.parseFloat(mEdtSecondNumber.getText().toString());
                    division(firstNumber, secondNumber);
                }
            }
        });
    }

    private void subtraction(float firstNumber, float secondNumber) {
        mTvResult.setText(String.valueOf(firstNumber - secondNumber));
    }

    private void addition(float firstNumber, float secondNumber) {
        final float result = firstNumber + secondNumber;
        mTvResult.setText(String.valueOf(result));
    }

    private void division(float firstNumber, float secondNumber) {
        if (Float.compare(secondNumber, 0) == 0) {
            Toast.makeText(this, "Error: divided by zero", Toast.LENGTH_LONG).show();
            mTvResult.setText(String.valueOf(Float.MAX_VALUE));
            return;
        }
        mTvResult.setText(String.valueOf(firstNumber / secondNumber));
    }

    private void multiplication(float firstNumber, float secondNumber) {
        mTvResult.setText(String.valueOf(firstNumber * secondNumber));
    }

    private boolean checkInput() {
        if (mEdtFirstNumber.length() == 0 || mEdtSecondNumber.length() == 0) {
            Toast.makeText(this, "Input must not be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
