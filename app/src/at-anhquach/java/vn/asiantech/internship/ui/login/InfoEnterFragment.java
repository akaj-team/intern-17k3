package vn.asiantech.internship.ui.login;
/**
 * Created by anh.quach on 11/23/17.
 */

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
    ImageView imgNext;
    EditText edtNumber, edtEmail, edtFullName;
    CheckBox chkTerm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_enter, container, false);

        imgNext = view.findViewById(R.id.imgNext);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtFullName = view.findViewById(R.id.edtFullname);
        edtNumber = view.findViewById(R.id.edtEnNum);
        chkTerm = view.findViewById(R.id.chkTermAccept);

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputInfo();
            }
        });

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputInfo();
            }
        });

        edtFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputInfo();
            }
        });

        chkTerm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!TextUtils.isEmpty(edtNumber.getText()) && !TextUtils.isEmpty(edtFullName.getText()) &&
                            !TextUtils.isEmpty(edtEmail.getText())) {
                        imgNext.setSelected(true);
                    }
                } else {
                    imgNext.setSelected(false);
                }
            }
        });

        return view;
    }

    private void checkInputInfo(){
        if (!TextUtils.isEmpty(edtNumber.getText()) && !TextUtils.isEmpty(edtFullName.getText()) &&
                !TextUtils.isEmpty(edtEmail.getText()) && chkTerm.isChecked()) {
            imgNext.setSelected(true);
        } else {
            imgNext.setSelected(false);
        }
    }

}


