package vn.asiantech.internship.ui.viewpager.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;
import vn.asiantech.internship.ui.service.MusicActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnotherFragment extends Fragment implements View.OnClickListener {
    private Button mBtnService;
    private View mView;

    public AnotherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_another, container, false);
        initViews();
        initClick();
        return mView;
    }

    private void initViews() {
        mBtnService = mView.findViewById(R.id.btnAnother);
    }

    private void initClick() {
        mBtnService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAnother:
                Intent intentService = new Intent(getContext(), MusicActivity.class);
                startActivity(intentService);
                break;
        }
    }
}
