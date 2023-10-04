package com.example.flashcardapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcard.R

class LoginActivity : AppCompatActivity() {

    private val correctUsername = "Sharon"
    private val correctPassword = "Sharon"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            if (usernameEditText.text.toString() == correctUsername &&
                passwordEditText.text.toString() == correctPassword) {
                val intent = Intent(this, FlashCardActivity::class.java)
                intent.putExtra("username", correctUsername)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incorrect username or password!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

