package com.jmv.retrofittut.networking

import com.jmv.retrofittut.model.Comment
import com.jmv.retrofittut.model.Post
import retrofit2.Call
import retrofit2.http.*


interface RemoteAPIService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @POST("posts")
    fun createPost(@Body post: Post?): Call<Post?>?

}




/*@GET("posts") // /posts?userId=1
  fun getPosts(@Query("userId") userId : Int): Call<List<Post>>*/

/*@GET("posts/2/comments")
fun getComments() : Call<List<Comment>>*/

/*@GET("posts/{id}/comments")
fun getComments(@Path("id") postId : Int) : Call<List<Comment>>*/