package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.asiantech.internship.R;

/**
 * Created by tiboo on 23/11/2017.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    public static Fragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.login_fragment, null);
        Button btnLogin = v.findViewById(R.id.btnLogIn2);
        btnLogin.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        ((LoginActivity)getActivity()).addFragment(new FilloutFragment(), true);
    }

}
