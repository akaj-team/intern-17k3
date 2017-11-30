package vn.asiantech.internship.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vn.asiantech.internship.R;

public class LoginFragment extends Fragment {
    private static final int START_TEXT = 12;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView tvSignIn = view.findViewById(R.id.tvSignIn);
        Button btnLoginFacebook = view.findViewById(R.id.btnLoginFacebook);
        btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof LoginActivity) {
                    ((LoginActivity) getActivity()).replaceFragment(InforFragment.newInstance(), true);
                }
            }
        });
        clickOnTextView(tvSignIn, START_TEXT, tvSignIn.getText().length());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibleBackButton(View.GONE);
    }

    private void clickOnTextView(TextView textView, int start, int end) {
        Spannable mSpannable = new SpannableString(textView.getText().toString());
        mSpannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof LoginActivity) {
                    ((LoginActivity) getActivity()).replaceFragment(InforFragment.newInstance(), true);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.color_button_phone));
            }
        }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(mSpannable);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
