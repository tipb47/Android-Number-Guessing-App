package com.example.midterm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoresViewModelFactory(private val dao: ScoreDao)
    : ViewModelProvider.Factory {

    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {

        if (modelClass.isAssignableFrom(ScoresViewModel::class.java)) {
            return ScoresViewModel(dao) as VM
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}