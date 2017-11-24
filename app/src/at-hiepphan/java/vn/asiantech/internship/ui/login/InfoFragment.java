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
    private EditText mEdtMobile;
    private EditText mEdtName;
    private EditText mEdtEmail;
    private CheckBox mChkBox;
    private ImageView mImgNext;

    public InfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mEdtMobile = view.findViewById(R.id.txtMobile);
        mEdtName = view.findViewById(R.id.txtName);
        mEdtEmail = view.findViewById(R.id.txtEmail);
        mChkBox = view.findViewById(R.id.chk);
        mImgNext = view.findViewById(R.id.imgNext);

        mChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (mEdtMobile.getText().length() != 0 && mEdtName.getText().length() != 0 && mEdtEmail.getText().length() != 0) {
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
