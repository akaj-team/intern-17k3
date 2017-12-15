package vn.asiantech.internship.viewpager.information;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;


public class AlertDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button mBtnSubmit;
    private Button mBtnCancel;
    private EditText mEdtName;
    private int REQUEST_CODE ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog, null,
                false);
        getDialog().setTitle("Information");
        mBtnSubmit = rootView.findViewById(R.id.btnSubmit);
        mBtnCancel = rootView.findViewById(R.id.btnCancel);
        mEdtName = rootView.findViewById(R.id.edtName);

        mBtnSubmit.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                break;
            case R.id.btnCancel:
                dismiss();
                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
