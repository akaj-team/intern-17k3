package vn.asiantech.internship.kotlin


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.`at-anhquach`.pager_tutorial.view.*
import vn.asiantech.internship.R

/**
 *
 * Created by anh.quach on 2/5/18.
 */
class TutorialFragment : Fragment() {
    private lateinit var tutorial: Tutorial

    companion object {
        fun newInstance(tutorial: Tutorial): Fragment {
            val fragment = TutorialFragment()
            val args = Bundle()
            args.putParcelable("args_resource", tutorial)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            tutorial = args.getParcelable("args_resource")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.pager_tutorial, container, false)
        view.llContentTutor.setBackgroundResource(tutorial.backgroundColors)
        view.tvTutorTittle.text = tutorial.title
        view.tvTutorContent.text = tutorial.content
        view.imgTutor.setImageResource(tutorial.image)
        view.btnJoinUs.setOnClickListener {
            val intent = Intent(activity, LogInActivity::class.java)
            intent.putExtra("fragment", 1)
            startActivity(intent)
        }
        if (tutorial.button == 0) {
            view.imgNextTutor.visibility = View.VISIBLE
            view.btnJoinUs.visibility = View.GONE
        } else {
            view.imgNextTutor.visibility = View.GONE
            view.btnJoinUs.visibility = View.VISIBLE
        }
        return view
    }
}
