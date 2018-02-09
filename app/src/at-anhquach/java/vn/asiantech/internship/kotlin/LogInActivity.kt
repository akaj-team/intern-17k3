package vn.asiantech.internship.kotlin

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.`at-anhquach`.activity_log_in.*
import vn.asiantech.internship.R

/**
 *
 * Created by anh.quach on 2/9/18.
 */
class LogInActivity : AppCompatActivity(), JoinUsFragment.ActiveNext {
    var fragment: Int = 0
    override fun onActiveNext(isFullInfo: Boolean) {
        if (isFullInfo) {
            if (fragment == 1) {
                tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorBlue300))
            } else {
                tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorRedLight))
            }
        } else {
            if (fragment == 1) {
                tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorBlue100))
            } else {
                tvNext.setTextColor(ContextCompat.getColor(this, R.color.colorRed100))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        fragment = intent.getIntExtra("fragment", 0)
        if (fragment == 0) {
            imgBack.setImageResource(R.drawable.ic_previous_red)
            tvNext.setTextColor(resources.getColor(R.color.colorRed100))
        }
        replaceFragment(fragment)
        imgBack.setOnClickListener { finish() }
    }

    protected fun replaceFragment(type: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (type == 0) {
            fragmentTransaction.replace(R.id.frLogin, SignInFragment.newInstance())
        } else {
            fragmentTransaction.replace(R.id.frLogin, JoinUsFragment.newInstance())
        }
        fragmentTransaction.commit()
    }
}
