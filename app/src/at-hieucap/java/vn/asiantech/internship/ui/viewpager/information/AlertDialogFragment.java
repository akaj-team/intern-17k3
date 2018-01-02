package vn.asiantech.internship.ui.viewpager.information;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;


/**
 * Create Alert Dialog Fragment
 */
public class AlertDialogFragment extends DialogFragment implements View.OnClickListener {
    private EditText mEdtName;
    private EditText mEdtPhone;
    private EditText mEdtStatus;

    public static AlertDialogFragment newInstance(String name, String number, String status) {
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("number", number);
        args.putString("status", status);
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_information, container,
                false);
        getDialog().setTitle("Information");
        Button btnSubmit = rootView.findViewById(R.id.btnSubmit);
        Button btnCancel = rootView.findViewById(R.id.btnCancel);
        mEdtName = rootView.findViewById(R.id.edtName);
        mEdtPhone = rootView.findViewById(R.id.edtPhoneNumber);
        mEdtStatus = rootView.findViewById(R.id.edtStatus);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mEdtName.setText(bundle.getString("name"));
            mEdtName.setSelection(mEdtName.getText().length());
            mEdtPhone.setText(bundle.getString("number"));
            mEdtPhone.setSelection(mEdtPhone.getText().length());
            mEdtStatus.setText(bundle.getString("status"));
            mEdtStatus.setSelection(mEdtStatus.getText().length());
        }
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name", mEdtName.getText().toString());
                bundle.putString("phone_number", mEdtPhone.getText().toString());
                bundle.putString("status", mEdtStatus.getText().toString());
                intent.putExtras(bundle);
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(), InformationFragment.REQUEST_CODE, intent);
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
        }
    }
}
