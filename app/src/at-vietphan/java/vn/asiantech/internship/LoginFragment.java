package vn.asiantech.internship;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginFragment extends Fragment {
    private Button btnSubmit;
    private EditText edtPhone, edtFullName, edtMail;
    protected CheckBox chkTermsAccept;

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
        chkTermsAccept = view.findViewById(R.id.chkTermsAccept);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtFullName = view.findViewById(R.id.edtFullName);
        edtMail = view.findViewById(R.id.edtEmail);
        btnSubmit.setEnabled(false);
        check();
        return view;
    }

    private void check() {
        checkEdt(edtPhone);
        checkEdt(edtFullName);
        checkEdt(edtMail);
        checkChk(chkTermsAccept);
    }

    public void checkEdt(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!((edtPhone.getText().toString()) == "") && !"".equals(edtFullName.getText().toString()) && !"".equals(edtMail.getText().toString()) && chkTermsAccept.isChecked() == true) {
                    btnSubmit.setEnabled(true);
                    Log.d("submit", "ok: ");
                } else {
                    btnSubmit.setEnabled(false);
                }
            }
        });
    }

    public void checkChk(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!"".equals(edtPhone.getText().toString()) && !"".equals(edtFullName.getText().toString()) && !"".equals(edtMail.getText().toString()) && b) {
                    btnSubmit.setEnabled(true);
                    Log.d("submit", "ok: ");
                } else {
                    btnSubmit.setEnabled(false);
                }
            }
        });
    }
}