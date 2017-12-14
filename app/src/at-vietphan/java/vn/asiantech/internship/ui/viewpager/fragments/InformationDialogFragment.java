package vn.asiantech.internship.ui.viewpager.fragments;


import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import vn.asiantech.internship.R;

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information_dialog, container, false);
        getDialog().setTitle(R.string.your_info);
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

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        window.setLayout(width * 9 / 10, height * 9 / 10);
        window.setGravity(Gravity.CENTER);
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
