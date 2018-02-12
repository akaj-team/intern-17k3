package vn.asiantech.internship.ui.tutorialkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import kotlinx.android.synthetic.`at-nhatnguyen`.activity_join_us.*
import vn.asiantech.internship.R

class JoinUsActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_us)
        changeColorBtnNext()
        btnJoinUpBack.setOnClickListener({ startActivity(Intent(this, TutorialActivity::class.java)) })
        edtEmailJoinUp.addTextChangedListener(this)
        edtPasswordJoinUs.addTextChangedListener(this)
        edtNameJoinUs.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        changeColorBtnNext()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    private fun changeColorBtnNext() {
        if (TextUtils.isEmpty(edtEmailJoinUp.text) || TextUtils.isEmpty(edtPasswordJoinUs.text) || TextUtils.isEmpty(edtNameJoinUs.text)) {
            tvNextJoinUs.setTextColor(ContextCompat.getColor(this, R.color.colorBlue100))
        } else {
            tvNextJoinUs.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        }
    }
}
