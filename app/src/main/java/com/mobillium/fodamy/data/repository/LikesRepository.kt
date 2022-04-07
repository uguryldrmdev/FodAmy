package com.mobillium.fodamy.data.repository

import com.mobillium.fodamy.core.base.BaseRepository
import com.mobillium.fodamy.data.network.likes.LikesService
import javax.inject.Inject

class LikesRepository @Inject constructor(
    private val service: LikesService
) : BaseRepository() {

    suspend fun getLikes() = execute {
        val response = service.getLikes()
        response
    }
}