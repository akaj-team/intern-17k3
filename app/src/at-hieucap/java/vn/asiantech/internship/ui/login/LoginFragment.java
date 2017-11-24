package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import vn.asiantech.internship.R;

public class LoginFragment extends Fragment implements View.OnClickListener {

    public static Fragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_log_in, container,false);
        TextView tvSignUp = v.findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        ((LogInActivity)getActivity()).addFragment(new FillOutFragment(), true);
    }

}
