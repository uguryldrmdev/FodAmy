package com.mobillium.fodamy.data.network.lastadded

import com.mobillium.fodamy.data.responses.lastadded.LastAddedResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface LastAddedService{

    @GET("api/recipe/")
    suspend fun getLastAdded(
        @Query("page") page:Int
    ): LastAddedResponse
}