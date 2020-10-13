package com.leinaro.posts.datasources.remote

interface RemoteService {
    suspend fun getAllPostsFromJPH(): List<Posts>
}

class Service(private val jphService: JPHService): RemoteService {

    override suspend fun getAllPostsFromJPH() = jphService.getAllPosts()

}
