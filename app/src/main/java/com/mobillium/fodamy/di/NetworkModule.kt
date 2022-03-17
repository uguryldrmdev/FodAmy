package com.mobillium.fodamy.di

import android.app.Application
import android.content.Context
import com.mobillium.fodamy.data.network.AuthService
import com.mobillium.fodamy.data.preferences.MyPreferences
import com.mobillium.fodamy.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val BASE_URL = "https://fodamy.mobillium.com/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                   /* if (token != null) {
                        it.addHeader("X-Fodamy-Token", token)
                    }*/
                }.build())
            }.also { client ->
                if (androidx.viewbinding.BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }


    @Provides
    @Inject
    fun provideAuthApi(retrofit: Retrofit): AuthRepository{
        return AuthRepository(retrofit.create(AuthService::class.java))
    }
}