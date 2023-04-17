package com.biniyam.frontendexmple

import com.google.gson.JsonObject
import retrofit2.Call  // right call OBS!
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET ("user")
    fun getUserList(): Call<List<UserModel>>

    @GET ("user/{id}")
    suspend fun getUserById( @Path("id") id:Int): UserModel

    @POST("user")
    suspend fun addUser(@Body newUser:UserModel):Response<JsonObject>

    @FormUrlEncoded  @PUT ("user/{id}")
    suspend fun updateUser(@Path("id") id:Int?,
                           @Field("name")name:String,
                           @Field("score")score:Int,
                           @Field("is_human")isHuman:Boolean)
    : Response<JsonObject>

    @DELETE("user/{id}")
    suspend fun deletUser(@Path("id")id:Int ):Response<JsonObject>

}