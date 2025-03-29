package com.example.lr1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var scrambledWordTextView: TextView
    private lateinit var userInputEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var feedbackTextView: TextView

    private val words = listOf("android", "kotlin", "developer", "programming", "scramble", "game")
    private var availableWords = words.toMutableList()
    private var currentWord: String = ""
    private var scrambledWord: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrambledWordTextView = findViewById(R.id.scrambledWord)
        userInputEditText = findViewById(R.id.userInput)
        submitButton = findViewById(R.id.submitButton)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this, BirthdayActivity::class.java)
            startActivity(intent)
        }

        startNewGame()

        submitButton.setOnClickListener {
            val userGuess = userInputEditText.text.toString().trim()
            if (userGuess.equals(currentWord, ignoreCase = true)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                startNewGame()
            } else {
                Toast.makeText(this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startNewGame() {
        if (availableWords.isEmpty()) {
            availableWords = words.toMutableList()
        }

        currentWord = availableWords.random()

        scrambledWord = currentWord.toCharArray().apply { shuffle() }.concatToString()

        scrambledWordTextView.text = scrambledWord
        userInputEditText.text.clear()

        availableWords.remove(currentWord)
    }
}