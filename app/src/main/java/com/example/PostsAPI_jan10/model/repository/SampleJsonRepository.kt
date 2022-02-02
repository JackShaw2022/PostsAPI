package com.example.PostsAPI_jan10.model.repository

import com.example.PostsAPI_jan10.model.network.ApiManager
import com.example.PostsAPI_jan10.model.network.models.Post
import com.example.PostsAPI_jan10.utils.Resource

class SampleJsonRepository(
    private val apiManager: ApiManager
) {
    suspend fun getPosts(): Resource<List<Post>> {
        return try {
            val response = apiManager.getPosts()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to get Posts.")
            }
        } catch (ex: Exception) {
            Resource.Error("unexpected error")
        }
    }
}
