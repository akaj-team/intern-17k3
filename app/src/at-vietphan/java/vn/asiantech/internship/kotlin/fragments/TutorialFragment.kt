package vn.asiantech.internship.kotlin.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.`at-vietphan`.fragment_tutorial.view.*
import vn.asiantech.internship.R
import vn.asiantech.internship.kotlin.JoinUsKotlinActivity
import vn.asiantech.internship.kotlin.models.ItemWelcome

/**
 * Check in Asian Tech Co., Ltd.
 * Created by vietphan on 05/02/2018.
 */
class TutorialFragment : Fragment(), View.OnClickListener {
    private lateinit var mItemWelcome: ItemWelcome

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tutorial, container, false).apply {
            imgHeader.setBackgroundResource(mItemWelcome.image)
            tvName.text = mItemWelcome.name
            tvContent.text = mItemWelcome.content
            lnTutorial.setBackgroundResource(mItemWelcome.color)
            if (mItemWelcome.color == R.color.colorItemWelcome_2) {
                tvSubmit.visibility = View.VISIBLE
                btnNext.visibility = View.GONE
                tvSubmit.setOnClickListener(this@TutorialFragment)
            }
        }
    }

    fun setTutorialFragment(itemWelcome: ItemWelcome) {
        mItemWelcome = itemWelcome
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(context, JoinUsKotlinActivity::class.java))
    }
}
