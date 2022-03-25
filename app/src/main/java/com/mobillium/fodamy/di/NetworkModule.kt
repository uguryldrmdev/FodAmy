package com.mobillium.fodamy.di

import com.mobillium.fodamy.data.network.AuthService
import com.mobillium.fodamy.data.preferences.PreferencesManager
import com.mobillium.fodamy.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val BASE_URL = "https://fodamy.mobillium.com/"

    @Provides
    @Inject
    fun provideRetrofit(preferenceManager: PreferencesManager): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient(providesAuthInterceptor(preferenceManager)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Inject
    fun providesAuthInterceptor(preferenceManager: PreferencesManager): AuthInterceptor {
        return AuthInterceptor(preferenceManager)
    }

    private fun getRetrofitClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor).build()
    }


    @Provides
    @Inject
    fun provideAuthApi(retrofit: Retrofit, preferenceManager: PreferencesManager): AuthRepository {
        return AuthRepository(retrofit.create(AuthService::class.java), preferenceManager)
    }
}


class AuthInterceptor constructor(private val preferenceManager: PreferencesManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder()
        req.addHeader("X-Fodamy-Token", preferenceManager.token)
        return chain.proceed(req.build())
    }
}