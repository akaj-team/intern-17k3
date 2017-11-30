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

public class CaculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtNumberA;
    private EditText mEdtNumberB;
    private Button mBtnSum;
    private Button mBtnSubtraction;
    private Button mBtnMultiplication;
    private Button mBtnDivision;
    private TextView mTvResult;
    private TextView mTvCalculation;
    private Calculation mCalculation;
    private boolean mIsEmty;

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
        mCalculation = new Calculation();
    }

    private void initListener() {
        mBtnSum.setOnClickListener(this);
        mBtnSubtraction.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Onclick Button
        switch (view.getId()) {
            case R.id.btnSum:
                getCalculator(mBtnSum);
                checkEmpty();
                if (mIsEmty) {
                    mCalculation.sum(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                }
                break;
            case R.id.btnSubtraction:
                getCalculator(mBtnSubtraction);
                checkEmpty();
                if (mIsEmty) {
                    mCalculation.subTraction(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                }
                break;
            case R.id.btnMultiplication:
                getCalculator(mBtnMultiplication);
                checkEmpty();
                if (mIsEmty) {
                    mCalculation.mulTiplication(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                }
                break;
            case R.id.btnDivision:
                getCalculator(mBtnDivision);
                checkEmpty();
                if (mIsEmty) {
                    mCalculation.diVision(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                    if (TextUtils.equals(mEdtNumberB.getText().toString(), getString(R.string.number_0))) {
                        showError(getString(R.string.dialog_message_num0));
                        mEdtNumberB.getText().clear();
                    }
                    break;
                }
        }
    }

    private void getCalculator(Button btnCalculator) {
        mTvCalculation.setText(btnCalculator.getText().toString());
    }

    private void checkEmpty() {
        mIsEmty = !TextUtils.isEmpty(mEdtNumberA.getText()) && !TextUtils.isEmpty(mEdtNumberB.getText());
        if (!mIsEmty) {
            showError(getString(R.string.dialog_message));
        }
    }

    private void showError(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.dialog_title);
        alertDialogBuilder.setMessage(message);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        mTvResult.setText(R.string.tv_result);
    }
}
