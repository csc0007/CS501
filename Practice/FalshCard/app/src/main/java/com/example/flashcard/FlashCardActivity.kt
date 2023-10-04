package com.example.flashcardapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcard.R
import java.util.*

class FlashCardActivity : AppCompatActivity() {

    private var problems = mutableListOf<String>()
    private var answers = mutableListOf<Int>()

    private lateinit var problemTextView: TextView
    private lateinit var answerEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        problemTextView = findViewById(R.id.problemTextView)
        answerEditText = findViewById(R.id.answerEditText)

        val generateButton: Button = findViewById(R.id.generateButton)
        val submitButton: Button = findViewById(R.id.submitButton)

        // Display welcome toast
        val username = intent.getStringExtra("username") ?: "User"
        Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()

        generateButton.setOnClickListener {
            generateProblems()
            displayProblem()
            generateButton.isEnabled = false
        }

        submitButton.setOnClickListener {
            val userAnswers = answerEditText.text.toString().split(",")
            var correctCount = 0
            for (i in userAnswers.indices) {
                if (userAnswers[i].toInt() == answers[i]) correctCount++
            }
            Toast.makeText(this, "$correctCount out of 10", Toast.LENGTH_SHORT).show()
            generateButton.isEnabled = true
        }
    }

    private fun generateProblems() {
        problems.clear()
        answers.clear()
        val rand = Random()

        for (i in 1..10) {
            val isAddition = rand.nextBoolean()
            val operand1 = 1 + rand.nextInt(99)
            val operand2 = 1 + rand.nextInt(20)

            if (isAddition) {
                problems.add("$operand1 + $operand2")
                answers.add(operand1 + operand2)
            } else {
                val finalOperand1 = Math.max(operand1, operand2)
                val finalOperand2 = Math.min(operand1, operand2)
                problems.add("$finalOperand1 - $finalOperand2")
                answers.add(finalOperand1 - finalOperand2)
            }
        }
    }

    private fun displayProblem() {
        problemTextView.text = problems.joinToString("\n")
    }

    // State preservation for screen rotation
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("problems", ArrayList(problems))
        outState.putIntegerArrayList("answers", ArrayList(answers))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        problems = savedInstanceState.getStringArrayList("problems")?.toMutableList() ?: mutableListOf()
        answers = savedInstanceState.getIntegerArrayList("answers")?.toMutableList() ?: mutableListOf()
        displayProblem()
    }
}
