package vn.asiantech.internship.ui.data_binding;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import vn.asiantech.internship.R;
import vn.asiantech.internship.databinding.ActivityEditProfileBinding;
import vn.asiantech.internship.model.User;

/**
 * Created by anh.quach on 1/10/18.
 * Edit Profile Activity
 */
public class EditProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnTouchListener {
    private EditText mEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        User user = new User();
        binding.setUser(user);
        Spinner mSpinner = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
        mEditText = findViewById(R.id.edtDatePicker);
        mEditText.setOnTouchListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //No-op
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() >= (mEditText.getRight() - mEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                mEditText.setText(getResources().getString(R.string.edittext_birthday, day, month + 1, year));
                            }
                        }, year, month, day);
                datePickerDialog.show();
                return true;
            }
        }
        return false;
    }
}
