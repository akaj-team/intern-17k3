package vn.asiantech.internship.ui.viewpager;

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
        getDialog().setTitle("Update Information");
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

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null) {
            return;
        }
        float dialogWidth = (float) (getActivity().getWindowManager().getDefaultDisplay().getWidth() * 0.9);
        float dialogHeight = (float) (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 0.9);
        getDialog().getWindow().setLayout((int)dialogWidth, (int)dialogHeight);
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
