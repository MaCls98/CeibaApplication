package com.celis.ceibaapplication.di

import com.celis.ceibaapplication.persistence.network.NetworkConstants
import com.celis.ceibaapplication.persistence.network.services.UserService
import com.celis.ceibaapplication.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkConstants.BASE_URL)
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BASIC
                        }
                    )
                    .build()
            )
            .build()

    @Singleton
    @Provides
    fun providesUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun providesUserRepository(userService: UserService) = UserRepository(userService)

}