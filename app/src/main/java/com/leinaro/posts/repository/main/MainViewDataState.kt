package com.leinaro.posts.repository.main

import com.leinaro.posts.datasources.remote.Posts

sealed class MainViewDataState
data class ShowAllPosts(val posts: List<Posts>) : MainViewDataState()
