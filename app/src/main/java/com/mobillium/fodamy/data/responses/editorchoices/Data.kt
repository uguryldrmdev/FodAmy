package com.mobillium.fodamy.data.responses.editorchoices

data class Data(
    val category: Category,
    val comment_count: Int,
    val definition: String,
    val difference: String,
    val directions: String,
    val id: Int,
    val images: List<ImageXX>,
    val ingredients: String,
    val is_approved: Boolean,
    val is_editor_choice: Boolean,
    val is_owner: Boolean,
    val language: String,
    val like_count: Int,
    val number_of_favorite_count: Int,
    val number_of_person: NumberOfPerson,
    val time_of_recipe: TimeOfRecipe,
    val title: String,
    val user: User,
    val youtube_image: Any,
    val youtube_url: String
)