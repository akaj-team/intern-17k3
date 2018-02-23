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
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.`at-vietphan`.activity_join_us_kotlin.*
import vn.asiantech.internship.MainApplication
import vn.asiantech.internship.R
import vn.asiantech.internship.kotlin.models.User

class JoinUsKotlinActivity : AppCompatActivity(), View.OnClickListener, TextWatcher, View.OnTouchListener {
    private lateinit var user: User
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_us_kotlin)
        imgBack.setOnClickListener(this)
        tvNext.setOnClickListener(this)
        edtName.addTextChangedListener(this)
        edtEmail.addTextChangedListener(this)
        edtPassword.addTextChangedListener(this)
        if (scrollViewJoinUs !is EditText) {
            scrollViewJoinUs.setOnTouchListener(this)
        }
    }

    //    private fun initDatabase() {
//        if (userDB == null) {
//            userDB = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "userDB").build()
//        }
//    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

    @SuppressLint("StaticFieldLeak")
    private inner class InsertUser : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
//            user = User(edtName.text.toString().trim(),edtPassword.text.toString().trim(),edtEmail.text.toString().toString.trim())
            user = User()
            user.userName = edtName.text.toString().trim()
            user.password = edtPassword.text.toString().trim()
            user.email = edtEmail.text.toString().trim()
            MainApplication.database?.userDao()?.insert(user)
            return null
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class GetAllUser : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            userList = MainApplication.database?.userDao()?.getAllUser() as MutableList<User>
            return null
        }
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
        tvNext.isEnabled = !(TextUtils.isEmpty(edtName.text.trim()) || TextUtils.isEmpty(edtEmail.text.trim()) || TextUtils.isEmpty(edtPassword.text.trim()))
        if (tvNext.isEnabled) {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.edt_color_join_us))
        } else {
            tvNext.setTextColor(ContextCompat.getColor(this, R.color.tv_next_color_not_select))
        }
    }

    override fun onClick(view: View?) {
        view?.apply {
            if (id == R.id.tvNext) {
                InsertUser().execute()
                GetAllUser().execute()
                Thread.sleep(500)
                Log.d("xxx", user.toString())
                Log.d("ccc", userList.toString())
            } else {
                finish()
            }
        }
    }
}
