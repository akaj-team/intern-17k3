package vn.asiantech.internship.ui.login;
/**
 * Created by anh.quach on 11/23/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class LoginButtonFragment extends Fragment {
    private TextView mTvSignup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_button, container, false);
        mTvSignup = view.findViewById(R.id.tvSignUp);
        mTvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).replace(new InfoEnterFragment(), true);
            }
        });
        return view;
    }

}
