package vn.asiantech.internship.ui.caculatorview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import vn.asiantech.internship.R;

public class CalculatorViewActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtNumberA;
    private EditText mEdtNumberB;
    private TextView mTvOperator;
    private TextView mTvResult;
    private Button mBtnAdd;
    private Button mBtnSub;
    private Button mBtnMulti;
    private Button mBtnDiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_view);
        initView();
        initListener();
        checkInputData();
    }

    @Override
    public void onClick(View view) {
        double result;
        String oper;
        double numA;
        double numB;
        numA = Double.parseDouble(mEdtNumberA.getText().toString());
        numB = Double.parseDouble(mEdtNumberB.getText().toString());

        switch (view.getId()) {
            case R.id.btnAdd:
                oper = this.getString(R.string.operator_add);
                result = numA + numB;
                convertFloatToInt(result, oper);
                break;
            case R.id.btnSub:
                oper = this.getString(R.string.operator_sub);
                result = numA - numB;
                convertFloatToInt(result, oper);
                break;
            case R.id.btnMulti:
                oper = this.getString(R.string.operator_multi);
                result = numA * numB;
                convertFloatToInt(result, oper);
                break;
            case R.id.btnDiv:
                oper = this.getString(R.string.operator_divide);
                if (numB != 0) {
                    result = numA / numB;
                    convertFloatToInt(result, oper);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = context.getString(R.string.error_divide_zero);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    mTvResult.setText("");
                }
                break;
            default:
                break;
        }

    }

    private void checkInputData() {
        mEdtNumberA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkClickButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op
            }
        });

        mEdtNumberB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkClickButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No-op

            }
        });
    }

    private void initView() {
        mEdtNumberA = findViewById(R.id.edtInputA);
        mEdtNumberB = findViewById(R.id.edtInputB);
        mTvOperator = findViewById(R.id.tvOperator);
        mTvResult = findViewById(R.id.tvResult);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnSub = findViewById(R.id.btnSub);
        mBtnMulti = findViewById(R.id.btnMulti);
        mBtnDiv = findViewById(R.id.btnDiv);
    }

    private void initListener() {
        mBtnAdd.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMulti.setOnClickListener(this);
        mBtnDiv.setOnClickListener(this);
    }

    private void convertFloatToInt(double result, String oper) {
        int intResult = (int) result;
        if (result == intResult) {
            mTvResult.setText(String.valueOf(intResult));
        } else {
            mTvResult.setText(String.valueOf(result));
        }
        mTvOperator.setText(oper);
    }

    private void checkClickButton() {
        if (!TextUtils.isEmpty(mEdtNumberA.getText().toString()) && !TextUtils.isEmpty(mEdtNumberB.getText().toString())) {
            mBtnAdd.setEnabled(true);
            mBtnSub.setEnabled(true);
            mBtnMulti.setEnabled(true);
            mBtnDiv.setEnabled(true);
        } else {
            mBtnAdd.setEnabled(false);
            mBtnSub.setEnabled(false);
            mBtnMulti.setEnabled(false);
            mBtnDiv.setEnabled(false);
            mTvOperator.setText("");
            mTvResult.setText("");
        }
    }

}
