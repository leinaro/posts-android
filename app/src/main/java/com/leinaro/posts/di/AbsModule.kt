package com.leinaro.posts.di

import com.leinaro.posts.datasources.remote.RemoteService
import com.leinaro.posts.datasources.remote.Service
import com.leinaro.posts.repository.RepositoryImpl
import com.leinaro.posts.repository.main.PostRepository
import com.leinaro.posts.repository.postsdetails.PostDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class AbsModule {
    @Binds
    abstract fun bindPostRepositoryService(
        postServiceImpl: RepositoryImpl
    ): PostRepository

    @Binds
    abstract fun bindPostDetailsRepositoryService(
        postServiceImpl: RepositoryImpl
    ): PostDetailsRepository


    @Binds
    abstract fun bindService(
        service: Service
    ): RemoteService
}