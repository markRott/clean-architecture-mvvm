package com.example.data.database

import android.content.Context
import androidx.room.Room

class DbInitializer(private val appContext: Context) {

    val database = initDatabase()

    private fun initDatabase(): AppDatabase {
        return Room
            .databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "database-app"
            )
            .build()
    }

}