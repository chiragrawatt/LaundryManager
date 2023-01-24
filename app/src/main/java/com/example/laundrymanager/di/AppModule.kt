package com.example.laundrymanager.di

import android.content.Context
import com.example.laundrymanager.Repository.APIRepository
import com.example.laundrymanager.Repository.DataStoreRepository
import com.example.laundrymanager.Services.UserAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val URL = "http://10.1.179.143:9000/"

    @Provides
    @Singleton
    fun provideLaundryServiceInterface() : UserAPI {
        return Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
    }

    @Provides
    fun provideLaundryAPIRepository(api: UserAPI) : APIRepository {
        return APIRepository(api)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context) : DataStoreRepository {
        return DataStoreRepository(context)
    }
}