package vn.asiantech.internship;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        mBtnAdd = findViewById(R.id.btnPlus);
        mBtnSub = findViewById(R.id.btnMinus);
        mBtnMulti = findViewById(R.id.btnMulti);
        mBtnDiv = findViewById(R.id.btnDiv);

        mBtnAdd.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
        mBtnMulti.setOnClickListener(this);
        mBtnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
