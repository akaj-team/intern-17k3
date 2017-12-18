package vn.asiantech.internship.viewpager;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 15/12/2560.
 * DialogFragment edit information
 */

public class EditInfoDialogFragment extends DialogFragment {
    private EditText mEdtName;
    private EditText mEdtPhone;
    private EditText mEdtHometown;
    private Button mBtnUpdate;
    private Button mBtnCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_edit_info_dialog, container, false);
        initViews(view);
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditInfoListener dialogEditInfoListener = (DialogEditInfoListener) getTargetFragment();
                dialogEditInfoListener.editInfoNameListener(mEdtName.getText().toString().trim());
                dialogEditInfoListener.editInfoPhoneListener(mEdtPhone.getText().toString().trim());
                dialogEditInfoListener.editInfoHometownListener(mEdtHometown.getText().toString().trim());
                dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        window.requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_edit_info_dialog);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout((int) (ScreenUtil.getWidthScreen(getActivity()) * 0.8), (int) (ScreenUtil.getHeightScreen(getActivity()) * 0.6));
        return dialog;
    }

    private void initViews(View view) {
        mEdtName = view.findViewById(R.id.edtName);
        mEdtPhone = view.findViewById(R.id.edtPhoneNum);
        mEdtHometown = view.findViewById(R.id.edtHomeTown);
        mBtnUpdate = view.findViewById(R.id.btnDialogUpdate);
        mBtnCancel = view.findViewById(R.id.btnDialogCancel);
    }

    public interface DialogEditInfoListener {
        void editInfoNameListener(String text);

        void editInfoPhoneListener(String text);

        void editInfoHometownListener(String text);
    }
}
