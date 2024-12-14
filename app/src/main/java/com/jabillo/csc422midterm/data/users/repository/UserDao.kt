package com.jabillo.csc422midterm.data.users.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jabillo.csc422midterm.data.users.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("Select * FROM User")
    suspend fun getAllUsers() : List<User>

    @Query("Select * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int) : User
}