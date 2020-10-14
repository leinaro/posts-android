package com.leinaro.posts.repository.postsdetails

import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.repository.PostsDetails

sealed class PostsDetailsViewDataState
data class ShowPostBody(val post: Posts) : PostsDetailsViewDataState()
data class ShowPostDetails(val data: PostsDetails) : PostsDetailsViewDataState()
data class ShowFavorite(val isFavorite: Boolean) : PostsDetailsViewDataState()
