package com.leinaro.posts.ui.main

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leinaro.posts.datasources.local.Favorite
import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.repository.Result
import com.leinaro.posts.repository.main.MainViewDataState
import com.leinaro.posts.repository.main.PostRepository
import com.leinaro.posts.repository.main.ShowAllPosts
import com.leinaro.posts.ui.main.handler.ShowAllPostsHandler
import com.leinaro.posts.utils.ViewDataState
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    application: Application,
    var repository: PostRepository
) : AndroidViewModel(application) {

    private val mainViewDataState = MutableLiveData<ViewDataState<MainViewDataState>>()

    init {
        getAllPost()
    }

    fun getViewData(): LiveData<ViewDataState<MainViewDataState>> = mainViewDataState

    fun getAllPost() {
        viewModelScope.launch {
            when (val result = repository.getAllPost()) {
                is Result.Success -> {
                    showPosts(result.data, repository.getAllFavorites())
                }
                else -> {
                }
            }
        }
    }

    private fun showPosts(
        result: List<Posts>,
        allFavorites: List<Favorite>
    ) {
        result.map { post ->
            post.isFavorite = allFavorites.filter {
                post.id == it.postId
            }.isNotEmpty()
        }
        mainViewDataState.postValue(
            ViewDataState(
                ShowAllPosts(result),
                ShowAllPostsHandler
            )
        )
    }

    fun deleteAll() {
        mainViewDataState.postValue(
            ViewDataState(
                ShowAllPosts(listOf()),
                ShowAllPostsHandler
            )
        )
    }

}
