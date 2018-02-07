package vn.asiantech.internship.ui.tutorialkotlin

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import kotlinx.android.synthetic.`at-nhatnguyen`.activity_sign_up.*
import vn.asiantech.internship.R

class SignUpActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        changeColorBtnNext()
        edtEmailSignUp.addTextChangedListener(this)
        edtPassWordSignUp.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        changeColorBtnNext()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    private fun changeColorBtnNext() {
        if (TextUtils.isEmpty(edtEmailSignUp.text) || TextUtils.isEmpty(edtPassWordSignUp.text)) {
            tvNextSignUp.setTextColor(ContextCompat.getColor(this, R.color.colorRed100))
        } else {
            tvNextSignUp.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        }
    }
}
