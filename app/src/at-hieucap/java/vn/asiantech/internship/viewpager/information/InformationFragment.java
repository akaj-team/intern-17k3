package vn.asiantech.internship.viewpager.information;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;

public class InformationFragment extends Fragment {
    FragmentManager fm = getFragmentManager();
    private Button mBtnUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater
                .from(container.getContext())
                .inflate(R.layout.fragment_information, container, false);
        mBtnUpdate = view.findViewById(R.id.btnUpdate);
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                alertDialogFragment.show(getChildFragmentManager(), "Alert Dialog Fragment");
            }
        });
        return view;

    }
}
