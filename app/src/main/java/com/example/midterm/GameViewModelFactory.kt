package com.example.midterm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameViewModelFactory(private val dao: ScoreDao)
    : ViewModelProvider.Factory {

    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {

        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(dao) as VM
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}