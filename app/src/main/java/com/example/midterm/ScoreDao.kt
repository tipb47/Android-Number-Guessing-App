package com.example.midterm

import androidx.lifecycle.LiveData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDao {

    @Insert
    suspend fun insert(score: Score)

    @Update
    suspend fun update(score: Score)

    @Delete
    suspend fun delete(score: Score)

    @Query("SELECT * FROM score_table WHERE scoreId = :scoreId")
    fun get(scoreId: Long): LiveData<Score>

    @Query("SELECT * FROM score_table ORDER BY score ASC")
    fun getAllScores(): LiveData<List<Score>>

}