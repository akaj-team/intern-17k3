package vn.asiantech.internship.utils;

import android.annotation.SuppressLint;
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
 * Class MyEditText
 */
public class MyEditTextWhileClear extends AppCompatEditText {
    public Drawable imgCloseButton = getResources().getDrawable(R.drawable.ic_clear_white_24dp);

    public MyEditTextWhileClear(Context context) {
        super(context);
        init();
    }

    public MyEditTextWhileClear(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditTextWhileClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {
        imgCloseButton.setBounds(0, 0, imgCloseButton.getIntrinsicWidth(), imgCloseButton.getIntrinsicHeight());
        handleClearButton();
        //if the Close image is displayed and the user remove his finger from the button, clear it. Otherwise do nothing
        this.setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyEditTextWhileClear et = MyEditTextWhileClear.this;
                if (et.getCompoundDrawables()[2] == null) {
                    return false;
                }
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > et.getWidth() - et.getPaddingRight() - imgCloseButton.getIntrinsicWidth()) {
                    et.setText("");
                    MyEditTextWhileClear.this.handleClearButton();
                }
                return false;
            }
        });
        this.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No-op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MyEditTextWhileClear.this.handleClearButton();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // No-op
            }
        });
    }

    void handleClearButton() {
        if (this.getText().toString().equals("")) {
            // add the clear button
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
        } else {
            //remove clear button
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], imgCloseButton, this.getCompoundDrawables()[3]);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean performClick() {
        return true;
    }
}