package com.leinaro.posts.ui.postsdetails

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leinaro.posts.datasources.remote.JSONPlaceHolderClient
import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.datasources.remote.Service
import com.leinaro.posts.repository.PostsDetails
import com.leinaro.posts.repository.RepositoryImpl
import com.leinaro.posts.repository.Result
import com.leinaro.posts.repository.main.PostRepository
import com.leinaro.posts.repository.postsdetails.PostsDetailsViewDataState
import com.leinaro.posts.repository.postsdetails.ShowPostBody
import com.leinaro.posts.repository.postsdetails.ShowPostDetails
import com.leinaro.posts.ui.postsdetails.handler.ShowPostBodyHandler
import com.leinaro.posts.ui.postsdetails.handler.ShowPostDestilsHandler
import com.leinaro.posts.utils.ViewDataState
import kotlinx.coroutines.launch

class PostsDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val viewDataState = MutableLiveData<ViewDataState<PostsDetailsViewDataState>>()
    private val repository: PostRepository =
        RepositoryImpl(Service(JSONPlaceHolderClient().jphService))

    fun getViewData(): LiveData<ViewDataState<PostsDetailsViewDataState>> = viewDataState
    fun setArgument(arguments: Bundle?) {
        arguments?.let {
            val post = arguments.getSerializable("posts") as Posts
            viewDataState.postValue(
                ViewDataState(
                    ShowPostBody(post),
                    ShowPostBodyHandler
                )
            )
            getPostDetails(post)
        }
    }

    private fun getPostDetails(posts: Posts) {
        viewModelScope.launch {
            when (val result = repository.getPostDetails(posts)) {
                is Result.Success -> {
                    showPostDetails(result.data)
                }
                else -> {
                }
            }
        }
    }

    private fun showPostDetails(data: PostsDetails) {
        viewDataState.postValue(
            ViewDataState(
                ShowPostDetails(data),
                ShowPostDestilsHandler
            )
        )
    }

}
