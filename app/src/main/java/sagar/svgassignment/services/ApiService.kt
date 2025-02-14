package sagar.svgassignment.services

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("breeds/image/random")
    fun generateDogImage(): Call<JsonObject>

}