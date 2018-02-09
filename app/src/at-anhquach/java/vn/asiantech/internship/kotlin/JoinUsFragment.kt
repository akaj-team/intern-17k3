package vn.asiantech.internship.kotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.`at-anhquach`.fragment_join_us.*
import kotlinx.android.synthetic.`at-anhquach`.fragment_join_us.view.*
import vn.asiantech.internship.R
import java.util.regex.Pattern

/**
 *
 * Created by anh.quach on 2/6/18.
 */
class JoinUsFragment : Fragment(), View.OnTouchListener {
    private lateinit var activeNext: ActiveNext

    companion object {
        fun newInstance(): Fragment {
            return JoinUsFragment()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_UP) {
            hideSoftKeyboard(activity)
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_join_us, container, false)
        view.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkFullInformation()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //No-opp
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //No-opp
            }
        })
        view.edtName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkFullInformation()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //No-opp
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //No-op
            }
        })
        view.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkFullInformation()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //No-opp
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //No-opp
            }
        })
        if (view.llSignIn !is EditText) {
            view.llSignIn.setOnTouchListener(this)
        }
        return view
    }

    private fun checkFullInformation() {
        if (!isEmailValid(edtEmail.text.toString()) || TextUtils.isEmpty(edtName.text) || TextUtils.isEmpty(edtPassword.text)) {
            activeNext.onActiveNext(false)
        } else {
            activeNext.onActiveNext(true)
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

    private fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activeNext = activity as ActiveNext
    }

    interface ActiveNext {
        fun onActiveNext(isFullInfo: Boolean)
    }
}