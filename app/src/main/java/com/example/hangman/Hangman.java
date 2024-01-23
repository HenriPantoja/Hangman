package com.example.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Hangman {

    public enum GameState {
        IN_PROGRESS,
        WON,
        LOST
    }
    // metodo para saber el estado del juego
    static public GameState getGameState() {
        if (hasWon()) {
            return GameState.WON;
        } else if (errorCount >= Words.imageError.length) {
            return GameState.LOST;
        }
        return GameState.IN_PROGRESS;
    }

    static private List<String> letters;
    static public String currentWord;
    static public int errorCount;

    static public void GetRandomWord()
    {
        Random random = new Random();
        int indexRand = random.nextInt(Words.wordList.length);
        GetWordByIndex(indexRand);
    }
    static public void GetWordByIndex(int index)
    {
        letters = new ArrayList<>();
        currentWord = Words.wordList[index];
    }
    static public String GetWordInGame()
    {
        String finalWord = "";
        for(int i =0;i<currentWord.length(); i++)
        {
            String lowerLetter = String.valueOf(currentWord.charAt(i)).toLowerCase();
            //comprobar tilde y sobrescribir el lower por la del diccionario
            //lowerLetter = CheckSpainDictionary(lowerLetter);
            boolean hasLetter = letters.contains(lowerLetter);
            if(hasLetter)
            {
                finalWord += String.valueOf(currentWord.charAt(i));
            }
            else {
                finalWord += "_";
            }
        }
        return finalWord;
    }
    static public boolean AddLetter(String letter)
    {
        String lowerLetter = letter.toLowerCase();
        String lowerWord = currentWord.toLowerCase();
        if (letters.contains(lowerLetter))
        {
            return false; // indicamos que la letra ya fue utilizada en el juego
        }
        else
        {
            letters.add(letter);
            boolean hasLetter= lowerWord.contains(lowerLetter);

            if (hasLetter) {
                // Comprobar si hemos ganado
                if (hasWon()) {
                    // El jugador ha ganado
                    // mas adelante podemos incrementar una bandera guapa para avisar que has ganado
                }
            } else {
                errorCount++;
                if (errorCount >= Words.imageError.length) {
                    // El jugador ha perdido
                   // mas adelante podemos incrementar una bandera guapa para avisar que has perdido
                }
            }
            return true;
        }
    }
    static private boolean hasWon() {
        for (int i = 0; i < currentWord.length(); i++) {
            if (!letters.contains(String.valueOf(currentWord.charAt(i)).toLowerCase())) {
                return false;
            }
        }
        return true;
    }
    static public void resetGame() {
        errorCount = 0;
        letters.clear();
        GetRandomWord(); // aqui establecera una nueva palabra y reiniciará la lista de letras
    }
}