package vn.asiantech.internship.ui.kotlinlogin

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.`at-hieucap`.activity_sign_in.*
import vn.asiantech.internship.R


@SuppressLint("Registered")
/**
 * Created by tiboo on 08/02/2018.
 * Create sign in activity
 */
class SignInActivity : AppCompatActivity(), View.OnClickListener, View.OnTouchListener, TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        // No-op
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // No-op
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        tvNext.isEnabled = (isValidEmail(edtEmail.text.toString()) &&
                !TextUtils.isEmpty(edtPassword.text.trim()))
        if (tvNext.isEnabled) {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorTextSignIn))
        } else {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorNextHint))
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            v?.performClick()
            hideSoftKeyboard(this)
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgBack -> {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        if (scrollViewContain !is EditText) {
            scrollViewContain.setOnTouchListener { _, _ ->
                hideSoftKeyboard(this)
                false
            }
        }
        if (scrollViewContain !is EditText) {
            scrollViewContain.setOnTouchListener(this)
        }
        imgBack.setOnClickListener(this)

        edtEmail.addTextChangedListener(this)
        edtPassword.addTextChangedListener(this)
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
