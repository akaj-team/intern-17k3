package vn.asiantech.internship.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class SingUpFragment extends Fragment implements View.OnClickListener, TextWatcher {
    private CheckBox mChkTermService;
    private ImageView mImgNext;
    private EditText mEdtPhone;
    private EditText mEdtEmail;
    private EditText mEdtFullName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        mChkTermService = v.findViewById(R.id.chkTermServices);
        mChkTermService.setOnClickListener(this);
        mImgNext = v.findViewById(R.id.imgNext);
        mEdtEmail = v.findViewById(R.id.edtEmail);
        mEdtFullName = v.findViewById(R.id.edtFullName);
        mEdtPhone = v.findViewById(R.id.edtPhone);
        // Handle edit text listener
        mEdtEmail.addTextChangedListener(this);
        mEdtPhone.addTextChangedListener(this);
        mEdtFullName.addTextChangedListener(this);
        // Set text with two color
        TextView tvTermService = v.findViewById(R.id.tvTermService);
        String text = "I have read and agree with the <font color='#00bfff'> <br/> term and conditions</font>. ";
        tvTermService.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
        return v;
    }

    @Override
    public void onClick(View v) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermService));
    }

    private boolean checkSignUp(TextView tv1, TextView tv2, TextView tv3, CheckBox chk) {
        return (!(tv1.getText().toString().trim().isEmpty()) && !(tv2.getText().toString().trim().isEmpty()) && !(tv3.getText().toString().trim().isEmpty()) && chk.isChecked());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermService));
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermService));
    }

    @Override
    public void afterTextChanged(Editable s) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermService));
    }
}
