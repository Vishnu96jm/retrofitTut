package com.jmv.retrofittut.model

import com.google.gson.annotations.SerializedName


data class Comment (
    val postId : Int = 0,
    val id : Int = 0,
    val name: String? = null,
    val email: String? = null,
    @SerializedName("body")
    val text: String? = null
)