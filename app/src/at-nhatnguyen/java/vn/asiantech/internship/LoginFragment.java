package vn.asiantech.internship;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btnLoginFacebook;
        btnLoginFacebook = (Button) view.findViewById(R.id.btnLoginFacebook);
        btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ( ( MainActivity) getActivity()).callFragment(new InforFragment(),true);
            }
        });
        return view;
    }
}
