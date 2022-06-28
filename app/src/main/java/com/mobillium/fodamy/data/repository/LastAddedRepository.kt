package com.mobillium.fodamy.data.repository

import com.mobillium.fodamy.core.base.BaseRepository
import com.mobillium.fodamy.data.network.lastadded.LastAddedService
import javax.inject.Inject

class LastAddedRepository @Inject constructor(
    private val service: LastAddedService
) : BaseRepository() {

    suspend fun getLastAdded(page: Int) = execute {
        val response = service.getLastAdded(page)
        response
    }
}