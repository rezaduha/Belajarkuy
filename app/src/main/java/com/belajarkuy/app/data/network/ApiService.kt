package com.belajarkuy.app.data.network

import com.belajarkuy.app.data.model.*
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("auth")
    suspend fun login(@Body authRequest: AuthRequest): AuthRequest

    @GET("competency/{user_id}")
    suspend fun getCompetency(@Path("user_id") userId: String): CompetencyResponse

    @GET("module/list")
    suspend fun getAllModule(): ModuleResponse

    @GET("module/{module_id}")
    suspend fun getModuleById(@Path("module_id") moduleId: Int): DetailModuleResponse

    @GET("module/{user_id}/{subject}")
    suspend fun getRecommendationBySubject(
        @Path("user_id") userId: Int,
        @Path("subject") subject: String
    ): DetailModuleResponse

    @POST("module/{user_id}")
    suspend fun submitAnswer(
        @Path("user_id") userId: String,
        @Body moduleRequest: List<ModuleRequest>
    ): ModuleRequest

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "id",
        @Query("category") category: String = "science",
        @Query("apiKey") apiKey: String = "4b7abda9e69f41579a797be412b6fd7e",
    ): NewsResponse
}