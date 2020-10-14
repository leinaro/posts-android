package com.leinaro.posts.repository.postsdetails

import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.repository.PostsDetails
import com.leinaro.posts.repository.Result

interface PostDetailsRepository {
    suspend fun getPostDetails(posts: Posts): Result<PostsDetails>
    suspend fun addFavorite(postId: Int)
    suspend fun removeFavorite(postId: Int)
    suspend fun getFavorite(postId: Int): Boolean
}