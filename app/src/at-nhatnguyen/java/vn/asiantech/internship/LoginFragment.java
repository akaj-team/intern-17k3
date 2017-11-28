package vn.asiantech.internship;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView tvSignin = view.findViewById(R.id.tvSignin);
        Button btnLoginFacebook = view.findViewById(R.id.btnLoginFacebook);
        btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(new InforFragment(), true);
            }
        });
        clickOnTextView(tvSignin, 12, tvSignin.getText().length());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setVisibleBackButton(View.GONE);
    }

    private void clickOnTextView(TextView textView, int start, int end) {
        Spannable mSpannable = new SpannableString(textView.getText().toString());
        mSpannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(new InforFragment(), true);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.color_button_phonenum_and_signup));
            }
        }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(mSpannable);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
