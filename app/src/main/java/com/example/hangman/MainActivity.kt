package com.example.hangman

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var newWord: String
    lateinit var gameWord: TextView
    lateinit var letterSelect: EditText
    lateinit var hangmanImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameWord = findViewById(R.id.word)
        letterSelect = findViewById(R.id.letter)
        hangmanImage = findViewById(R.id.hangman)

        Hangman.GetRandomWord()
        PrintWord()
    }

    fun PrintWord() {
        newWord = Hangman.GetWordInGame()
        gameWord.text = newWord
        letterSelect.text.clear()

        if (Hangman.errorCount < Words.imageError.size) {
            hangmanImage.setImageResource(Words.imageError[Hangman.errorCount])
        } else {

        }
    }

    fun CheckLetter(view: View) {
        val letter = letterSelect.text.toString()
        if (letter.isEmpty()) {
            Toast.makeText(this, "Por favor, introduce una letra", Toast.LENGTH_SHORT).show()
            return
        }

        val isLetterNew = Hangman.AddLetter(letter)
        if (!isLetterNew) {
            Toast.makeText(this, "Esta letra ya ha sido utilizada", Toast.LENGTH_SHORT).show()
            return
        }

        PrintWord()

        when (Hangman.getGameState()) {
            Hangman.GameState.WON -> startActivity(Intent(this, WinActivity::class.java))
            Hangman.GameState.LOST -> startActivity(Intent(this, LoseActivity::class.java))
            else -> { /* Juego en progreso */ }
        }
    }

}

