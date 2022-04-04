package com.mobillium.fodamy.data.responses.lastadded

import com.google.gson.annotations.SerializedName
import com.mobillium.fodamy.data.responses.editorchoices.Pagination
import com.mobillium.fodamy.data.responses.editorchoices.Recipe

data class LastAddedResponse(
    @SerializedName("data")
    val recipe: ArrayList<Recipe>,
    val pagination: Pagination
)