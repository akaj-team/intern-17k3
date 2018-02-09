package vn.asiantech.internship.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.`at-anhquach`.activity_tutorial.*
import vn.asiantech.internship.R

/**
 *
 * Created by anh.quach on 2/2/18.
 */
class TutorialActivity : AppCompatActivity() {
    private var tutorials: MutableList<Tutorial> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        setData()
        viewPagerTutor.adapter = TutorialAdapter(supportFragmentManager, tutorials)
        pageIndicatorView.setViewPager(viewPagerTutor)
        tvSignIn.setOnClickListener {
            val intent = Intent(this@TutorialActivity, LogInActivity::class.java)
            intent.putExtra("fragment", 0)
            startActivity(intent)
        }

    }

    private fun setData() {
        tutorials.add(Tutorial("Welcome", "Express yourself through the art of the fashionism.", R.color.colorYellow, R.drawable.img_tutor_pager1, 0))
        tutorials.add(Tutorial("Be unique", "Your profile is your online art gallery.", R.color.colorCyanLight, R.drawable.img_tutor_pager2, 0))
        tutorials.add(Tutorial("Get started", "Inspire everyone with the power of your ideas.", R.color.colorRedLight, R.drawable.img_tutor_pager3, 1))
    }
}
