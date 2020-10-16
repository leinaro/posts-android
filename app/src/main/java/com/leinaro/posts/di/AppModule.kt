package com.leinaro.posts.di

import com.leinaro.posts.datasources.local.FavoriteDao
import com.leinaro.posts.datasources.local.PostsDao
import com.leinaro.posts.datasources.remote.JPHService
import com.leinaro.posts.datasources.remote.JSONPlaceHolderClient
import com.leinaro.posts.datasources.remote.Service
import com.leinaro.posts.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    /* @Provides
     fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
         .baseUrl("https://rickandmortyapi.com/api/")
         .addConverterFactory(GsonConverterFactory.create(gson))
         .build()

     @Provides
     fun provideGson(): Gson = GsonBuilder().create()

     @Provides
     fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)
 */

    @Provides
    fun provideJphService(): JPHService = JSONPlaceHolderClient.jphService

    @Singleton
    @Provides
    fun provideService(jphService: JPHService) = Service(jphService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteService: Service, postsDao: PostsDao, favoriteDao: FavoriteDao
    ): RepositoryImpl = RepositoryImpl(remoteService, postsDao, favoriteDao)
}


/*abstract class AppModule {



    @Provides
    @Singleton
    fun provideOkHttpClient() : JPHService = JSONPlaceHolderClient.jphService

}*/