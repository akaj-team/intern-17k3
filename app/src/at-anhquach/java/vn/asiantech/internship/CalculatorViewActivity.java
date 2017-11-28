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
    private TextView mTvResutl;
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
        init();
        onClickListener();
    }

    @Override
    public void onClick(View view) {
        float numA;
        float numB;

        if (TextUtils.isEmpty(mEdtNumberA.getText().toString())
                || TextUtils.isEmpty(mEdtInputB.getText().toString())) {
            return;
        }

        numA = Float.parseFloat(mEdtNumberA.getText().toString());
        numB = Float.parseFloat(mEdtInputB.getText().toString());

        switch (view.getId()) {
            case R.id.btnAdd:
                mOper = this.getString(R.string.Add);
                mResult = numA + numB;
                convertFloattoInt();
                break;
            case R.id.btnSub:
                mOper = this.getString(R.string.Sub);
                mResult = numA - numB;
                convertFloattoInt();
                break;
            case R.id.btnMulti:
                mOper = this.getString(R.string.Multi);
                mResult = numA * numB;
                convertFloattoInt();
                break;
            case R.id.btnDiv:
                mOper = this.getString(R.string.Divide);
                if (numB != 0) {
                    mResult = numA / numB;
                    convertFloattoInt();
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = context.getString(R.string.NoteDivideZero);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    mTvResutl.setText("");
                }
                break;
            default:
                break;
        }

    }

    private void init() {
        mEdtNumberA = findViewById(R.id.edtInputA);
        mEdtInputB = findViewById(R.id.edtInputB);
        mTvOperator = findViewById(R.id.tvOperator);
        mTvResutl = findViewById(R.id.tvResutl);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnSub = findViewById(R.id.btnSub);
        mBtnMulti = findViewById(R.id.btnMulti);
        mBtnDiv = findViewById(R.id.btnDiv);
    }

    private void onClickListener() {
        mBtnAdd.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMulti.setOnClickListener(this);
        mBtnDiv.setOnClickListener(this);
    }

    private void convertFloattoInt() {
        int intResult = (int) mResult;
        if (mResult == intResult) {
            mTvResutl.setText(String.valueOf(intResult));
        } else {
            mTvResutl.setText(String.valueOf(mResult));
        }
        mTvOperator.setText(mOper);
    }
}
