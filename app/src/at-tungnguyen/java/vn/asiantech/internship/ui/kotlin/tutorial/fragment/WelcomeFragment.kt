package vn.asiantech.internship.ui.kotlin.tutorial.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.`at-tungnguyen`.fragment_welcome.*
import vn.asiantech.internship.R
import vn.asiantech.internship.ui.kotlin.login.JoinUsKotlinActivity
import vn.asiantech.internship.ui.kotlin.login.SignInKotlinActivity
import vn.asiantech.internship.ui.kotlin.tutorial.TutorialEnum


/**
 * A simple [Fragment] subclass.
 */
class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance(page: TutorialEnum): WelcomeFragment {
            val bundle = Bundle()
            bundle.putSerializable("page", page)
            return WelcomeFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    private fun initClick() {
        btnSubmit.setOnClickListener({
            startActivity(Intent(activity, JoinUsKotlinActivity::class.java))
        })
        tvSignin.setOnClickListener({
            startActivity(Intent(activity, SignInKotlinActivity::class.java))

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initChoiceFragment(arguments!!.getSerializable("page") as TutorialEnum)
        initClick()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initChoiceFragment(typeItem: TutorialEnum) {
        when (typeItem) {
            TutorialEnum.WELCOME -> {
                getFragmentBackground(typeItem)
                getFragmentImage(typeItem)
                getFragmentTitle(typeItem)
                getFragmentDescription(typeItem)
                getFragmentBtn(typeItem)
            }
            TutorialEnum.JOINUP -> {
                getFragmentBackground(typeItem)
                getFragmentImage(typeItem)
                getFragmentTitle(typeItem)
                getFragmentDescription(typeItem)
                getFragmentBtn(typeItem)
            }
            TutorialEnum.SIGNUP -> {
                getFragmentBackground(typeItem)
                getFragmentImage(typeItem)
                getFragmentTitle(typeItem)
                getFragmentDescription(typeItem)
                getFragmentBtn(typeItem)
            }
        }
    }

    private fun getFragmentBackground(typeItem: TutorialEnum) {
        activity?.let {
            when (typeItem) {
                TutorialEnum.WELCOME -> {
                    llFragment.setBackgroundColor(ContextCompat.getColor(it, R.color.color_welcome))
                }
                TutorialEnum.SIGNUP -> {
                    llFragment.setBackgroundColor(ContextCompat.getColor(it, R.color.color_singup))
                }
                TutorialEnum.JOINUP -> {
                    llFragment.setBackgroundColor(ContextCompat.getColor(it, R.color.color_beunique))
                }
            }
        }

    }

    private fun getFragmentImage(typeItem: TutorialEnum) {
        when (typeItem) {
            TutorialEnum.WELCOME -> {
                Glide.with(this).load(R.drawable.ic_girl).into(imgGirl)
            }
            TutorialEnum.SIGNUP -> {
                Glide.with(this).load(R.drawable.ic_girl2).into(imgGirl)
            }
            TutorialEnum.JOINUP -> {
                Glide.with(this).load(R.drawable.ic_girl3).into(imgGirl)
            }
        }
    }

    private fun getFragmentTitle(typeItem: TutorialEnum) {
        when (typeItem) {
            TutorialEnum.WELCOME -> {
                tvTitle.text = getString(R.string.tv_welcome)
            }
            TutorialEnum.SIGNUP -> {
                tvTitle.text = getString(R.string.tv_title_unique)
            }
            TutorialEnum.JOINUP -> {
                tvTitle.text = getString(R.string.tv_started)
            }
        }
    }
    private fun getFragmentDescription(typeItem: TutorialEnum) {
        when (typeItem) {
            TutorialEnum.WELCOME -> {
                tvDesTutorial.text = getString(R.string.tv_des_welcome)
            }
            TutorialEnum.SIGNUP -> {
                tvDesTutorial.text = getString(R.string.tv_des_signup)
            }
            TutorialEnum.JOINUP -> {
                tvDesTutorial.text = getString(R.string.tv_des_joinup)
            }
        }
    }

    private fun getFragmentBtn(typeItem: TutorialEnum) {
        activity?.let {
            when (typeItem) {
                TutorialEnum.WELCOME -> {
                    imgNext.setImageResource(R.drawable.ic_next_button)
                    imgNext.visibility = View.VISIBLE
                }
                TutorialEnum.SIGNUP -> {
                    imgNext.visibility = View.VISIBLE
                    imgNext.setImageResource(R.drawable.ic_next_button)
                }
                TutorialEnum.JOINUP -> {
                    btnSubmit.background = ContextCompat.getDrawable(it, R.drawable.bg_shadow_button)
                    btnSubmit.text = getString(R.string.tv_btn_joinup)
                    btnSubmit.visibility = View.VISIBLE
                }
            }
        }
    }
}
