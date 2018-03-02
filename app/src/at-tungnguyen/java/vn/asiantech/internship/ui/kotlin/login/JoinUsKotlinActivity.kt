package vn.asiantech.internship.ui.kotlin.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.`at-tungnguyen`.activity_join_us_kotlin.*
import vn.asiantech.internship.R
import vn.asiantech.internship.ui.kotlin.KotlinApplication
import vn.asiantech.internship.ui.kotlin.tutorial.TutorialActivity

class JoinUsKotlinActivity : AppCompatActivity(), View.OnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_us_kotlin)
        initListener()
        isCheckNext()
        isCheckKeyboard()
        initClick()
    }

    private fun initClick() {
        imgBackJoin.setOnClickListener({
            finish()
        })
        tvNextJoinUs.setOnClickListener({
            AsyncTaskJoinUS().execute()
        })
    }

    fun getUser(): UserLogin {
        val mailUser = edtEmailJoinUs.text.toString()
        val passUser = edtPassWordJoinUs.text.toString()
        return UserLogin(mailUser, passUser)
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        if (p1!!.action == MotionEvent.ACTION_UP) {
            hideSoftKeyboard(this)
        }
        return true
    }

    private fun initListener() {
        edtNameJoinUs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                isCheckNext()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //No-opp
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //No-opp
            }

        })
        edtEmailJoinUs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //No-opp
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //No-opp
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isCheckNext()
            }

        })
        edtPassWordJoinUs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                isCheckNext()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //No-opp
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //No-opp
            }

        })
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskJoinUS : AsyncTask<Void, String, Boolean>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg p0: Void?): Boolean{
            if(tvNextJoinUs.isSelected){
                KotlinApplication.database?.userDao()?.run {
                    insertUser(getUser())
                    getAllUser()
                }
            }else{
                return false
            }
            Log.d("AAA",""+KotlinApplication.database?.userDao()?.getAllUser())
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            if(result==false){
                Toast.makeText(this@JoinUsKotlinActivity,"Nhap du thong tin",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@JoinUsKotlinActivity,"OK",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@JoinUsKotlinActivity,TutorialActivity::class.java))
            }
        }
    }



    private fun isCheckNext() {
        tvNextJoinUs.isSelected = !TextUtils.isEmpty(edtNameJoinUs.text) && !TextUtils.isEmpty(edtEmailJoinUs.text) && !TextUtils.isEmpty(edtPassWordJoinUs.text) && isCheckEmail(edtEmailJoinUs.text.toString())
    }

    private fun isCheckKeyboard() {
        if (llJoinUs !is EditText) {
            llJoinUs.setOnTouchListener(this)
        }
    }

    private fun isCheckEmail(email: String): Boolean {
        val expression = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
        return email.matches(Regex(expression))
    }
}
