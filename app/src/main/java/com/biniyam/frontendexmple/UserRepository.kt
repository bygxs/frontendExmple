package com.biniyam.frontendexmple

import android.util.Log
import com.google.gson.JsonObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class UserRepository {
    // singliton like java static    http://10.0.2.2:3000/ localhost where the emulator or hard phone exist
    companion object{

        private const val URL = "http://10.0.2.2:3000/"

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getUsers(onComplete: (ArrayList<UserModel>) -> Unit){
            var userList = ArrayList<UserModel>()
            val requestCall = retrofit.create(UserService::class.java).getUserList()

            requestCall.enqueue (object: Callback<List<UserModel>>{
                override fun onResponse(
                    call: Call<List<UserModel>>,
                    response: Response<List<UserModel>>,
                ) {
                    if (response.isSuccessful){
                         val responseList = response.body()
                        for (element in responseList!!) {
                            userList.add(element)
                        }
                        onComplete.invoke(userList)
                    } else return

                }

                override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                    Log.e("Error: ", t.message!!)
                }

            })
        }

        suspend fun getUserById(id: Int): UserModel {
            val userDao = retrofit.create(UserService::class.java)
            val user = userDao.getUserById(id)
            return user
        }

        suspend fun addUser(newUser: UserModel): Response<JsonObject>{
            val userDao = retrofit.create(UserService::class.java)
            val response = userDao.addUser(newUser)
            return  response
        }

    }
}