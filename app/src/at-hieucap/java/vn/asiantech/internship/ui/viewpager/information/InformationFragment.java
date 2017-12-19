package vn.asiantech.internship.ui.viewpager.information;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vn.asiantech.internship.R;

/**
 * Create Information Fragment
 */
public class InformationFragment extends Fragment {
    public static final int REQUEST_CODE = 1;
    private TextView mTvName;
    private TextView mTvPhone;
    private TextView mTvStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater
                .from(container.getContext())
                .inflate(R.layout.fragment_information, container, false);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        mTvName = view.findViewById(R.id.tvName);
        mTvPhone = view.findViewById(R.id.tvPhoneNumber);
        mTvStatus = view.findViewById(R.id.tvStatus);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                mTvName.setText(bundle.getString("name"));
                mTvPhone.setText(bundle.getString("phone_number"));
                mTvStatus.setText(bundle.getString("status"));
            }
        }
    }

    private void showDialog() {
        AlertDialogFragment alertDialogFragment;
        alertDialogFragment = AlertDialogFragment.newInstance(mTvName.getText().toString(), mTvPhone.getText().toString(),
                mTvStatus.getText().toString());
        alertDialogFragment.setTargetFragment(this, REQUEST_CODE);
        alertDialogFragment.show(getFragmentManager(), "Alert Dialog Fragment");
    }
}
