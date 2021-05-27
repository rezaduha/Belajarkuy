package com.belajarkuy.app.data.network

import com.belajarkuy.app.data.model.*
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/auth")
    suspend fun login(@Body authRequest: AuthRequest): AuthRequest

    @GET("/progress/{user_id}")
    suspend fun getProgress(@Path("user_id") id: Int): ProgressResponse

    @GET("/module/list")
    suspend fun getAllModule(): ModuleResponse

    @GET("/module/{module_id}")
    suspend fun getModuleById(@Path("module_id") moduleId: Int): DetailModuleResponse

    @POST("/module/{module_id}")
    suspend fun submitAnswer(@Path("module_id") moduleId: Int, @Body moduleRequest: List<ModuleRequest>): ModuleRequest
}