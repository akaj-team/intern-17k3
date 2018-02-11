package vn.asiantech.internship.ui.kotlinlogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.`at-hieucap`.activity_kotlin.*
import vn.asiantech.internship.R
import vn.asiantech.internship.models.CardLogin

@SuppressLint("Registered")
/**
 * Created by tiboo on 05/02/2018.
 * Create Login activity
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private var mLoginAdapter: ViewPagerAdapter? = null
    private var mListCardLogin: ArrayList<CardLogin> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        initData()
        addListener()
        pageIndicatorView?.setViewPager(viewPagerContain)
        mLoginAdapter = ViewPagerAdapter(supportFragmentManager, mListCardLogin)
        viewPagerContain?.adapter = mLoginAdapter
        viewPagerContain?.currentItem = 0
    }

    private fun addListener() {
        tvSignIn.setOnClickListener(this)
    }

    private fun initData() {
        mListCardLogin.add(CardLogin(R.color.colorBackgroundStepOne, R.drawable.ic_avatar_1, R.string.status_screen_1, R.string.slogan_screen_1, R.drawable.ic_next, null))
        mListCardLogin.add(CardLogin(R.color.colorBackgroundStepTwo, R.drawable.ic_avatar_2, R.string.status_screen_2, R.string.slogan_screen_2, R.drawable.ic_next, null))
        mListCardLogin.add(CardLogin(R.color.colorBackgroundStepThree, R.drawable.ic_avatar_3, R.string.status_screen_3, R.string.slogan_screen_3, null, R.string.join_us))
    }
}
