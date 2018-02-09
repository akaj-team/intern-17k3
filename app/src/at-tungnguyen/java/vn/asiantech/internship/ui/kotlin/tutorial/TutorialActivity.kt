package vn.asiantech.internship.ui.kotlin.tutorial

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.`at-tungnguyen`.activity_tutorial.*
import vn.asiantech.internship.R

class TutorialActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    private var mIsFinishSlide:Boolean = false
    private var mLast:Int = 0
    var tutorialAdapter: TutorialAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        initAdapter()

    }

    private fun initAdapter() {
        tutorialAdapter = TutorialAdapter(supportFragmentManager)
        viewPagerTutorial.adapter = tutorialAdapter
        indicatorTutorial.setViewPager(viewPagerTutorial)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        mIsFinishSlide = mLast == tutorialAdapter!!.count - 1
        mLast = position
    }

    override fun onPageSelected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

