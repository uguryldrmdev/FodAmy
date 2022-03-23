package com.mobillium.fodamy.core.base

import com.mobillium.fodamy.data.network.Resource
import retrofit2.HttpException

abstract class BaseRepository {

    /**
     * docs
     */
    suspend fun <T> execute(request: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(request.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                }
                else -> {
                    Resource.Failure(true, null, null)
                }
            }
        }
    }
}