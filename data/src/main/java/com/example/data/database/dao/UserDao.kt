package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.db.UserDb

@Dao
interface UserDao {

    @Query("SELECT * FROM UserDb")
    fun getUsers(): List<UserDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUsers(users: List<UserDb>)
}