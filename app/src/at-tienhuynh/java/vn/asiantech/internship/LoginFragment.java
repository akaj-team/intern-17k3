package vn.asiantech.internship;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created at 2017
 * Created by jackty on 23/11/2017.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private static final int START_SPAN_ENBLE_STRING = 13;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
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
        Button btnLoginFaceBook = mView.findViewById(R.id.btnLoginFacebook);
        Button btnLoginPhone = mView.findViewById(R.id.btnLoginPhone);
        TextView tvSignUp = mView.findViewById(R.id.tvSignup);
        SpannableString spannableString = new SpannableString(tvSignUp.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).replaceFragment(SignupFragment.newInstance(), true);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.bgColor = Color.WHITE;
                ds.setARGB(255, 255, 255, 255);
                ds.setUnderlineText(false);
                ds.setColor(ContextCompat.getColor(getContext(), R.color.colorLink));
            }
        };
        spannableString.setSpan(clickableSpan, START_SPAN_ENBLE_STRING, tvSignUp.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSignUp.setText(spannableString);
        tvSignUp.setMovementMethod(LinkMovementMethod.getInstance());
        // Init OnClickListener
        btnLoginFaceBook.setOnClickListener(this);
        btnLoginPhone.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        ((LoginActivity) getActivity()).setVisibilityImageBack(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginFacebook:
                Toast.makeText(getContext(), "FB", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLoginPhone:
                Toast.makeText(getContext(), "phone", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
