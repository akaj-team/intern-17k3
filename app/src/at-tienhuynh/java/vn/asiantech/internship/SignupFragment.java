package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by jackty on 23/11/2017.
 */

public class SignupFragment extends Fragment implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    private String[] mCountryNames = {"VietNam", "Australia"};
    private int mFlags[] = {R.drawable.ic_vietnam_flag, R.drawable.ic_uc_flag};
    private Spinner mSpnCountry;
    private View mView;
    private EditText mEdtPhone;
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

        return mView;
    }

    private void inits() {
        mEdtPhone = mView.findViewById(R.id.edtPhoneNum);
        mSpnCountry = mView.findViewById(R.id.spnCountry);
        mImgNext = mView.findViewById(R.id.imgNext);
        chkAgree = mView.findViewById(R.id.chkCheck);
        CustomSpnPhone customSpnPhone = new CustomSpnPhone(getContext(), mFlags, mCountryNames);
        mSpnCountry.setAdapter(customSpnPhone);
        mSpnCountry.setOnItemSelectedListener(this);
        chkAgree.setOnCheckedChangeListener(this);
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
                    mImgNext.setImageResource(R.drawable.ic_btn_next_select);
                    break;

            }
        } else {
            switch (id) {
                case R.id.chkCheck:
                    mImgNext.setImageResource(R.drawable.ic_btn_next);
                    break;
            }
        }
    }
}
