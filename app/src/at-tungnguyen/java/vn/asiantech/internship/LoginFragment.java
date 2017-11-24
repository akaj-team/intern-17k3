package vn.asiantech.internship;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tungnguyen on 23/11/2017.
 */

public class LoginFragment extends Fragment {
    private TextView mtvSignup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mtvSignup = view.findViewById(R.id.tvSignup);
        ((MainActivity)getActivity()).getBack().setVisibility(View.INVISIBLE);
        mtvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replace(new SignUpFragment(), true);
            }
        });

        return view;
    }
}
