package vn.asiantech.internship;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tungnguyen on 23/11/2017.
 */

public class SignUpFragment extends Fragment {
    private TextView mtvNumber;
    private Button mbtnSingUp;
    private EditText medtNumber;
    private EditText medtEmail;
    private EditText medtFullname;
    private CheckBox mchkTerms;
    private ImageView mimgNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singup, container, false);
        mtvNumber = view.findViewById(R.id.tvTerm);
        medtEmail = view.findViewById(R.id.edtEmail);
        medtNumber = view.findViewById(R.id.edtNumber);
        mchkTerms = view.findViewById(R.id.chkSignup);
        medtFullname = view.findViewById(R.id.edtFullname);
        mimgNext = view.findViewById(R.id.imgSignUp);
        Event();
        ((MainActivity) getActivity()).getBack().setVisibility(View.VISIBLE);
        return view;
    }

    private void Event() {
        mchkTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!TextUtils.isEmpty(medtNumber.getText()) && !TextUtils.isEmpty(medtEmail.getText()) && !TextUtils.isEmpty(medtFullname.getText())) {
                        mimgNext.setSelected(true);
                    }
                } else {
                    mimgNext.setSelected(false);
                }
            }
        });
        medtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                changeImage();
            }
        });
        medtFullname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                changeImage();

            }
        });
        medtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                changeImage();
            }
        });
    }

    private void changeImage() {
        if (!TextUtils.isEmpty(medtNumber.getText()) && !TextUtils.isEmpty(medtEmail.getText()) && !TextUtils.isEmpty(medtFullname.getText()) && mchkTerms.isChecked()) {
            mimgNext.setSelected(true);
        } else {
            mimgNext.setSelected(false);
        }
    }
}
