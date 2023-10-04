package com.example.flashcard

//import android.app.Activity
//import android.content.Context
//import android.content.Intent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcard.databinding.ActivityFlashcardBinding


class FlashcardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlashcardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateButton.setOnClickListener {
            //do something
        }
        binding.submitButton.setOnClickListener {
            //do something
        }
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, FlashcardActivity::class.java).apply {

            }
        }
    }
}