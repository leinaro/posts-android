package com.leinaro.posts.datasources.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey val postId: Int
)