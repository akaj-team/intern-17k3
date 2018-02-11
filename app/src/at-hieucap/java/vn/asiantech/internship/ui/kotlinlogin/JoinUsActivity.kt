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
import kotlinx.android.synthetic.`at-hieucap`.activity_join_us.*
import vn.asiantech.internship.R

@SuppressLint("Registered")
/**
 * Created by tiboo on 09/02/2018.
 * Create join us activity
 */
class JoinUsActivity : AppCompatActivity(), TextWatcher, View.OnClickListener, View.OnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_us)
        if (scrollViewContain !is EditText) {
            scrollViewContain.setOnTouchListener { _, _ ->
                hideSoftKeyboard(this)
                false
            }
        }
        imgBack.setOnClickListener(this)
        edtName.addTextChangedListener(this)
        edtEmail.addTextChangedListener(this)
        edtPassword.addTextChangedListener(this)
        if (scrollViewContain !is EditText) {
            scrollViewContain.setOnTouchListener(this)
        }
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


    override fun afterTextChanged(s: Editable?) {
        // No-op
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // No-op
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        tvNext.isEnabled = (!TextUtils.isEmpty(edtName.text.trim()) &&
                isValidEmail(edtEmail.text.toString()) &&
                !TextUtils.isEmpty(edtPassword.text.trim()))
        if (tvNext.isEnabled) {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorBlueJoinUs))
        } else {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorTextHintJoinUs))
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
}
