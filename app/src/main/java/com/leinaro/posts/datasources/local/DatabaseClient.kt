package com.leinaro.posts.datasources.local

import android.content.Context
import androidx.room.Room

class DatabaseClient(applicationContext: Context) {

    val db = Room.databaseBuilder(
        applicationContext,
        PostDatabase::class.java,
        "post-database"
    ).build()
}