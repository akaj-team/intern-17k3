package vn.asiantech.internship.ui.login;

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
import android.widget.TextView;

import vn.asiantech.internship.R;


public class LoginButtonFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_button, container, false);
        ((LoginActivity) getActivity()).getImgBack().setVisibility(View.INVISIBLE);
        TextView tvMemoLogin = view.findViewById(R.id.tvMemoLogin);
        SpannableString spannableString = new SpannableString(tvMemoLogin.getText());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).replace(new InfoEnterFragment(), true);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.bgColor = Color.WHITE;
                ds.setARGB(255, 255, 255, 255);
                ds.setUnderlineText(false);
                ds.setColor(ContextCompat.getColor(getContext(), R.color.colorTextBlue));
            }
        };
        spannableString.setSpan(clickableSpan, 12, tvMemoLogin.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMemoLogin.setText(spannableString);
        tvMemoLogin.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }

}
