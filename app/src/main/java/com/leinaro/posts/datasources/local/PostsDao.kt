package com.leinaro.posts.datasources.local

import androidx.room.*
import com.leinaro.posts.datasources.remote.Posts

@Dao
interface PostsDao {

    @Query("SELECT * FROM post")
    fun getAllPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts : List<Post>)
}
