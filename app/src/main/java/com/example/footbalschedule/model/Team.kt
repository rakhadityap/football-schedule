package com.example.footbalschedule.model

import androidx.room.*

@Entity(tableName = "tb_team")
data class Team(
    @PrimaryKey
    val idTeam: String,
    val strTeam: String?,
    val strLeague: String?,
    val strAlternate: String?,
    val strFormedYear: String?,
    val strStadium: String?,
    val strStadiumThumb: String?,
    val strStadiumLocation: String?,
    val strCountry: String?,
    val strTeamBadge: String?,
    val strTeamJersey: String?,
    val strTeamBanner: String?,
    val strDescriptionEN: String?
){

    @Dao
    interface TeamDao{
        @Query("SELECT * FROM tb_team")
        suspend fun getAll(): List<Team>

        @Query("SELECT * FROM tb_team WHERE idTeam = :idTeam")
        suspend fun getDetail(idTeam: String): Team

        @Query("SELECT count(*) FROM tb_team WHERE idTeam = :idTeam")
        suspend fun checkTeam(idTeam: String): Int

        @Insert
        suspend fun insertTeam(team: Team)

        @Delete
        suspend fun removeTeam(team: Team)
    }
}
