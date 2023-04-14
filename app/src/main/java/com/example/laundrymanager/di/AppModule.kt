package com.example.laundrymanager.di

import android.content.Context
import android.media.session.MediaSession
import com.example.laundrymanager.Repository.APIRepository
import com.example.laundrymanager.Repository.DataStoreRepository
import com.example.laundrymanager.Repository.TokenRepository
import com.example.laundrymanager.Services.AuthInterceptor
import com.example.laundrymanager.Services.OrdersAPI
import com.example.laundrymanager.Services.TokenAPI
import com.example.laundrymanager.Services.UserAPI
import com.example.laundrymanager.Utils.Utilities.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.intellij.lang.annotations.PrintFormat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitBuilder() : Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideTokenAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient) : TokenAPI {
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create(TokenAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideUserAPI(retrofitBuilder: Retrofit.Builder) : UserAPI {
        return retrofitBuilder
            .build()
            .create(UserAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesOrdersAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient) : OrdersAPI {
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create(OrdersAPI::class.java)
    }

    @Provides
    fun provideLaundryAPIRepository(api: UserAPI) : APIRepository {
        return APIRepository(api)
    }

    @Provides
    fun provideTokenAPIRepository(api: TokenAPI) : TokenRepository {
        return TokenRepository(api)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context) : DataStoreRepository {
        return DataStoreRepository(context)
    }
}