package vn.asiantech.internship.ui.tutorialkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.`at-nhatnguyen`.item_tutorial.view.*
import vn.asiantech.internship.R
import vn.asiantech.internship.models.Tutorial

/**
 * Created by hoangnhat on 05/02/2018.
 * fragment
 */
class TutorialItemFragment : Fragment() {
    private lateinit var mTutorial: Tutorial

    companion object {
        val ARG_ID = "args_id"
        fun newInstance(tutorial: Tutorial) = TutorialItemFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_ID, tutorial)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mTutorial = arguments.getParcelable(ARG_ID)
        return inflater.inflate(R.layout.item_tutorial, container, false).apply {
            imgHeader.setImageResource(mTutorial.imageHeader)
            tvTitle.text = mTutorial.title
            tvContent.text = mTutorial.content
            if (mTutorial.color == R.color.colorItemWelcome_2) {
                imgNextTutor.visibility = View.GONE
                btnJoinUs.visibility = View.VISIBLE
                btnJoinUs.setOnClickListener({
                    startActivity(Intent(activity, JoinUsActivity::class.java))
                })
            } else {
                imgNextTutor.visibility = View.VISIBLE
                imgNextTutor.setImageResource(mTutorial.imageViewClick)
                btnJoinUs.visibility = View.GONE
            }
            llTutorial.setBackgroundColor(ContextCompat.getColor(activity, mTutorial.color))
        }
    }
}
