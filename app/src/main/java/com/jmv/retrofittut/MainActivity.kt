package com.jmv.retrofittut

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jmv.retrofittut.model.Comment
import com.jmv.retrofittut.model.Post
import com.jmv.retrofittut.networking.RemoteAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    private lateinit var remoteAPIService: RemoteAPIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.text_view_result)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
                //define that we want to use Gson to pass the response
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        remoteAPIService = retrofit.create(RemoteAPIService::class.java)

        getPosts()
      //  getComments()

      //  createPost()

    }

    private fun getPosts() {
        val call: Call<List<Post>> = remoteAPIService.getPosts()
      //  val call: Call<List<Post>> = remoteAPIService.getPosts(4)


        call.enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                val posts: List<Post>? = response.body()

                for (post in posts!!){
                    var content = ""
                    content += "Text : ${post.text} \n\n"
                    textViewResult.append(content)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textViewResult.text = t.message
            }
        })
    }

    /*private fun getComments() {
        var call : Call<List<Comment>> = remoteAPIService.getComments(3)

        call.enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                val comments : List<Comment>? = response.body()

                for (comment in comments!!){
                    var content = ""
                    content += "Post ID: ${comment.postId} \n"
                    content += "Text : ${comment.text} \n\n"
                    textViewResult.append(content)
                }

            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                textViewResult.text = t.message
            }
        })
    }*/

    private fun createPost() {
        val post = Post(23, null, "New Title","New Text")

        val call: Call<Post?>? = remoteAPIService.createPost(post)

        call?.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if (!response.isSuccessful) {
                    textViewResult.text = "Code: " + response.code()
                    return
                }
                val postResponse = response.body()
                var content = ""
                content += "Code: ${response.code()} \n"
                content += "Title: ${postResponse?.title} \n"
                textViewResult.text = content
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                textViewResult.text = t.message
            }
        })
    }

}