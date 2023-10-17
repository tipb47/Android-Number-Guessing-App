package com.example.midterm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm.databinding.ScoreItemBinding

class ScoreItemAdapter (
    private val deleteClickListener: (scoreId: Long) -> Unit
) : ListAdapter<Score, ScoreItemAdapter.ScoreItemViewHolder>(ScoreDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreItemViewHolder {
        return ScoreItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ScoreItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, deleteClickListener)
    }

    class ScoreItemViewHolder(private val binding: ScoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun inflateFrom(parent: ViewGroup): ScoreItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ScoreItemBinding.inflate(layoutInflater, parent, false)
                return ScoreItemViewHolder(binding)
            }
        }

        //setup each card item
        fun bind(
            item: Score,
            deleteClickListener: (scoreId: Long) -> Unit
        ) {
            binding.score = item

            binding.deleteButton.setOnClickListener {
                deleteClickListener(item.scoreId)
            }
        }
    }
}