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
    EditText mEdtPhone;
    EditText mEdtFullName;
    EditText mEdtEmail;
    CheckBox mChkConfirm;
    Button mBtnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_infor, container, false);
        mEdtPhone = view.findViewById(R.id.edtEmail);
        mEdtFullName = view.findViewById(R.id.edtFullname);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mChkConfirm = view.findViewById(R.id.chkConfirm);
        mBtnNext = view.findViewById(R.id.btnNext);
        checkEdt(mEdtEmail);
        checkEdt(mEdtFullName);
        checkEdt(mEdtPhone);
        checkChk(mChkConfirm);
        return view;
    }

    private void checkEdt(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
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

    private void checkChk(CheckBox checkBox){
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
