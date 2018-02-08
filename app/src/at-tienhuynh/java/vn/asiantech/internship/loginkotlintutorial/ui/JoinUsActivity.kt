package vn.asiantech.internship.loginkotlintutorial.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.`at-tienhuynh`.activity_join_us.*
import vn.asiantech.internship.R

class JoinUsActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_us)
        addTextChangedListener()
        initListener()
        if (llJoinUs !is EditText) {
            llJoinUs.setOnTouchListener { v, event ->
                hideSoftKeyboard(this@JoinUsActivity)
                false
            }
        }
    }

    private fun initListener() {
        imgBackJoinUs.setOnClickListener({
            startActivity(Intent(this@JoinUsActivity, TutorialActivity::class.java))
        })
    }

    private fun addTextChangedListener() {
        edtNameJoinUs.addTextChangedListener(this)
        edtEmailJoinUs.addTextChangedListener(this)
        edtPassJoinUs.addTextChangedListener(this)
    }

    private fun checkEmptyText() {
        if (TextUtils.isEmpty(edtNameJoinUs.text) || TextUtils.isEmpty(edtEmailJoinUs.text)
                || TextUtils.isEmpty(edtPassJoinUs.text)) {
            tvNextJoinUs.setTextColor(ContextCompat.getColor(this, R.color.colorBlue100))
        } else {
            tvNextJoinUs.setTextColor(ContextCompat.getColor(this, R.color.colorBlue300))
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
        //No-op
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //No-op
    }
}
