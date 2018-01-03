package vn.asiantech.internship.ui.viewpager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import vn.asiantech.internship.util.ScreenUtil;

/**
 * Created by hoangnhat on 14/12/2017.
 * Dialog update information
 */
public class UpdateDialog extends DialogFragment {
    private EditText mEdtNameUpdate;
    private EditText mEdtOldUpdate;
    private EditText mEdtAdressUpdate;

    public static UpdateDialog newInstance() {
        Bundle args = new Bundle();
        UpdateDialog fragment = new UpdateDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_dialog, container);
        mEdtNameUpdate = view.findViewById(R.id.etdNameUpdate);
        mEdtOldUpdate = view.findViewById(R.id.edtOldUpdate);
        mEdtAdressUpdate = view.findViewById(R.id.edtAdressUpdate);
        Button btnOk = view.findViewById(R.id.btnOk);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBackResult();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
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
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        final Window window = dialog.getWindow();
        if (window != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(((int)(ScreenUtil.getWidthScreen(getActivity()) * 0.8)),
                    ((int) (ScreenUtil.getHeightScreen(getActivity()) * 0.8)));
        }
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public void sendBackResult() {
        EditNameDialogListener listener = (EditNameDialogListener) getTargetFragment();
        listener.onFinishEditDialog(mEdtNameUpdate.getText().toString()
                , mEdtOldUpdate.getText().toString().trim()
                , mEdtAdressUpdate.getText().toString().trim());
        dismiss();
    }

    /**
     * This interface use for send data
     */
    public interface EditNameDialogListener {

        void onFinishEditDialog(String inputName, String inputOld, String inputAdress);
    }
}
