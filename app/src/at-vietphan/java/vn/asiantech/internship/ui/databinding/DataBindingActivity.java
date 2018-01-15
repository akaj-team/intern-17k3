package vn.asiantech.internship.ui.databinding;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.asiantech.internship.R;
import vn.asiantech.internship.utils.CustomEditText;
import vn.asiantech.internship.utils.MyEditText;

public class DataBindingActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private EditText mEdtBirthDate;
    private CustomEditText mEdtName;
    private MyEditText mEdtEmail;
    private MyEditText mEdtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);
        initViews();
        initListeners();
        CircleImageView circleImageView = findViewById(R.id.imgAvatar);

        Glide.with(this)
                .load("http://2sao.vietnamnetjsc.vn/images/2017/03/24/20/38/Anh-doi-thuong-xinh-dep-cua-hot-girl-bong-chuyen-tuyen-Viet-Nam-3.jpg")
                .into(circleImageView);
    }

    private void initViews() {
        mEdtBirthDate = findViewById(R.id.edtBirthDate);
        mEdtName = findViewById(R.id.edtName);
        mEdtEmail = findViewById(R.id.edtEmail);
        mEdtPhone = findViewById(R.id.edtPhone);
    }

    private void initListeners() {
        mEdtBirthDate.setOnClickListener(this);
        mEdtName.setOnTouchListener(this);
//        mEdtEmail.setOnTouchListener(this);
//        mEdtPhone.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.edtBirthDate) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mEdtBirthDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (view.getId() == R.id.btnSubmit) {
            Toast.makeText(this, getResources().getString(R.string.submit), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int DRAWABLE_RIGHT = 2;
        if (view.getId() == R.id.edtName) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int leftEdgeOfRightDrawable = mEdtName.getRight() - mEdtName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width();
                if (event.getRawX() >= leftEdgeOfRightDrawable) {
                    mEdtName.setText("");
                    return true;
                }
            }
        }
        view.performClick();
        return false;
    }
}
