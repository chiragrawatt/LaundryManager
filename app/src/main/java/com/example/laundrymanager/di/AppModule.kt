package com.example.laundrymanager.di

import android.content.Context
import android.util.Log
import com.example.laundrymanager.Repository.APIRepository
import com.example.laundrymanager.Repository.DataStoreRepository
import com.example.laundrymanager.Services.LaundryService
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
    private const val URL = "https://a90f-49-43-154-92.in.ngrok.io"

    @Provides
    @Singleton
    fun provideLaundryServiceInterface() : LaundryService {
        return Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LaundryService::class.java)
    }

    @Provides
    @Singleton
    fun provideLaundryAPIRepository(api: LaundryService) : APIRepository {
        return APIRepository(api)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context) : DataStoreRepository {
        return DataStoreRepository(context)
    }
}