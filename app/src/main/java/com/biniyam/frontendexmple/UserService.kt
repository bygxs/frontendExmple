package com.biniyam.frontendexmple

import com.google.gson.JsonObject
import retrofit2.Call  // right call OBS!
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET ("user")
    fun getUserList(): Call<List<UserModel>>

    @GET ("user/{id}")
    suspend fun getUserById( @Path("id") id:Int): UserModel

    @POST("user")
    suspend fun addUser(@Body newUser:UserModel):Response<JsonObject>
}