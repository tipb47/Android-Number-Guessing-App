package com.example.midterm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class Score (
    @PrimaryKey(autoGenerate = true) var scoreId: Long = 0L,
    @ColumnInfo(name = "player_name") var playerName: String = "",
    @ColumnInfo(name = "score") var playerScore: String = ""
)