package com.leinaro.posts.repository.main

import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.repository.Result

interface PostRepository {
    suspend fun getAllPost(): Result<List<Posts>>
}
