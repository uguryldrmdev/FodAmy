package com.mobillium.fodamy.core.base

import com.mobillium.fodamy.data.network.Resource
import com.mobillium.fodamy.data.network.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {
    suspend fun logout() = safeApiCall {
        api.logout()
    }
}