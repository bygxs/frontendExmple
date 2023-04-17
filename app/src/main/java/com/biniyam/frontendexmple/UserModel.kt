package com.biniyam.frontendexmple

import com.google.gson.annotations.SerializedName

data class UserModel(val id: Int?, val name: String?, val score: Int?, @SerializedName("is_human") val isHuman: Boolean)
{
    override fun toString(): String {
        return "UserModel(id=$id, name=$name, score=$score, isHuman=$isHuman)"
    }
}