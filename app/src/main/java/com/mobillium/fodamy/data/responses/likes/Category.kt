package com.mobillium.fodamy.data.responses.likes

import com.mobillium.fodamy.data.responses.editorchoices.Image
import com.mobillium.fodamy.data.responses.editorchoices.Recipe

data class Category(
    val id: Int,
    val image: Image,
    val language: String,
    val main_category_id: Int?,
    val name: String,
    val recipe_count: Int,
    val recipes: ArrayList<Recipe>
)