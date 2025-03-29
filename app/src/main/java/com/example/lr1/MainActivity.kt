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

        // Initialize views
        scrambledWordTextView = findViewById(R.id.scrambledWord)
        userInputEditText = findViewById(R.id.userInput)
        submitButton = findViewById(R.id.submitButton)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            // Create an Intent to start the SecondActivity
            val intent = Intent(this, BirthdayActivity::class.java)
            startActivity(intent)  // Start the activity
        }

        // Set up the game
        startNewGame()

        // Set click listener for submit button
        submitButton.setOnClickListener {
            val userGuess = userInputEditText.text.toString().trim()
            if (userGuess.equals(currentWord, ignoreCase = true)) {
                // Show a Toast message for correct guess
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                startNewGame() // Start a new round
            } else {
                // Show a Toast message for incorrect guess
                Toast.makeText(this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Start a new game by selecting a random word and scrambling it
    private fun startNewGame() {
        // Check if availableWords is empty, refill it if necessary
        if (availableWords.isEmpty()) {
            availableWords = words.toMutableList() // Refill with the original list
        }

        // Select a random word from the availableWords list
        currentWord = availableWords.random()

        // Scramble the word
        scrambledWord = currentWord.toCharArray().apply { shuffle() }.concatToString()

        // Update UI with scrambled word
        scrambledWordTextView.text = scrambledWord
        userInputEditText.text.clear()

        // Remove the chosen word from the availableWords list
        availableWords.remove(currentWord)
    }
}