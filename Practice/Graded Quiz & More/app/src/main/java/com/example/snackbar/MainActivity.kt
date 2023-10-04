package com.example.snackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.snackbar.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    private var score = arrayOf(0,0,0,0,0,0)
    private var total=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener{ view: View ->
            //Do something
            checkAnswer(true)
            binding.trueButton.isEnabled = false
        }
        binding.falseButton.setOnClickListener{ view: View ->
            //Do something
            checkAnswer(false)
            binding.falseButton.isEnabled = false
        }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1)
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
            if (currentIndex==6)
            {
                for (i in score)
                {
                    if (i==1) total+=1
                }
                total = (total/6)*100
                Toast.makeText(
                    this,
                    "Your Score is $total",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else
            {
                updateQuestion()
            }
        }
        binding.prevButton.setOnClickListener {
            if (currentIndex - 1 >=0){
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQuestion()
            }
            else{
                Toast.makeText(
                    this,
                    R.string.error_toast,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId: Int
        if (userAnswer == correctAnswer)
        {
            messageResId= R.string.correct_toast
            score[currentIndex] = 1
        }
        else{
            messageResId= R.string.incorrect_toast
            score[currentIndex] = 0
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }
}