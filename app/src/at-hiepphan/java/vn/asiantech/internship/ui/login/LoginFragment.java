package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import vn.asiantech.internship.R;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // No-op
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView tvLogin = view.findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).replaceFragment(new InfoFragment());
            }
        });
        return view;
    }
}
