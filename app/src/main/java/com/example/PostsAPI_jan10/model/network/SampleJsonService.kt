package com.example.PostsAPI_jan10.model.network

import com.example.PostsAPI_jan10.model.network.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface SampleJsonService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}