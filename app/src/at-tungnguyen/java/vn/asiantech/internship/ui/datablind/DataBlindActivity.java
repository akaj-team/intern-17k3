package vn.asiantech.internship.ui.datablind;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import vn.asiantech.internship.R;

public class DataBlindActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtBirthDate;
    private EditText mEdtNameProfile;
    private EditText mEdtEmailProfile;
    private Spinner mSpnGender;
    private DatePickerDialog mDatePickerDialog;
    private String mDate;
    private Calendar mCalender;
    private TextView mTvSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_blind);
        initViews();
        initListener();
    }


    private void initViews() {
        mEdtBirthDate = findViewById(R.id.edtBirthDate);
        mEdtNameProfile = findViewById(R.id.edtNameProfile);
        mEdtEmailProfile = findViewById(R.id.edtEmailProfile);
        mSpnGender = findViewById(R.id.spnGender);
        mTvSubmit = findViewById(R.id.tvSubmit);
    }

    private void initListener() {
        mTvSubmit.setOnClickListener(this);
        mEdtBirthDate.setOnClickListener(this);
        mEdtNameProfile.setOnClickListener(this);
        mEdtEmailProfile.setOnClickListener(this);
    }

    private void getBirthDate() {
        mCalender = Calendar.getInstance();
        int ngay = mCalender.get(Calendar.DATE);
        int thang = mCalender.get(Calendar.MONTH);
        int nam = mCalender.get(Calendar.YEAR);
        mDate = ngay + thang + nam + "";

        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                mCalender.set(i, i1, i2);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                mEdtBirthDate.setText(simpleDateFormat.format(mCalender.getTime()));
            }
        }, nam, thang, ngay);
        mDatePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtBirthDate:
                getBirthDate();
                break;
            case R.id.tvSubmit:
                Intent i = new Intent(DataBlindActivity.this,DisplayDataBlindActivity.class);
                startActivity(i);
                break;
        }
    }
}
