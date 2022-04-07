package com.mobillium.fodamy.data.responses.likes
import com.google.gson.annotations.SerializedName
import com.mobillium.fodamy.data.responses.editorchoices.Pagination

data class CategoriesResponse(

    @SerializedName("data")
    val categoryList: ArrayList<Category>,
    val pagination: Pagination
)