package com.leinaro.posts.datasources.remote

import retrofit2.http.GET

interface JPHService {
    @GET("posts")
    suspend fun getAllPosts(): List<Posts>

}
