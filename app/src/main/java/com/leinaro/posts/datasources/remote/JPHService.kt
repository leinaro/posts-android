package com.leinaro.posts.datasources.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JPHService {
    @GET("posts")
    suspend fun getAllPosts(): List<Posts>

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User

    @GET("comments")
    suspend fun getComments(@Query("postId") postId: String): List<Comment>

}
