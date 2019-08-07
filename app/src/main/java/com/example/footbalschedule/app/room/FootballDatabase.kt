package com.example.footbalschedule.app.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.footbalschedule.model.Match
import com.example.footbalschedule.model.Team

@Database(entities = [Match::class, Team::class], version = 1)
abstract class FootballDatabase : RoomDatabase()
{
    abstract fun matchDao(): Match.MatchDao
//    abstract fun eventDetailDao(): EventDetail.EventDao

    abstract fun teamDao(): Team.TeamDao
//    abstract fun teamDetailDao(): Team.TeamDao

    companion object
    {

        @Volatile
        private var instance: FootballDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                FootballDatabase::class.java,
                "db_football"
            ).build().also {
                instance = it
            }
        }

    }
}