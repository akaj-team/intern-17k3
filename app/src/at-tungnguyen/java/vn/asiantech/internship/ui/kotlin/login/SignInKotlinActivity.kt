package vn.asiantech.internship.ui.kotlin.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import kotlinx.android.synthetic.`at-tungnguyen`.activity_sign_in_kotlin.*
import vn.asiantech.internship.R

class SignInKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_kotlin)
        initListener()
        isCheckNext()
    }
    private fun initListener(){
        edtEmailSignIn.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isCheckNext()
            }
        })
        edtPassWordSignIn.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isCheckNext()
            }

        })

    }
    private fun isCheckNext(){
        tvNextSignIn.isSelected = !TextUtils.isEmpty(edtEmailSignIn.text) && !TextUtils.isEmpty(edtPassWordSignIn.text)
    }
}
