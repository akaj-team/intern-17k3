package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class FillOutFragment extends Fragment implements TextWatcher, View.OnClickListener {
    private CheckBox mChkTermAccept;
    private EditText mEdtEmail;
    private EditText mEdtFullName;
    private ImageView mImgNext;
    private EditText mEdtPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fill_out, container, false);

        // Item back
        mImgNext = v.findViewById(R.id.imgNext);

        // Checkbox
        mChkTermAccept = v.findViewById(R.id.chkSignUp);

        // Email
        mEdtEmail = v.findViewById(R.id.edtEmail);
        mEdtEmail.addTextChangedListener(this);

        // Phone
        mEdtPhone = v.findViewById(R.id.edtPhone);
        mEdtPhone.addTextChangedListener(this);

        // Full Name
        mEdtFullName = v.findViewById(R.id.edtFullName);
        mEdtFullName.addTextChangedListener(this);

        // Color TextView
        TextView tvLinkCheck = v.findViewById(R.id.tvLinkSp);
        String text = "<font color='black'>I have read and agree with me</font><font color='#34A4F1'><br>terms and conditions</font><font color='black'>.</font>";
        tvLinkCheck.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
        mChkTermAccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mChkTermAccept.setSelected(!mChkTermAccept.isSelected());
            }
        });

        mChkTermAccept.setOnClickListener(this);
        return v;
    }

    private boolean checkSignUp(EditText edtPhone, EditText edtEmail, EditText edtFullName, CheckBox chkCheck) {
        return (((edtPhone.getText().toString().trim().length() != 0) && (edtEmail.getText().toString().trim().length() != 0) && (edtFullName.getText().toString().trim().length() != 0)) && chkCheck.isChecked());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }

    @Override
    public void afterTextChanged(Editable s) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }

    @Override
    public void onClick(View v) {
        mImgNext.setSelected(checkSignUp(mEdtPhone, mEdtFullName, mEdtEmail, mChkTermAccept));
    }
}
