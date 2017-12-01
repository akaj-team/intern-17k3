package vn.asiantech.internship.calculation;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdtNumberA;
    private EditText mEdtNumberB;
    private Button mBtnSum;
    private Button mBtnSubtraction;
    private Button mBtnMultiplication;
    private Button mBtnDivision;
    private TextView mTvResult;
    private TextView mTvCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculator);
        innitViews();
        initListener();
    }

    private void innitViews() {
        mEdtNumberA = findViewById(R.id.edtNumberA);
        mEdtNumberB = findViewById(R.id.edtNumberB);
        mBtnSum = findViewById(R.id.btnSum);
        mBtnSubtraction = findViewById(R.id.btnSubtraction);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
        mTvResult = findViewById(R.id.tvResult);
        mTvCalculation = findViewById(R.id.tvCalculation);
    }

    private void initListener() {
        mBtnSum.setOnClickListener(this);
        mBtnSubtraction.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (isEmpty()) {
            switch (view.getId()) {
                case R.id.btnSum:
                    sum(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()));
                    break;
                case R.id.btnSubtraction:
                    subtraction(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()));
                    break;
                case R.id.btnMultiplication:
                    multiplication(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()));

                    break;
                case R.id.btnDivision:
                    division(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()));
                    if (TextUtils.equals(mEdtNumberB.getText().toString(), getString(R.string.number_0))) {
                        showError(getString(R.string.dialog_message_num0));
                        mEdtNumberB.getText().clear();
                    }
                    break;
            }
        }
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(mEdtNumberA.getText()) || TextUtils.isEmpty(mEdtNumberB.getText())) {
            showError(getString(R.string.dialog_message));
            return false;
        }
        return true;
    }

    private void showError(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.dialog_title);
        alertDialogBuilder.setMessage(message);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        mTvResult.setText(R.string.tv_result);
    }

    void sum(float numA, float numB) {
        float result;
        result = numA + numB;
        mTvCalculation.setText(getString(R.string.edt_sum));
        mTvResult.setText(String.valueOf(result));
    }

    void subtraction(float numA, float numB) {
        float result;
        result = numA - numB;
        mTvCalculation.setText(getString(R.string.edt_sub));
        mTvResult.setText(String.valueOf(result));
    }

    void multiplication(float numA, float numB) {
        float result;
        result = numA * numB;
        mTvCalculation.setText(getString(R.string.edt_multi));
        mTvResult.setText(String.valueOf(result));
    }

    void division(float numA, float numB) {
        float result;
        result = numA / numB;
        mTvCalculation.setText(getString(R.string.edt_divi));
        mTvResult.setText(String.valueOf(result));
    }
}
