package vn.asiantech.internship.loginkotlintutorial.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.`at-tienhuynh`.fragment_tutorial_item.view.*
import vn.asiantech.internship.R
import vn.asiantech.internship.loginkotlintutorial.utils.InitData

/**
 * A simple [Fragment] subclass.
 */
class TutorialItemFragment : Fragment() {
    private lateinit var mView: View

    companion object {
        val ARG_ID = "args_id"
        fun newInstance(id: Int): Fragment {
            val tutorialItemFragment = TutorialItemFragment()
            val args = Bundle()
            args.putInt(ARG_ID, id)
            tutorialItemFragment.arguments = args
            return tutorialItemFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_tutorial_item, container, false)
        initDataToItem(arguments.getInt(ARG_ID))
        return mView
    }

    private fun initDataToItem(positionItem: Int) {
        mView.imgHeaderAvatar.setImageResource(InitData.listsTutorial()[positionItem].imgHeaderAvatar)
        mView.tvTitle.text = InitData.listsTutorial()[positionItem].title
        mView.tvContain.text = InitData.listsTutorial()[positionItem].content
        mView.llContent.setBackgroundColor(ContextCompat.getColor(activity, InitData.listsTutorial()[positionItem].color))
        mView.btnItemTutorial.background = ContextCompat.getDrawable(activity, InitData.listsTutorial()[positionItem].imgBackGround)
        if (positionItem == 2) {
            mView.btnItemTutorial.text = getString(R.string.tv_tutorial_join_us)
            mView.btnItemTutorial.width = 600
            mView.btnItemTutorial.setOnClickListener(View.OnClickListener {
                Toast.makeText(activity, "aaa", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
