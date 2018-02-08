package vn.asiantech.internship.loginkotlintutorial.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.`at-tienhuynh`.activity_login2.*
import vn.asiantech.internship.R

class LoginActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        addTextChangedListener()
        initListener()
        if (llSignIn !is EditText) {
            llSignIn.setOnTouchListener { v, event ->
                hideSoftKeyboard(this@LoginActivity)
                false
            }
        }
    }

    private fun initListener() {
        imgBackLogin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity, TutorialActivity::class.java))
        })
    }

    private fun addTextChangedListener() {
        edtEmailLogin.addTextChangedListener(this)
        edtPassLogin.addTextChangedListener(this)
    }

    private fun checkEmptyText() {
        if (TextUtils.isEmpty(edtEmailLogin.text) || TextUtils.isEmpty(edtPassLogin.text)) {
            tvNextLogin.setTextColor(ContextCompat.getColor(this, R.color.colorRed100))
        } else {
            tvNextLogin.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

    override fun afterTextChanged(p0: Editable?) {
        checkEmptyText()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // No-op
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // No-op
    }
}
