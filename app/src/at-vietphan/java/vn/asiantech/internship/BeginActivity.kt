package vn.asiantech.internship

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.`at-vietphan`.activity_begin.*
import vn.asiantech.internship.kotlin.TutorialActivity

class BeginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begin)
        setListeners()
    }

    private fun setListeners() {
        btnKotlinTutorial.setOnClickListener(this)
        btnJavaTutorial.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btnKotlinTutorial) {
            startActivity(Intent(this, TutorialActivity::class.java))
        } else {
            startActivity(Intent(this, ExerciseActivity::class.java))
        }
    }
}
