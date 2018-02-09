package vn.asiantech.internship.loginkotlintutorial.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.`at-tienhuynh`.activity_tutorial.*
import vn.asiantech.internship.R
import vn.asiantech.internship.loginkotlintutorial.adapters.TutorialSlideAdapter
import vn.asiantech.internship.loginkotlintutorial.utils.InitData

class TutorialActivity : AppCompatActivity() {

    private val CURRENT_PAGE = 0
    private lateinit var mSlideAdapter: TutorialSlideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        initAdapter()
        tvSignIn.setOnClickListener({
            startActivity(Intent(this@TutorialActivity, LoginActivity::class.java))
        })
    }

    private fun initAdapter() {
        mSlideAdapter = TutorialSlideAdapter(supportFragmentManager, InitData.listsTutorial())
        viewPagerSlideTutorial.adapter = mSlideAdapter
        pageIndicatorView.setViewPager(viewPagerSlideTutorial)
        viewPagerSlideTutorial.currentItem = CURRENT_PAGE
    }
}
