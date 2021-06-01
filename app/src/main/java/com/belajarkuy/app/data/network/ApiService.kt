package com.belajarkuy.app.data.network

import com.belajarkuy.app.data.model.*
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("auth")
    suspend fun login(@Body authRequest: AuthRequest): AuthRequest

//    @GET("/competency/{user_id}")
//    suspend fun getCompetency(@Path("user_id") userId: Int): CompetencyResponse
//
//    @GET("/modules")
//    suspend fun getAllModule(): ModuleResponse
//
//    @GET("/module/{module_id}")
//    suspend fun getModuleById(@Path("module_id") moduleId: Int): DetailModuleResponse
//
//    @GET("/module/{user_id}/{subject}")
//    suspend fun getRecommendationBySubject(@Path("user_id") userId: Int, @Path("subject") subject: String): DetailModuleResponse

    @POST("/module/{module_id}")
    suspend fun submitAnswer(@Path("module_id") moduleId: Int, @Body moduleRequest: List<ModuleRequest>): ModuleRequest



    // empty competency endpoint = 2176bcc5-3545-46f6-8e77-a3fb9031f7b2
    // not empty endpoint = e345a71a-e6e6-4246-b234-e9ef2b6b579b
    // just for testing =====================
    @GET("e345a71a-e6e6-4246-b234-e9ef2b6b579b")
    suspend fun getCompetency(): CompetencyResponse

    @GET("d5845f7e-b404-4b5a-a54b-88b70207c41d")
    suspend fun getAllModule(): ModuleResponse

    @GET("faa85def-6274-4a18-ad62-c380f875ec63")
    suspend fun getModuleById(): DetailModuleResponse

    @GET("/module/{user_id}/{subject}")
    suspend fun getRecommendationBySubject(@Path("user_id") userId: Int, @Path("subject") subject: String): DetailModuleResponse
}