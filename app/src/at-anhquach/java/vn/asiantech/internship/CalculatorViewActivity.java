package vn.asiantech.internship;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorViewActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtNumberA;
    private EditText mEdtInputB;
    private TextView mTvOperator;
    private TextView mTvResult;
    private Button mBtnAdd;
    private Button mBtnSub;
    private Button mBtnMulti;
    private Button mBtnDiv;
    private float mResult = 0;
    private String mOper = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_view);
        initView();
        initListener();
    }

    @Override
    public void onClick(View view) {

        if (TextUtils.isEmpty(mEdtNumberA.getText().toString())
                || TextUtils.isEmpty(mEdtInputB.getText().toString())) {
            return;
        }
        float numA;
        float numB;
        numA = Float.parseFloat(mEdtNumberA.getText().toString());
        numB = Float.parseFloat(mEdtInputB.getText().toString());

        switch (view.getId()) {
            case R.id.btnAdd:
                mOper = this.getString(R.string.operator_add);
                mResult = numA + numB;
                convertFloattoInt(mResult, mOper);
                break;
            case R.id.btnSub:
                mOper = this.getString(R.string.operator_sub);
                mResult = numA - numB;
                convertFloattoInt(mResult, mOper);
                break;
            case R.id.btnMulti:
                mOper = this.getString(R.string.operator_multi);
                mResult = numA * numB;
                convertFloattoInt(mResult, mOper);
                break;
            case R.id.btnDiv:
                mOper = this.getString(R.string.operator_divide);
                if (numB != 0) {
                    mResult = numA / numB;
                    convertFloattoInt(mResult, mOper);
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

    private void initView() {
        mEdtNumberA = findViewById(R.id.edtInputA);
        mEdtInputB = findViewById(R.id.edtInputB);
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

    private void convertFloattoInt(float result, String oper) {
        int intResult = (int) result;
        if (result == intResult) {
            mTvResult.setText(String.valueOf(intResult));
        } else {
            mTvResult.setText(String.valueOf(result));
        }
        mTvOperator.setText(oper);
    }
}
