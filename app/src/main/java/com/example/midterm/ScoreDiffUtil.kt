package com.example.midterm

import androidx.recyclerview.widget.DiffUtil

class ScoreDiffUtil : DiffUtil.ItemCallback<Score>() {
    override fun areItemsTheSame(oldItem: Score, newItem: Score) //update recycler, new/deleted notes.
            = (oldItem.scoreId == newItem.scoreId)

    override fun areContentsTheSame(oldItem: Score, newItem: Score) //no change to recycler.
            = (oldItem == newItem)
}