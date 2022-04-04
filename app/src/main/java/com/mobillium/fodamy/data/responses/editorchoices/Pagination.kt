package com.mobillium.fodamy.data.responses.editorchoices

data class Pagination(
    val current_page: Int,
    val first_item: Int,
    val last_item: Int,
    val last_page: Int,
    val per_page: Int,
    val total: Int
)