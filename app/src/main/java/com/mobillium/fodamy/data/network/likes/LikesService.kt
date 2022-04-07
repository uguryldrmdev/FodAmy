package com.mobillium.fodamy.data.network.likes

import com.mobillium.fodamy.data.responses.likes.CategoriesResponse
import retrofit2.http.GET

interface LikesService {
    @GET("api/category-recipes")
    suspend fun getLikes(): CategoriesResponse
}