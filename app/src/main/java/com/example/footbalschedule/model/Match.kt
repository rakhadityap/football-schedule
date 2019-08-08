package com.example.footbalschedule.model

import androidx.room.*

@Entity(tableName = "tb_match")
data class Match(
    @PrimaryKey
    val idEvent: String,
    val strDate: String?,
    val strTime: String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intHomeScore: String? = "0",
    val intAwayScore: String? = "0",
    val idHomeTeam: String?,
    val idAwayTeam: String?
)
{
    @Dao
    interface MatchDao
    {
        @Query("SELECT * FROM tb_match")
        suspend fun getAll(): List<Match>

        @Query("SELECT * FROM tb_match WHERE idEvent= :idEvent")
        suspend fun getDetail(idEvent: String): Match

        @Query("SELECT count(*) FROM tb_match WHERE idEvent= :idEvent")
        suspend fun checkMatch(idEvent: String): Int

        @Insert
        suspend fun insertMatch(match: Match)

        @Delete
        suspend fun removeMatch(match: Match)

        @Query("DELETE FROM tb_match WHERE idEvent = :idEvent")
        suspend fun removeMatchById(idEvent: String)
    }
}


