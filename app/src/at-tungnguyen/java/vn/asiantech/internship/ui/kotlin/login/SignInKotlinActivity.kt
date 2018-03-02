package vn.asiantech.internship.ui.kotlin.login

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.`at-tungnguyen`.activity_sign_in_kotlin.*
import vn.asiantech.internship.R
import vn.asiantech.internship.ui.kotlin.KotlinApplication

class SignInKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_kotlin)
        initClick()
        initListener()
        isCheckNext()
    }

    /**
     * initCLick SignInKotlinActivity
     */
    private fun initClick() {
        imgBackSignin.setOnClickListener({
            finish()
        })
        tvNextSignIn.setOnClickListener({
            AsyncTaskSignIn().execute()
        })
    }

    /**
     * initListener SignInKotlinActivity
     */
    private fun initListener() {
        edtIdSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isCheckNext()
            }
        })
        edtEmailSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isCheckNext()
            }
        })
        edtPassWordSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isCheckNext()
            }

        })

    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskSignIn : AsyncTask<Void, String, String>() {
        override fun doInBackground(vararg p0: Void?): String {
            val user: UserLogin? = KotlinApplication.database?.userDao()?.checkUser(edtEmailSignIn.text.toString(), edtPassWordSignIn.text.toString())
            if (user != null) {
                runOnUiThread({
                    Toast.makeText(this@SignInKotlinActivity, R.string.tv_login_success, Toast.LENGTH_SHORT).show()
                })
            } else {
                runOnUiThread({
                    Toast.makeText(this@SignInKotlinActivity, R.string.tv_login_fail, Toast.LENGTH_SHORT).show()

                })
            }

            return "Thêm thành công"
        }
    }

    /**
     * Check TextView Next Selected
     */
    private fun isCheckNext() {
        tvNextSignIn.isSelected = !TextUtils.isEmpty(edtIdSignIn.text) && !TextUtils.isEmpty(edtEmailSignIn.text) && !TextUtils.isEmpty(edtPassWordSignIn.text) && isCheckEmail(edtEmailSignIn.text.toString())
    }

    /**
     * Check Email Regex
     */
    private fun isCheckEmail(email: String): Boolean {
        val expression = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
        return email.matches(Regex(expression))
    }
}
