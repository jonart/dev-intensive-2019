package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r,g,b) = benderObj.status.color
        iv_bender.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)


        tv_text.text = benderObj.askQuestion()

        et_message.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendAnswer()
                return@setOnEditorActionListener true
            }
            else{
                return@setOnEditorActionListener false
            }
        }

        iv_send.setOnClickListener {
             sendAnswer()
        }
    }

    private fun sendAnswer(){
        val (phase, color) = benderObj.listenAnswer(et_message.text.toString().toLowerCase())
        et_message.setText("")
        val (r, g, b) = color
        iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        tv_text.text = phase
        hideKeyboard()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
    }
}
