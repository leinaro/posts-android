package com.leinaro.posts.datasources.remote

import com.leinaro.posts.repository.PostsDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

interface RemoteService {
    suspend fun getAllPostsFromJPH(): List<Posts>
    suspend fun getPostDetails(posts: Posts): PostsDetails
}

class Service(private val jphService: JPHService) : RemoteService {

    override suspend fun getAllPostsFromJPH() = jphService.getAllPosts()

    /* override suspend fun getPostDetails(posts: Posts): Flow<PostsDetails> = flowOf(
    jphService.getUser(posts.userId.toString()))
             .zip(flowOf(jphService.getComments(posts.id.toString()))) { user, list ->
                 PostsDetails(user, list)
             }
     }*/
    override suspend fun getPostDetails(posts: Posts): PostsDetails =
        withContext(Dispatchers.IO) {
            val user = async { jphService.getUser(posts.userId.toString()) }
            val comments = async { jphService.getComments(posts.id.toString()) }
            PostsDetails(user.await(), comments.await())
        }


}
