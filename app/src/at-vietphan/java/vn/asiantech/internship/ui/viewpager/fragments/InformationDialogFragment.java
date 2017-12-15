package vn.asiantech.internship.ui.viewpager.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import vn.asiantech.internship.R;
import vn.asiantech.internship.utils.ScreenUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationDialogFragment extends DialogFragment implements View.OnClickListener {
    private EditText mEdtName;
    private EditText mEdtBirthday;
    private EditText mEdtEmail;
    private TextView mTvUpdate;
    private TextView mTvCancel;

    public static InformationDialogFragment newInstance() {
        return new InformationDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_information_dialog);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout((int) (ScreenUtil.getWidthScreen(getActivity()) * 0.9), (int) (ScreenUtil.getHeightScreen(getActivity()) * 0.9));
            window.setGravity(Gravity.CENTER);
        }
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information_dialog, container, false);
        initViews(view);
        initListener();
        return view;
    }

    private void initViews(View view) {
        mEdtName = view.findViewById(R.id.edtName);
        mEdtBirthday = view.findViewById(R.id.edtBirthday);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mTvUpdate = view.findViewById(R.id.tvUpdate);
        mTvCancel = view.findViewById(R.id.tvCancel);
    }

    private void initListener() {
        mTvUpdate.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvUpdate:
                sendBackResult();
                break;
            case R.id.tvCancel:
                dismiss();
                break;
        }
    }

    public void sendBackResult() {
        UpdateDialogListener dialogListener = (UpdateDialogListener) getTargetFragment();
        String name = mEdtName.getText().toString().trim();
        String birthday = mEdtBirthday.getText().toString().trim();
        String email = mEdtEmail.getText().toString().trim();
        dialogListener.onUpdateDialog(name, birthday, email);
        dismiss();
    }

    /**
     * Interface UpdateDialogListener: setonclick update in DialogFragment
     */
    public interface UpdateDialogListener {
        void onUpdateDialog(String name, String birthday, String email);
    }
}
