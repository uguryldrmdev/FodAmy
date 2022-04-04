package com.mobillium.fodamy.data.responses.editorchoices

import com.google.gson.annotations.SerializedName

data class EditorChoicesResponse(
    @SerializedName("data")
    val recipe: ArrayList<Recipe>,

    val pagination: Pagination
)