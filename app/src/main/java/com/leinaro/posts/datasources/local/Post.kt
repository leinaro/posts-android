package com.leinaro.posts.datasources.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    val isRead: Boolean = false
)