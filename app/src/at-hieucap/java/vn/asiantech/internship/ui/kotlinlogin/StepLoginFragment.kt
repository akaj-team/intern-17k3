package vn.asiantech.internship.ui.kotlinlogin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.`at-hieucap`.fragment_first_screen.*
import vn.asiantech.internship.R
import vn.asiantech.internship.models.CardLogin

/**
 * Created by tiboo on 05/02/2018.
 * Create step login fragment
 */
class StepLoginFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        val intent = Intent(activity, JoinUsActivity::class.java)
        startActivity(intent)
    }

    private lateinit var cardLogin: CardLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        cardLogin = bundle.getSerializable(KEY) as CardLogin
    }

    private fun addListener() {
        llAction.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_first_screen, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        addListener()
    }

    private fun initData() {
        rlContain.setBackgroundResource(cardLogin.color)
        imgAvatar.setImageResource(cardLogin.avatar)
        tvStatus.setText(cardLogin.status)
        tvSlogan.setText(cardLogin.slogan)
        if (cardLogin.background == null) {
            llAction.visibility = View.VISIBLE
            llAction.setBackgroundResource(R.drawable.ic_button_join_us)
            tvAction.setText(cardLogin.action!!)
        } else {
            imgNext.setImageResource(cardLogin.background!!)
            llAction.visibility = View.INVISIBLE
        }
    }

    companion object {
        val KEY = "key_string"
        fun newInstance(cardLogin: CardLogin): StepLoginFragment {
            val args = Bundle()
            val fragment = StepLoginFragment()
            args.putSerializable(KEY, cardLogin)
            fragment.arguments = args
            return fragment
        }
    }
}
