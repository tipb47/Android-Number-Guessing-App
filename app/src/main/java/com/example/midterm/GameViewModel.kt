package com.example.midterm

import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameViewModel (val dao: ScoreDao) : ViewModel() {
    var randomNumber: Int = (1..100).random()
    var playerNameInput: String = ""

    //game status
    private val _isGameOver = MutableLiveData<Boolean>()
    val isGameOver: LiveData<Boolean>
        get() = _isGameOver

    //attempts
    private val _attempts = MutableLiveData<Int>()
    val attempts: LiveData<Int>
        get() = _attempts

    init {
        _attempts.value = 0
    }

    //helper function to increment attempts
    fun incrementAttempts() {
        val currentAttempts = _attempts.value ?: 0
        _attempts.value = currentAttempts + 1
    }

    //toast messages to pass to main frag
    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?>
        get() = _toastMessage

    init {
        _toastMessage.value = null
    }

    // set toast message
    fun setToastMessage(message: String) {
        _toastMessage.postValue(message)
    }

    //clear after
    fun clearToastMessage() {
        _toastMessage.value = null
    }

    //toggle incorrect sound variable
    private val _incorrectSound = MutableLiveData<Boolean?>()
    val incorrectSound: LiveData<Boolean?>
        get() = _incorrectSound

    fun incorrectSound() {
        _incorrectSound.value = true
    }
    fun resetIncorrectSound() {
        _incorrectSound.value = false
    }

    //main game logic
    fun submitGuess(guess: String) {
        viewModelScope.launch {
            if (guess.isNotEmpty()) {

                val guessedNumber = guess.toInt() //convert to integer

                incrementAttempts() // increment attempt (valid attempt found)

                when {
                    guessedNumber == randomNumber -> {
                        // correct number
                        setToastMessage("Congratulations! You guessed it right!")

                        //handle blank playername
                        if (playerNameInput.isEmpty()) {playerNameInput = "Unnamed"}

                        //insert into room library database
                        dao.insert(Score(playerName = playerNameInput, playerScore = _attempts.value.toString()))

                        //navigate
                        _isGameOver.value = true
                    }

                    guessedNumber < randomNumber -> {
                        //too low
                        incorrectSound()
                        setToastMessage("Your guess is too low. Try a higher number.")
                    }

                    else -> {
                        // too high
                        incorrectSound()
                        setToastMessage("Your guess is too high. Try a lower number.")
                    }
                }
            } else {
                // no input
                setToastMessage("Please enter a guess.")
            }
        }
    }
}