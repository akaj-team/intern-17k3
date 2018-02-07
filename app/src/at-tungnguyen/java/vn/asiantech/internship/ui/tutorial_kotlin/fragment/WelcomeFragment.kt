package vn.asiantech.internship.ui.tutorial_kotlin.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.`at-tungnguyen`.fragment_welcome.*
import vn.asiantech.internship.R
import vn.asiantech.internship.ui.tutorial_kotlin.Tutorial


/**
 * A simple [Fragment] subclass.
 */
class WelcomeFragment : Fragment() {
    private var isCheckButton: Boolean = false

    companion object {
        fun newInstance(page: Tutorial): WelcomeFragment {
            val bundle = Bundle()
            bundle.putSerializable("page", page)
            return WelcomeFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initChoiceFragment(arguments!!.getSerializable("page") as Tutorial)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initChoiceFragment(typeItem: Tutorial) {
        when (typeItem) {
            Tutorial.WELCOME -> {
                getFragmentBackground(typeItem)
                getFragmentImage(typeItem)
                getFragmentTitle(typeItem)
                getFragmentDesription(typeItem)
                getFragmentBtn(typeItem)
            }
            Tutorial.JOINUP -> {
                getFragmentBackground(typeItem)
                getFragmentImage(typeItem)
                getFragmentTitle(typeItem)
                getFragmentDesription(typeItem)
                getFragmentBtn(typeItem)
            }
            Tutorial.SIGNUP -> {
                getFragmentBackground(typeItem)
                getFragmentImage(typeItem)
                getFragmentTitle(typeItem)
                getFragmentDesription(typeItem)
                getFragmentBtn(typeItem)
            }
        }
    }

    private fun getFragmentBackground(typeItem: Tutorial) {
        when (typeItem) {
            Tutorial.WELCOME -> {
                llFragment.setBackgroundColor(resources.getColor(R.color.color_welcome))
            }
            Tutorial.SIGNUP -> {
                llFragment.setBackgroundColor(resources.getColor(R.color.color_singup))
            }
            Tutorial.JOINUP -> {
                llFragment.setBackgroundColor(resources.getColor(R.color.color_beunique))
            }
        }
    }

    private fun getFragmentImage(typeItem: Tutorial) {
        when (typeItem) {
            Tutorial.WELCOME -> {
                Glide.with(this).load(R.drawable.ic_girl).into(imgGirl)
            }
            Tutorial.SIGNUP -> {
                Glide.with(this).load(R.drawable.ic_girl2).into(imgGirl)
            }
            Tutorial.JOINUP -> {
                Glide.with(this).load(R.drawable.ic_girl3).into(imgGirl)
            }
        }
    }

    private fun getFragmentTitle(typeItem: Tutorial) {
        when (typeItem) {
            Tutorial.WELCOME -> {
                tvTitle.text = getString(R.string.tv_welcome)
            }
            Tutorial.SIGNUP -> {
                tvTitle.text = getString(R.string.tv_title_unique)
            }
            Tutorial.JOINUP -> {
                tvTitle.text = getString(R.string.tv_started)
            }
        }
    }

    private fun getFragmentDesription(typeItem: Tutorial) {
        when (typeItem) {
            Tutorial.WELCOME -> {
                tvDesTutorial.text = getString(R.string.tv_des_welcome)
            }
            Tutorial.SIGNUP -> {
                tvDesTutorial.text = getString(R.string.tv_des_signup)
            }
            Tutorial.JOINUP -> {
                tvDesTutorial.text = getString(R.string.tv_des_joinup)
            }
        }
    }

    private fun getFragmentBtn(typeItem: Tutorial) {
        when (typeItem) {
            Tutorial.WELCOME -> {
                imgNext.setImageResource(R.drawable.ic_next_button)
                imgNext.visibility = View.VISIBLE
            }
            Tutorial.SIGNUP -> {
                imgNext.visibility = View.VISIBLE
                imgNext.setImageResource(R.drawable.ic_next_button)
            }
            Tutorial.JOINUP -> {
                btnSubmit.background = resources.getDrawable(R.drawable.bg_shadow_button)
                btnSubmit.text = getString(R.string.tv_btn_joinup)
                btnSubmit.visibility = View.VISIBLE
            }
        }
    }
}
