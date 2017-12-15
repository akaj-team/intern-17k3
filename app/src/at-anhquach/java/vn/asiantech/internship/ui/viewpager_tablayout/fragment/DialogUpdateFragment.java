package vn.asiantech.internship.ui.viewpager_tablayout.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.viewpager_tablayout.ScreenUtil;

public class DialogUpdateFragment extends DialogFragment {
    private EditText mEdtName;
    private EditText mEdtEmail;
    private EditText mEdtPhone;
    private Button mBtnUpdate;
    private Button mBtnCancel;

    public static DialogFragment newInstance() {
        return new DialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_update, container, false);
        initViews(view);
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDialogUpdateListener onDialogUpdateListener = (onDialogUpdateListener) getTargetFragment();
                onDialogUpdateListener.onDialogUpdateData(mEdtName.getText().toString(), mEdtPhone.getText().toString(), mEdtEmail.getText().toString());
                dismiss();

            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        dialog.setContentView(R.layout.fragment_dialog_update);
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout((int) (ScreenUtil.getWidthScreen(getActivity()) * 0.9), (int) (ScreenUtil.getHeightScreen(getActivity()) * 0.6));
            window.setGravity(Gravity.CENTER);
        }
        return dialog;
    }

    private void initViews(View view) {
        mEdtName = view.findViewById(R.id.edtNameUserDialog);
        mEdtPhone = view.findViewById(R.id.edtPhoneNumberDialog);
        mEdtEmail = view.findViewById(R.id.edtEmailDialog);
        mBtnUpdate = view.findViewById(R.id.btnUpdateDialog);
        mBtnCancel = view.findViewById(R.id.btnCancelDialog);
    }

    public interface onDialogUpdateListener {
        void onDialogUpdateData(String name, String email, String phone);
    }
}
