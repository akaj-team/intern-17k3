package vn.asiantech.internship.ui.datablind;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityDataBlindBinding;

public class DataBlindActivity extends AppCompatActivity {
    private EditText mEdtBirthDate;
    private DatePickerDialog mDatePickerDialog;
    private String mDate;
    private Calendar mCalender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBlindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_blind);
        User user = new User();
        user.setmEmail("Abc");
        binding.setUser(user);
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
}
