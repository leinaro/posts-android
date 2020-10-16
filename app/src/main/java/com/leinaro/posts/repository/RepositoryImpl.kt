package com.leinaro.posts.repository

import com.leinaro.posts.datasources.local.Favorite
import com.leinaro.posts.datasources.local.FavoriteDao
import com.leinaro.posts.datasources.local.PostsDao
import com.leinaro.posts.datasources.remote.Comment
import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.datasources.remote.RemoteService
import com.leinaro.posts.datasources.remote.User
import com.leinaro.posts.repository.main.PostRepository
import com.leinaro.posts.repository.postsdetails.PostDetailsRepository
import com.leinaro.posts.utils.toLocalPost
import com.leinaro.posts.utils.toRemotePost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception, val message: String) : Result<Nothing>()
}

typealias PostsDetails = Pair<User, List<Comment>>

class RepositoryImpl @Inject constructor(
    private val remoteService: RemoteService,
    private val postsDao: PostsDao,
    private val favoriteDao: FavoriteDao
) : PostRepository, PostDetailsRepository {

    override suspend fun getAllPost(): Result<List<Posts>> {
        return try {
            withContext(Dispatchers.IO) {
                Result.Success(data = getAllPosts())
            }
        } catch (exception: Exception) {
            Result.Error(
                exception = exception,
                message = exception.message ?: "Error getting all posts!"
            )
        }
    }

    override suspend fun getAllFavorites(): List<Favorite> {
        return withContext(Dispatchers.IO) {
            favoriteDao.getAllFavoriteIds()
        }
    }

    override suspend fun getPostDetails(posts: Posts): Result<PostsDetails> {
        return try {
            Result.Success(data = remoteService.getPostDetails(posts))
        } catch (exception: Exception) {
            Result.Error(
                exception = exception,
                message = exception.message ?: "Error getting post details!"
            )
        }
    }

    override suspend fun addFavorite(postId: Int) {
        withContext(Dispatchers.IO) {
            favoriteDao.insert(Favorite(postId))
        }
    }


    override suspend fun removeFavorite(postId: Int) {
        withContext(Dispatchers.IO) {
            favoriteDao.deleteByPostId(postId)
        }
    }

    override suspend fun getFavorite(postId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                favoriteDao.getFavoriteByIds(postId).isNotEmpty()
            } catch (e: Exception) {
                false
            }
        }
    }

    private suspend fun getAllPosts(): List<Posts> {
        return try {
            val data = remoteService.getAllPostsFromJPH()
            postsDao.insertAll(data.map {
                it.toLocalPost()
            })
            data
        } catch (e: Exception) {
            postsDao.getAllPosts().map {
                it.toRemotePost()
            }
        }
    }
}