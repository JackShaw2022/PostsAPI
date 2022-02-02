package com.example.PostsAPI_jan10.model.network

class ApiManager {

    private var sampleJsonService: SampleJsonService =
        RetrofitInstance.providesRetrofit().create(SampleJsonService::class.java)

    suspend fun getPosts() = sampleJsonService.getPosts()
}