package com.leinaro.posts.datasources.local

import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAllFavoriteIds(): List<Favorite>

    @Query("SELECT * FROM favorite WHERE postId = :postId")
    fun getFavoriteByIds(postId: Int): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE postId = :postId")
    fun deleteByPostId(postId: Int)

}