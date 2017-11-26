package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import vn.asiantech.internship.R;

public class InfoFragment extends Fragment {
    private EditText mEdtPhoneNumber;
    private EditText mEdtUserName;
    private EditText mEdtEmail;
    private CheckBox mChkAcceptTerm;
    private ImageView mImgNext;

    public InfoFragment() {
        // No-op
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mEdtPhoneNumber = view.findViewById(R.id.edtPhoneNumber);
        mEdtUserName = view.findViewById(R.id.edtUserName);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mChkAcceptTerm = view.findViewById(R.id.chkAcceptTerm);
        mImgNext = view.findViewById(R.id.imgNext);

        mChkAcceptTerm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (mEdtPhoneNumber.getText().length() != 0 && mEdtUserName.getText().length() != 0 && mEdtEmail.getText().length() != 0) {
                        mImgNext.setSelected(true);
                    }
                } else {
                    mImgNext.setSelected(false);
                }
            }
        });
        return view;
    }
}
