package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
/**
 * A simple {@link Fragment} subclass.
 */
public class InforFragment extends Fragment {
    private EditText mEdtPhone;
    private EditText mEdtFullName;
    private EditText mEdtEmail;
    private CheckBox mChkConfirm;
    private Button mBtnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_infor, container, false);
        mEdtPhone = view.findViewById(R.id.edtPhoneNumber);
        mEdtFullName = view.findViewById(R.id.edtFullname);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mChkConfirm = view.findViewById(R.id.chkConfirm);
        mBtnNext = view.findViewById(R.id.btnNext);
        checkInputData(mEdtEmail);
        checkInputData(mEdtFullName);
        checkInputData(mEdtPhone);
        checkStatusCheckBox(mChkConfirm);
        return view;
    }

    private void checkInputData(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No-op
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mChkConfirm.isChecked() && !"".equals(mEdtPhone.getText().toString()) && !"".equals(mEdtEmail.getText().toString()) && !"".equals(mEdtFullName.getText().toString())){
                    mBtnNext.setSelected(true);
                }
                else{
                    mBtnNext.setSelected(false);
                }
            }
        });
    }

    private void checkStatusCheckBox(CheckBox checkBox){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(mChkConfirm.isChecked() && !"".equals(mEdtPhone.getText().toString()) && !"".equals(mEdtEmail.getText().toString()) && !"".equals(mEdtFullName.getText().toString())){
                    mBtnNext.setSelected(true);
                }
                else{
                    mBtnNext.setSelected(false);
                }
            }
        });
    }
}
