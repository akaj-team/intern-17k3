package vn.asiantech.internship.databinding;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import vn.asiantech.internship.R;

/**
 * DataBinding Activity to Edit Profile
 */
public class EditInfoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final int DIALOG_PICKER_ID = 999;
    private Button mBtnHintNameInfo;
    private Button mBtnHintEmailInfo;
    private Button mBtnHintDateInfo;
    private Button mBtnHintPhoneInfo;
    private EditText mEdtNameInfo;
    private EditText mEdtEmailInfo;
    private EditText mEdtDateInfo;
    private EditText mEdtPhoneInfo;
    //Date Picker
    private int mYear, mMonth, mDay;
    private Spinner mSpnGender;
    // ActivityBinding
    private ActivityEditInfoBinding mActivityEditInfoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view Data Binding
        mActivityEditInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_info);
        mActivityEditInfoBinding.setHandler(new HandlerClick());
        // initViews
//        initViews();
        // init Listener
//        initListeners();
        //init DatePicker
//        initDatePicker();
        // setDefault spinner
//        mSpnGender.setSelection(user.getGender());
    }

    /**
     * Init Date Picker
     */
    private void initDatePicker() {
        Calendar mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        showDate(mYear, mMonth + 1, mDay);
    }

    /**
     * Call dialog date picker when click the button
     *
     * @param view view
     */
    public void setDate(View view) {
        showDialog(DIALOG_PICKER_ID);
    }

    /**
     * create Dialog Picker
     *
     * @param id id
     * @return null
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == DIALOG_PICKER_ID) {
            return new DatePickerDialog(this,
                    myDateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    /**
     * Date Picker listener when user choose day in this
     **/
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    /**
     * Show day after user choose in day picker
     *
     * @param year  year
     * @param month month
     * @param day   day
     */
    private void showDate(int year, int month, int day) {
        mEdtDateInfo.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(day));
    }

    /**
     * Init Listeners
     */
    private void initListeners() {
        mBtnHintNameInfo.setOnClickListener(this);
        mBtnHintEmailInfo.setOnClickListener(this);
        mBtnHintDateInfo.setOnClickListener(this);
        mBtnHintPhoneInfo.setOnClickListener(this);

        mSpnGender.setOnItemSelectedListener(this);
    }

    /**
     * Init Views
     */
    private void initViews() {
        mBtnHintNameInfo = findViewById(R.id.btnHintNameInfo);
        mBtnHintEmailInfo = findViewById(R.id.btnHintEmailInfo);
        mBtnHintDateInfo = findViewById(R.id.btnHintDateInfo);
        mBtnHintPhoneInfo = findViewById(R.id.btnHintPhoneInfo);

        mEdtNameInfo = findViewById(R.id.edtNameUpdate);
        mEdtEmailInfo = findViewById(R.id.edtEmailUpdate);
        mEdtDateInfo = findViewById(R.id.edtBirthday);
        mEdtPhoneInfo = findViewById(R.id.edtContactNumber);

        mSpnGender = findViewById(R.id.spnGender);
    }

    /**
     * Onclick button hint
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHintNameInfo:
                mEdtNameInfo.setText("");
                break;
            case R.id.btnHintEmailInfo:
                mEdtEmailInfo.setText("");
                break;
            case R.id.btnHintDateInfo:
                setDate(view);
                break;
            case R.id.btnHintPhoneInfo:
                mEdtPhoneInfo.setText("");
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "" + i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //No-opp
    }
}
