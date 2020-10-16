package com.leinaro.posts.di

import android.content.Context
import com.leinaro.posts.datasources.local.DatabaseClient
import com.leinaro.posts.datasources.local.FavoriteDao
import com.leinaro.posts.datasources.local.PostDatabase
import com.leinaro.posts.datasources.local.PostsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): PostDatabase =
        DatabaseClient(appContext).db

    @Provides
    fun providePostDao(db: PostDatabase): PostsDao = db.postDao()

    @Provides
    fun provideFavoriteDao(db: PostDatabase): FavoriteDao = db.favoriteDao()

}