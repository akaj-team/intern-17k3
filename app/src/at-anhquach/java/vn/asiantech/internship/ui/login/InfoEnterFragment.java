package vn.asiantech.internship.ui.login;

/**
 * Created by anh.quach on 11/23/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import vn.asiantech.internship.R;


public class InfoEnterFragment extends Fragment {
    ImageButton imgBtnNext;
    EditText edtNumber, edtEmail, edtFullName;
    CheckBox chkTerm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info_enter, container, false);
        imgBtnNext = view.findViewById(R.id.imgBtnNext);
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
                if (edtNumber != null && edtFullName != null && edtEmail != null ){
                    imgBtnNext.setSelected(true);
                }
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
                if (edtNumber != null && edtFullName != null && edtEmail != null ){
                    imgBtnNext.setSelected(true);
                }
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
                if (edtNumber != null && edtFullName != null && edtEmail != null ){
                    imgBtnNext.setSelected(true);
                }
            }
        });

        return view;
    }


}
