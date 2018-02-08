package vn.asiantech.internship.kotlin

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.`at-vietphan`.activity_login_kotlin.*
import vn.asiantech.internship.R

class LoginKotlinActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
        //No-op
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // No-op
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        tvNext.isEnabled = !(TextUtils.isEmpty(edtEmail.text.trim()) || TextUtils.isEmpty(edtPassword.text.trim()))
        if (tvNext.isEnabled) {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_kotlin)
        imgBack.setOnClickListener(this)
        edtEmail.addTextChangedListener(this)
        edtPassword.addTextChangedListener(this)
    }

    override fun onClick(p0: View?) {
        finish()
    }
}
