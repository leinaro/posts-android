package com.leinaro.posts.repository

import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.datasources.remote.RemoteService
import com.leinaro.posts.repository.main.PostRepository

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception, val message: String) : Result<Nothing>()
}

class RepositoryImpl(val remoteService: RemoteService) : PostRepository {
    override suspend fun getAllPost() : Result<List<Posts>> {
        return try {
            Result.Success(data = remoteService.getAllPostsFromJPH())
        } catch (exception: Exception) {
            Result.Error(exception = exception, message = exception.message ?: "Error getting all posts!")
        }
    }
}