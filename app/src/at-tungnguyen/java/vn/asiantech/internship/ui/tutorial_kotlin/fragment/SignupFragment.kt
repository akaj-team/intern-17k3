package vn.asiantech.internship.ui.tutorial_kotlin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.asiantech.internship.R


/**
 * A simple [Fragment] subclass.
 */
class SignupFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_signup, container, false)
    }

}// Required empty public constructor
