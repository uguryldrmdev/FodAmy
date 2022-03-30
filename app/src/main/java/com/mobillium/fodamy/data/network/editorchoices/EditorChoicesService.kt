package com.mobillium.fodamy.data.network.editorchoices

import com.mobillium.fodamy.data.responses.editorchoices.EditorChoicesResponse
import retrofit2.http.*

interface EditorChoicesService {

    @GET("api/editor-choices")
    suspend fun getEditorChoices(
        @Query("page") page:Int
    ): EditorChoicesResponse
}