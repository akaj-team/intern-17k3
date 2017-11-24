package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by jackty on 23/11/2017.
 */

public class SignupFragment extends Fragment implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    private String[] mCountryNames = {"VietNam", "Australia"};
    private int mFlags[] = {R.drawable.ic_vietnam_flag, R.drawable.ic_ustrailia_flag};
    private Spinner mSpnCountry;
    private View mView;
    private EditText mEdtPhone, mEdtName, mEdtMail;
    private ImageView mImgNext;
    private CheckBox chkAgree;

    public static SignupFragment newInstance() {
        Bundle args = new Bundle();
        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_signup, container, false);
        // Inflate the layout for this fragment
        inits();
        validate();
        return mView;
    }

    private void inits() {
        mEdtPhone = mView.findViewById(R.id.edtPhoneNum);
        mEdtName = mView.findViewById(R.id.edtFullName);
        mEdtMail = mView.findViewById(R.id.edtEmail);
        mSpnCountry = mView.findViewById(R.id.spnCountry);
        mImgNext = mView.findViewById(R.id.imgNext);
        chkAgree = mView.findViewById(R.id.chkCheck);
        CustomSpnPhone customSpnPhone = new CustomSpnPhone(getContext(), mFlags, mCountryNames);
        mSpnCountry.setAdapter(customSpnPhone);
        mSpnCountry.setOnItemSelectedListener(this);
        chkAgree.setOnCheckedChangeListener(this);
        ((LoginActivity) getActivity()).getImgBack().setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (mSpnCountry.getSelectedItemPosition() == 0) {
            mEdtPhone.setText("+84");
        }
        if (mSpnCountry.getSelectedItemPosition() == 1) {
            mEdtPhone.setText("+64");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id = compoundButton.getId();
        if (b) {
            switch (id) {
                case R.id.chkCheck:
                    if (!TextUtils.isEmpty(mEdtPhone.getText()) && !TextUtils.isEmpty(mEdtName.getText()) && !TextUtils.isEmpty(mEdtMail.getText())) {
                        mImgNext.setSelected(true);
                    }
                    break;

            }
        } else {
            switch (id) {
                case R.id.chkCheck:
                    mImgNext.setSelected(false);
                    break;
            }
        }
    }

    private void validate() {
        mEdtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkemtytext();
            }
        });
        mEdtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkemtytext();
            }
        });
        mEdtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkemtytext();
            }
        });
    }

    private void checkemtytext() {
        if (!TextUtils.isEmpty(mEdtPhone.getText()) && !TextUtils.isEmpty(mEdtName.getText()) && !TextUtils.isEmpty(mEdtMail.getText()) && chkAgree.isChecked()) {
            mImgNext.setSelected(true);
        } else {
            mImgNext.setSelected(false);
        }
    }
}
