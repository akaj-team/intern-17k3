package vn.asiantech.internship.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
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
    private TextView mTvTermService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        initViews(v);
        initListener();
        setColorTvTermService();
        return v;
    }

    private void initViews(View v) {
        mChkTermService = v.findViewById(R.id.chkTermServices);
        mImgNext = v.findViewById(R.id.imgNext);
        mEdtEmail = v.findViewById(R.id.edtEmail);
        mEdtFullName = v.findViewById(R.id.edtFullName);
        mEdtPhone = v.findViewById(R.id.edtPhone);
        mTvTermService = v.findViewById(R.id.tvTermService);
    }

    private void initListener() {
        mEdtEmail.addTextChangedListener(this);
        mEdtPhone.addTextChangedListener(this);
        mEdtFullName.addTextChangedListener(this);
        mChkTermService.setOnClickListener(this);
    }

    private void setColorTvTermService() {
        SpannableString ss = new SpannableString(getString(R.string.tv_term_services));
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue_dark)), 32, 53, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvTermService.setText(ss);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibilityBackButton(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.chkTermServices) {
            mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermService));
        }
    }

    private boolean checkSignUp(TextView tv1, TextView tv2, TextView tv3, CheckBox chk) {
        return (!(tv1.getText().toString().trim().isEmpty()) && !(tv2.getText().toString().trim().isEmpty()) && !(tv3.getText().toString().trim().isEmpty()) && chk.isChecked());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermService));
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
