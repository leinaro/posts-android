package com.leinaro.posts.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leinaro.posts.datasources.remote.JSONPlaceHolderClient
import com.leinaro.posts.datasources.remote.Posts
import com.leinaro.posts.datasources.remote.Service
import com.leinaro.posts.repository.RepositoryImpl
import com.leinaro.posts.repository.Result
import com.leinaro.posts.repository.main.MainViewDataState
import com.leinaro.posts.repository.main.PostRepository
import com.leinaro.posts.repository.main.ShowAllPosts
import com.leinaro.posts.ui.main.handler.ShowAllPostsHandler
import com.leinaro.posts.utils.ViewDataState
import com.leinaro.posts.utils.ViewHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val mainViewDataState = MutableLiveData<ViewDataState<MainViewDataState>>()
    private val repository: PostRepository = RepositoryImpl(Service(JSONPlaceHolderClient().jphService))

    init {
        getAllPost()
    }

    fun getViewData() : LiveData<ViewDataState<MainViewDataState>> = mainViewDataState

    private fun getAllPost(){
        viewModelScope.launch {
            when(val result = repository.getAllPost()){
                is Result.Success ->{
                    showPosts(result.data)
                }
                else -> {}
            }
        }
    }

    private fun showPosts(result: List<Posts>) {
        mainViewDataState.postValue(ViewDataState(
            ShowAllPosts(result),
            ShowAllPostsHandler
        ))
    }

}
