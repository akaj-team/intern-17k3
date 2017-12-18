package vn.asiantech.internship.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 14/12/2560.
 * Fragment information
 */
public class InformationFragment extends Fragment implements EditInfoDialogFragment.DialogEditInfoListener {
    private TextView mTvName;
    private TextView mTvPhone;
    private TextView mTvHometown;
    private Button mBtnUpdate;

    public InformationFragment() {
        // No op
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_infomation, container, false);
        initViews(view);
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                EditInfoDialogFragment editInfoDialogFragment = new EditInfoDialogFragment();
                editInfoDialogFragment.setTargetFragment(InformationFragment.this, 300);
                editInfoDialogFragment.show(fragmentManager, "UpdateInformation");
                editInfoDialogFragment.setCancelable(false);
            }
        });
        return view;
    }

    private void initViews(View view) {
        mTvName = view.findViewById(R.id.tvName);
        mTvPhone = view.findViewById(R.id.tvPhone);
        mTvHometown = view.findViewById(R.id.tvHomeTown);
        mBtnUpdate = view.findViewById(R.id.btnUpdateInfo);
    }

    @Override
    public void editInfoNameListener(String text) {
        mTvName.setText(text);
    }

    @Override
    public void editInfoPhoneListener(String text) {
        mTvPhone.setText(text);
    }

    @Override
    public void editInfoHometownListener(String text) {
        mTvHometown.setText(text);
    }
}
