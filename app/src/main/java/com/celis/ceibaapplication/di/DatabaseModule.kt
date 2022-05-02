package com.celis.ceibaapplication.di

import android.content.Context
import androidx.room.Room
import com.celis.ceibaapplication.persistence.database.CeibaDatabase
import com.celis.ceibaapplication.persistence.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesUserDao(ceibaDatabase: CeibaDatabase): UserDao =
        ceibaDatabase.userDao()

    @Provides
    @Singleton
    fun providesDrinkeandoDatabase(@ApplicationContext appContext: Context): CeibaDatabase =
        Room.databaseBuilder(
            appContext,
            CeibaDatabase::class.java,
            CeibaDatabase.DATABASE_NAME
        ).build()
}