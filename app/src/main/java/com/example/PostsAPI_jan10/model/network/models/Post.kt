package com.example.PostsAPI_jan10.model.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)