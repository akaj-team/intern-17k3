package vn.asiantech.internship.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.`at-vietphan`.activity_tutorial_kotlin.*
import vn.asiantech.internship.R
import vn.asiantech.internship.kotlin.adapter.TutorialAdapter
import vn.asiantech.internship.kotlin.models.ItemWelcome

class TutorialActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mPagerAdapter: TutorialAdapter
    lateinit var mItemWelcomeList: MutableList<ItemWelcome>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_kotlin)
        initData()
        initAdapter()
        initListeners()
        pageIndicatorView.setViewPager(viewPagerTutorial)
    }

    private fun initData() {
        mItemWelcomeList = mutableListOf()
        mItemWelcomeList.add(ItemWelcome("Get started 1", "aaaaaaaaa\naaaaaaaaa", R.drawable.img_welcome_1, R.color.colorItemWelcome_0))
        mItemWelcomeList.add(ItemWelcome("Get started 2", "bbbbbbbbbbbbbbb\nbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", R.drawable.img_welcome_2, R.color.colorItemWelcome_1))
        mItemWelcomeList.add(ItemWelcome("Get started 3", "cccccccccccc\ncccccccccccc\ncccccccccccc", R.drawable.img_welcome_3, R.color.colorItemWelcome_2))
    }

    private fun initAdapter() {
        mPagerAdapter = TutorialAdapter(supportFragmentManager, mItemWelcomeList)
        viewPagerTutorial.adapter = mPagerAdapter
        Log.d("aaa", mItemWelcomeList.toString())
    }

    private fun initListeners() {
        tvSignIn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        startActivity(Intent(this, LoginKotlinActivity::class.java))
    }
}
