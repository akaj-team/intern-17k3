package vn.asiantech.internship.ui.tutorialkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.`at-nhatnguyen`.activity_tutorial.*
import vn.asiantech.internship.R
import vn.asiantech.internship.models.Tutorial

class TutorialActivity : AppCompatActivity() {
    private lateinit var mListTutorial: MutableList<Tutorial>
    private lateinit var mTutorialAdapter: TutorialAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        initData()
        initAdapter()
        initListener()
    }

    private fun initData() {
        mListTutorial = mutableListOf()
        mListTutorial.add(Tutorial(R.color.colorItemWelcome_0, R.drawable.header1, "Welcome", "Express yourself through \n the art of the fashionism", R.drawable.ic_btn_next_tutorial))
        mListTutorial.add(Tutorial(R.color.colorItemWelcome_1, R.drawable.header2, "Be unique", "Your profile is \n your \n online art gallery", R.drawable.ic_btn_next_tutorial))
        mListTutorial.add(Tutorial(R.color.colorItemWelcome_2, R.drawable.header3, "Get started", "Inspire everyone with the \n power of your ideas", R.drawable.bg_bottom_text))
    }

    private fun initAdapter() {
        mTutorialAdapter = TutorialAdapter(mListTutorial, supportFragmentManager)
        viewPagerTutorial.adapter = mTutorialAdapter
        pageIndicatorView.setViewPager(viewPagerTutorial)
        viewPagerTutorial.currentItem = 0
    }

    private fun initListener() {
        tvSignInHeader.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
    }
}
