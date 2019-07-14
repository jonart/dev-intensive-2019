package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))


        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)


        textTxt.text = benderObj.askQuestion()

        messageEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendAnswer()
                return@setOnEditorActionListener true
            }
            else{
                return@setOnEditorActionListener false
            }
        }

        sendBtn.setOnClickListener {
             sendAnswer()
        }
    }

    private fun isAnswerCorrect():Boolean{
        return benderObj.question.checkAnswer(messageEt.text.toString())
    }

    private fun sendAnswer(){
        if (isAnswerCorrect()){
            val (phase, color) = benderObj.listenAnswer(messageEt.text.toString())
            messageEt.setText("")
            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phase
            hideKeyboard()
        }
        else{
            val errorMessage = when(benderObj.question){
                Bender.Question.NAME -> "Имя должно начинаться с заглавной буквы"
                Bender.Question.PROFESSION -> "Профессия должна начинаться со строчной буквы"
                Bender.Question.MATERIAL -> "Материал не должен содержать цифр"
                Bender.Question.BDAY -> "Год моего рождения должен содержать только цифры"
                Bender.Question.SERIAL -> "Серийный номер содержит только цифры, и их 7"
                else -> "На этом все, вопросов больше нет"
            }
            textTxt.text = errorMessage + "\n" + benderObj.question.question
            messageEt.setText("")
        }
    }



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
    }
}
