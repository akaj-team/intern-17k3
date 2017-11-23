package vn.asiantech.internship.ui.login;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import vn.asiantech.internship.R;

public class LoginFragment extends Fragment {
    private TextView mtxtLogin;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mtxtLogin=view.findViewById(R.id.txtLogin);
        mtxtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).replace(new InfoFragment());
            }
        });
        return view;
    }
}
