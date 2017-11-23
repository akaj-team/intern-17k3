package vn.asiantech.internship;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by vietphan on 23/11/2017.
 */

public class LoginFragment extends Fragment {
    private Button btnSubmit;
    private CheckBox chkTermsAccept;
    private EditText edtPhone, edtFullName, edtMail;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        chkTermsAccept = (CheckBox) view.findViewById(R.id.chkTermsAccept);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtFullName = view.findViewById(R.id.edtFullName);
        edtMail = view.findViewById(R.id.edtEmail);
        btnSubmit.setEnabled(false);
        check();
        return view;
    }

    private void check() {

        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSubmit.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!"".equals(edtPhone)) {
                    btnSubmit.setEnabled(true);
                }
            }
        });

        edtFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSubmit.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!"".equals(edtFullName)) {
                    btnSubmit.setEnabled(true);
                }
            }
        });
        edtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSubmit.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!"".equals(edtMail)) {
                    btnSubmit.setEnabled(true);
                }

            }
        });
    }


    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chkTermsAccept.setChecked(true);
        chkTermsAccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("VVVV", "onCheckedChanged: " + b);
            }
        });
        if (!"".equals(edtPhone) && !"".equals(edtFullName) && !"".equals(edtmail) && chkTermsAccept.isChecked() == true) {
            btnSubmit.setSelected(true);
            Log.d("submit", "ok: ");
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSubmit.setSelected(!btnSubmit.isSelected());
            }
        });
    }*/
}
