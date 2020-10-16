package com.leinaro.posts.datasources.local

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class DatabaseClient @Inject constructor(applicationContext: Context) {

    val db = Room.databaseBuilder(
        applicationContext,
        PostDatabase::class.java,
        "post-database"
    ).build()
}