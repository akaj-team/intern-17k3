package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import vn.asiantech.internship.R;

public class InfoEnterFragment extends Fragment {
    private ImageView mimgNext;
    private EditText medtNumber;
    private EditText medtEmail;
    private EditText medtFullName;
    private CheckBox mchkTerm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_enter, container, false);

        mimgNext = view.findViewById(R.id.imgNext);
        medtEmail = view.findViewById(R.id.edtEmail);
        medtFullName = view.findViewById(R.id.edtFullName);
        medtNumber = view.findViewById(R.id.edtEnterPhoneNumber);
        mchkTerm = view.findViewById(R.id.chkTermAccept);

        medtEmail.addTextChangedListener(new TextWatcher() {
            //No-op
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //No-op
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputInfo();
            }
        });

        medtNumber.addTextChangedListener(new TextWatcher() {
            //No-op
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //No-op
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                checkInputInfo();
            }
        });

        medtFullName.addTextChangedListener(new TextWatcher() {
            //No-op
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //No-op
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                checkInputInfo();
            }
        });

        mchkTerm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!TextUtils.isEmpty(medtNumber.getText()) && !TextUtils.isEmpty(medtFullName.getText()) &&
                            !TextUtils.isEmpty(medtEmail.getText())) {
                        mimgNext.setSelected(true);
                    }
                } else {
                    mimgNext.setSelected(false);
                }
            }
        });

        return view;
    }

    private void checkInputInfo() {
        if (!TextUtils.isEmpty(medtNumber.getText()) && !TextUtils.isEmpty(medtFullName.getText()) &&
                !TextUtils.isEmpty(medtEmail.getText()) && mchkTerm.isChecked()) {
            mimgNext.setSelected(true);
        } else {
            mimgNext.setSelected(false);
        }
    }

}
