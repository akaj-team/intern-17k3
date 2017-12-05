package vn.asiantech.internship.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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
        TextView tvSignUp = view.findViewById(R.id.tvSingUp);
        setTextSignUp(tvSignUp);
        return view;
    }

    private void setTextSignUp(TextView tvSignUp) {
        SpannableString ss = new SpannableString(getString(R.string.tv_sign_up));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                ((LoginActivity) getActivity()).replaceFragment(new SingUpFragment(), true);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getContext().getResources().getColor(R.color.blue_dark));
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 12, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 12, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSignUp.setText(ss);
        tvSignUp.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibilityBackButton(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvSingUp) {
            ((LoginActivity) getActivity()).replaceFragment(new SingUpFragment(), true);
        }
    }
}
