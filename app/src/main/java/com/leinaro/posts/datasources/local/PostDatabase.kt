package com.leinaro.posts.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class, Favorite::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostsDao
    abstract fun favoriteDao(): FavoriteDao
}