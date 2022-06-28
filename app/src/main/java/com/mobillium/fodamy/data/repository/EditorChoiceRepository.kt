package com.mobillium.fodamy.data.repository

import com.mobillium.fodamy.core.base.BaseRepository
import com.mobillium.fodamy.data.network.editorchoices.EditorChoicesService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditorChoiceRepository @Inject constructor(
    private val service: EditorChoicesService
) : BaseRepository() {

    suspend fun getEditorChoices(page: Int) = execute {
        val response = service.getEditorChoices(page)
        response
    }
}