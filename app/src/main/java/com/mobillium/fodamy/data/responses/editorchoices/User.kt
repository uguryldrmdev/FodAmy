package com.mobillium.fodamy.data.responses.editorchoices

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("cover")
    val cover: String,
    @SerializedName("definition")
    val definition: String,
    @SerializedName("facebook_url")
    val facebookUrl: String,
    @SerializedName("favorites_count")
    val favoritesCount: Int,
    @SerializedName("followed_count")
    val followedCount: Int,
    @SerializedName("following_count")
    val followingCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: ImageX?,
    @SerializedName("instagram_url")
    val instagramUrl: String,
    @SerializedName("is_following")
    val isFollowing: Boolean,
    @SerializedName("is_top_user_choice")
    val isTopUserChoice: Boolean,
    @SerializedName("is_trusted")
    val isTrusted: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("recipe_count")
    val recipeCount: Int,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("twitter_url")
    val twitterUrl: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("youtube_url")
    val youtubeUrl: String
)