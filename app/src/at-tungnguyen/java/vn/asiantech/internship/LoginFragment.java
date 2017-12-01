package vn.asiantech.internship;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    private static final int START_SPAN = 12;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView mTvSignUp = view.findViewById(R.id.tvSignUp);
        ((MainActivity) getActivity()).getBack(View.INVISIBLE);
        mTvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(SignUpFragment.newInstance(), true);
            }
        });
        SpannableString spannableString = new SpannableString(mTvSignUp.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // No-op
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.bgColor = Color.WHITE;
                ds.setARGB(255, 255, 255, 255);
                ds.setUnderlineText(false);
                ds.setColor(ContextCompat.getColor(getContext(), R.color.colorSignup));
            }
        };
        spannableString.setSpan(clickableSpan,START_SPAN, mTvSignUp.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvSignUp.setText(spannableString);
        mTvSignUp.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }
}