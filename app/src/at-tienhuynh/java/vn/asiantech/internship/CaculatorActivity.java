package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private Boolean mIsEmty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculator_);
        innitsViewandClick();
    }

    private void innitsViewandClick() {
        mEdtNumberA = findViewById(R.id.edtNumberA);
        mEdtNumberB = findViewById(R.id.edtNumberB);
        mBtnSum = findViewById(R.id.btnSum);
        mBtnSubtraction = findViewById(R.id.btnSubtraction);
        mBtnMultiplication = findViewById(R.id.btnMultiplication);
        mBtnDivision = findViewById(R.id.btnDivision);
        mTvResult = findViewById(R.id.tvResult);
        mTvCalculation = findViewById(R.id.tvCalculation);
        mCalculation = new Calculation();
        mBtnSum.setOnClickListener(this);
        mBtnSubtraction.setOnClickListener(this);
        mBtnMultiplication.setOnClickListener(this);
        mBtnDivision.setOnClickListener(this);
    }

    @Override
    // Onclick Button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSum:
                getCaculator(mBtnSum);
                checkEmty();
                if (mIsEmty) {
                    mCalculation.sum(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                }
                break;
            case R.id.btnSubtraction:
                getCaculator(mBtnSubtraction);
                checkEmty();
                if (mIsEmty) {
                    mCalculation.subTraction(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                }
                break;
            case R.id.btnMultiplication:
                getCaculator(mBtnMultiplication);
                checkEmty();
                if (mIsEmty) {
                    mCalculation.mulTiplication(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                }
                break;
            case R.id.btnDivision:
                getCaculator(mBtnDivision);
                checkEmty();
                if (mIsEmty) {
                    mCalculation.diVision(Float.parseFloat(mEdtNumberA.getText().toString()), Float.parseFloat(mEdtNumberB.getText().toString()), mTvResult);
                }
                break;
        }
    }

    private void getCaculator(Button btnCaculator) {
        mTvCalculation.setText(btnCaculator.getText().toString());
    }

    private void checkEmty() {
        if (mEdtNumberA.getText().toString().length() > 0 && mEdtNumberB.getText().toString().length() > 0) {
            mIsEmty = true;

        } else {
            mIsEmty = false;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.DialogTitle);
            alertDialogBuilder.setMessage(R.string.dialogMessage);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            mTvResult.setText(R.string.tvResult);
        }
    }
}
