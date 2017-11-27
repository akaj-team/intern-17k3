package vn.asiantech.internship;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends Activity {
    private EditText mEdtNumA;
    private EditText mEdtNumB;
    private Button mBtnCong;
    private Button mBtnTru;
    private Button mBtnNhan;
    private Button mBtnChia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mBtnCong = findViewById(R.id.btnCong);
        mBtnTru = findViewById(R.id.btnCong);
        mB = findViewById(R.id.btnCong);
        mBtnCong = findViewById(R.id.btnCong);
    }

}
