package vn.asiantech.internship.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import vn.asiantech.internship.R;

/*
 * Created by vietphan on 11/01/2018.
 * Class MyEditTextWhileClear
 */
public class MyEditTextWhileClear extends AppCompatEditText {
    public Drawable mImgClearButton;

    public MyEditTextWhileClear(Context context) {
        this(context,null);
    }

    public MyEditTextWhileClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditTextWhileClear(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void init() {
        mImgClearButton = getResources().getDrawable(R.drawable.ic_clear_white_18dp);
        setCompoundDrawablesWithIntrinsicBounds(null, null, mImgClearButton, null);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                MyEditTextWhileClear et = MyEditTextWhileClear.this;
                if (event.getX() > et.getWidth() - et.getPaddingRight() - mImgClearButton.getIntrinsicWidth()) {
                    et.setText("");
                }
                return false;
            }
        });
        addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleClearButton();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // No-op
            }
        });
    }

    private void handleClearButton() {
        if (getText().toString().equals("")) {
            //remove clear button
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
        } else {
            // add the clear button
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mImgClearButton, getCompoundDrawables()[3]);
        }
    }
}
