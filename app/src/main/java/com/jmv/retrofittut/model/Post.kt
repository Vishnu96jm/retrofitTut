package com.jmv.retrofittut.model
import com.google.gson.annotations.SerializedName;

data class Post(
    val userId: Int = 0,
    val id: Int? = 0,
    val title: String? = null,
    @SerializedName("body")
    val text: String? = null
    )