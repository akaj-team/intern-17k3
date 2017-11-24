package vn.asiantech.internship.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class SingUpFragment extends Fragment implements View.OnClickListener {
    private CheckBox mChkTermService;
    private ImageView mImgNext;
    private EditText mEdtPhone, mEdtEmail, mEdtFullName;

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
        TextView tvTermService = v.findViewById(R.id.tvTermService);
        //Set text with two color
        String text = "I have read and agree with the <font color='#00bfff'> <br/> term and conditions</font>. ";
        tvTermService.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (mChkTermService.isChecked() && !((mEdtPhone.getText().toString()).isEmpty()) && !((mEdtFullName.getText().toString()).isEmpty()) && !((mEdtEmail.getText().toString()).isEmpty())) {
            mImgNext.setSelected(true);
        } else {
            mImgNext.setSelected(false);
        }
    }
}
