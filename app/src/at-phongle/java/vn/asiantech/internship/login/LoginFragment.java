package vn.asiantech.internship.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // Set listener for tvSingUp
        TextView tvSignUp = view.findViewById(R.id.tvSingUp);
        tvSignUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibilityBackButton(View.GONE);
    }

    @Override
    public void onClick(View v) {
        ((LoginActivity) getActivity()).replaceFragment(new SingUpFragment(), true);
    }
}
