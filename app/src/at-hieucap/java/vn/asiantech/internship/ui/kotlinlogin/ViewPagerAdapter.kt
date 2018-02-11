package vn.asiantech.internship.ui.kotlinlogin

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import vn.asiantech.internship.models.CardLogin

/**
 * Created by tiboo on 05/02/2018.
 * Create fragment state pager adapter
 */
class gitViewPagerAdapter(val fragmentManager: FragmentManager, private val listCardLogin: ArrayList<CardLogin>) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): StepLoginFragment {
        return StepLoginFragment.newInstance(listCardLogin[position])
    }

    override fun getCount(): Int {
        return listCardLogin.size
    }
}
