package vn.asiantech.internship;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jackty on 23/11/2017.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private Button mBtnLoginFaceBook, mBtnLoginPhone;
    private TextView mTvSignup;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        // Inflate the layout for this fragment
        init();
        return mView;
    }

    private void init() {
        mBtnLoginFaceBook = mView.findViewById(R.id.btnLoginFacebook);
        mBtnLoginPhone = mView.findViewById(R.id.btnLoginPhone);
        mTvSignup = mView.findViewById(R.id.tvSignup);
        // onclick btn
        mBtnLoginFaceBook.setOnClickListener(this);
        mBtnLoginPhone.setOnClickListener(this);
        mTvSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginFacebook:
                //
                Toast.makeText(getContext(), "FB", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLoginPhone:
                //
                Toast.makeText(getContext(), "phone", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvSignup:
                //
                ((LoginActivity) getActivity()).replaceFragment(SignupFragment.newInstance());
                break;
        }
    }
}

