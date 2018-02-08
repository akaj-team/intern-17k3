package vn.asiantech.internship.kotlin

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.`at-vietphan`.activity_join_us_kotlin.*
import vn.asiantech.internship.R

class JoinUsKotlinActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
        // No-op
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // No-op
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        tvNext.isEnabled = !(TextUtils.isEmpty(edtName.text.trim()) || TextUtils.isEmpty(edtEmail.text.trim()) || TextUtils.isEmpty(edtPassword.text.trim()))
        if (tvNext.isEnabled) {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.edt_color_join_us))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_us_kotlin)
        imgBack.setOnClickListener(this)
        edtName.addTextChangedListener(this)
        edtEmail.addTextChangedListener(this)
        edtPassword.addTextChangedListener(this)
    }

    override fun onClick(p0: View?) {
        finish()
    }
}
