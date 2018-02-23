package vn.asiantech.internship.kotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.`at-vietphan`.activity_login_kotlin.*
import vn.asiantech.internship.MainApplication
import vn.asiantech.internship.R
import vn.asiantech.internship.kotlin.models.User


class LoginKotlinActivity : AppCompatActivity(), View.OnClickListener, TextWatcher, View.OnTouchListener {
    var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_kotlin)
        imgBack.setOnClickListener(this)
        tvNext.setOnClickListener(this)
        edtEmail.addTextChangedListener(this)
        edtPassword.addTextChangedListener(this)
        if (scrollViewSignIn !is EditText) {
            scrollViewSignIn.setOnTouchListener(this)
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class CheckUserLogin : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            user = MainApplication.database?.userDao()?.findUserByEmailAndPassword(edtEmail.text.toString().trim(), edtPassword.text.toString().trim())!!
            return null
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            view?.performClick()
            hideSoftKeyboard(this)
        }
        return true
    }

    override fun afterTextChanged(p0: Editable?) {
        // No-op
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // No-op
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        tvNext.isEnabled = !(TextUtils.isEmpty(edtEmail.text.trim()) || TextUtils.isEmpty(edtPassword.text.trim()))
        if (tvNext.isEnabled) {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        } else {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.tv_next_pink_color_not_select))
        }
    }

    override fun onClick(view: View?) {
        view?.apply {
            if (id == R.id.tvNext) {
                CheckUserLogin().execute()
                Thread.sleep(500)
                if (user != null) {
                    Toast.makeText(applicationContext, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "User not exists!", Toast.LENGTH_SHORT).show()
                }
            } else {
                finish()
            }
        }
    }
}
