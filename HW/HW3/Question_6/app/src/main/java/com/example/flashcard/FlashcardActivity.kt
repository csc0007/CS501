package com.example.flashcard

//import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcard.databinding.ActivityFlashcardBinding


class FlashcardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlashcardBinding

    private val flashcardViewModel: FlashcardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateButton.setOnClickListener {
            //do something
            updateQuestion()
            binding.generateButton.isEnabled = false
            binding.submitButton.isEnabled = true
        }

        binding.submitButton.setOnClickListener {
            //do something
//            if (flashcardViewModel.currentIndex == 9) flashcardViewModel.moveToNext()
//            if (flashcardViewModel.currentIndex == 10)
//            {
//                binding.generateButton.isEnabled = true
//                binding.submitButton.isEnabled = false
//            }
//            if (flashcardViewModel.currentIndex != 10 || flashcardViewModel.currentIndex != 9)
//            {
//                flashcardViewModel.moveToNext()
//                updateQuestion()
//            }
            flashcardViewModel.moveToNext()
            updateQuestion()
        }
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, FlashcardActivity::class.java).apply {

            }
        }
    }

    private fun updateQuestion(){
        val showText : String = flashcardViewModel.setQuestion()
        binding.questionTextView.text = showText
    }
}