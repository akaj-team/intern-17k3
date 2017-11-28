package vn.asiantech.internship;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.asiantech.internship.R;

public class CalculatorViewActivity extends Activity implements View.OnClickListener {
    private EditText mEdtInputA;
    private EditText mEdtInputB;
    private TextView mTvOperator;
    private TextView mTvResutl;
    private Button mBtnAdd;
    private Button mBtnSub;
    private Button mBtnMulti;
    private Button mBtnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_view);
        mEdtInputA = findViewById(R.id.edtInputA);
        mEdtInputB = findViewById(R.id.edtInputB);
        mTvOperator = findViewById(R.id.tvOperator);
        mTvResutl = findViewById(R.id.tvResutl);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnSub = findViewById(R.id.btnSub);
        mBtnMulti = findViewById(R.id.btnMulti);
        mBtnDiv = findViewById(R.id.btnDiv);

        mBtnAdd.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMulti.setOnClickListener(this);
        mBtnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        float numA=0;
        float numB=0;
        float result=0;
        String oper="";

        if (TextUtils.isEmpty(mEdtInputA.getText().toString())
                || TextUtils.isEmpty(mEdtInputB.getText().toString())) {
            return;
        }

        numA = Float.parseFloat(mEdtInputA.getText().toString());
        numB = Float.parseFloat(mEdtInputB.getText().toString());

        switch (view.getId()) {
            case R.id.btnAdd:
                oper = "+";
                result = numA + numB;
                break;
            case R.id.btnSub:
                oper = "-";
                result = numA - numB;
                break;
            case R.id.btnMulti:
                oper = "*";
                result = numA * numB;
                break;
            case R.id.btnDiv:
                oper = "/";
                if (numB!=0){
                    result = numA / numB;
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Cant divide for 0";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                break;
            default:
                break;
        }
        mTvResutl.setText(""+result);
        mTvOperator.setText(oper);
    }
}
