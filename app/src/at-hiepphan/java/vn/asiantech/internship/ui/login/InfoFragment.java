package vn.asiantech.internship.ui.login;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import vn.asiantech.internship.R;

public class InfoFragment extends Fragment {
    private EditText mEdtMobile, mEdtName, mEdtEmail;
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

        mEdtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeCheckBoxState();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEdtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeCheckBoxState();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEdtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeCheckBoxState();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mImgNext.setImageResource(R.drawable.ic_enable);
                } else {
                    mImgNext.setImageResource(R.drawable.ic_disable);
                }
            }
        });

        return view;
    }

    private void changeCheckBoxState() {
        if (mEdtMobile.getText().length() != 0 && mEdtName.getText().length() != 0 && mEdtEmail.getText().length() != 0) {
            mChkBox.setEnabled(true);
        } else {
            mChkBox.setChecked(false);
            mChkBox.setEnabled(false);
        }
    }
}
