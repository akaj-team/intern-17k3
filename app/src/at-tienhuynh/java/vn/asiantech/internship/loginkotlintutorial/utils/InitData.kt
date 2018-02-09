package vn.asiantech.internship.loginkotlintutorial.utils

import vn.asiantech.internship.R
import vn.asiantech.internship.loginkotlintutorial.models.Screen

/**
 * Created by TienHuynh on 05/02/2018.
 * AsianTech Co., Ltd
 */
class InitData {
    companion object {
        fun listsTutorial(): MutableList<Screen> {
            val mListTutorials: MutableList<Screen> = mutableListOf()
            mListTutorials.add(Screen(R.color.colorItemWelcome_0, R.drawable.ic_header_avatar_1, "Welcome", "Express yourself through" + "\n" + "the art of the fashionism.", R.drawable.ic_next_tutorial))
            mListTutorials.add(Screen(R.color.colorItemWelcome_1, R.drawable.ic_header_avatar_2, "Be unique", "Your profile is" + "\n" + " your " + "\n" + " online art gallery.", R.drawable.ic_next_tutorial))
            mListTutorials.add(Screen(R.color.colorItemWelcome_2, R.drawable.ic_header_avatar_3, "Get started", "Inspire everyone with the" + "\n" + " power of your ideas.", R.drawable.bg_button_tutorial_blue))
            return mListTutorials
        }
    }
}
