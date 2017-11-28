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
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        TextView tvSignUp = v.findViewById(R.id.tvSignup);
        tvSignUp.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        ((LoginActivity) getActivity()).replaceFragment(new FillOutFragment(), true);
        ((LoginActivity) getActivity()).updateBackButton(true);
    }
}
