package vn.asiantech.internship.ui.login;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import vn.asiantech.internship.R;

/**
 * Class IndexFragment: screen index login
 */
public class IndexFragment extends Fragment {
    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).replaceFragment(LoginFragment.newInstance(), true);
            }
        });
        TextView tvMemoLogin = view.findViewById(R.id.tvMemoLogin);
        SpannableString spannableString = new SpannableString(tvMemoLogin.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof LoginActivity) {
                    ((LoginActivity) getActivity()).replaceFragment(new LoginFragment(), true);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.bgColor = Color.WHITE;
                ds.setARGB(255, 255, 255, 255);
                ds.setUnderlineText(false);
                ds.setColor(ContextCompat.getColor(getContext(), R.color.colorSignupAcc));
                ds.setTypeface(Typeface.DEFAULT_BOLD);            }
        };
        spannableString.setSpan(clickableSpan, 12, tvMemoLogin.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMemoLogin.setText(spannableString);
        tvMemoLogin.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginActivity) getActivity()).setVisibilityBackImageView(View.INVISIBLE);
    }
}
