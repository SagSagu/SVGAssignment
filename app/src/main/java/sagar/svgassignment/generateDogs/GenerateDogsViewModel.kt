package sagar.svgassignment.generateDogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sagar.svgassignment.services.ApiClient
import sagar.svgassignment.utils.LocalCache
import java.lang.Exception

class GenerateDogsViewModel: ViewModel() {

    var imageUrl = MutableLiveData<String>()
    var enableButton = MutableLiveData<Boolean>()

    fun generateDogImage() {
        enableButton.value = false
        ApiClient.apiService.generateDogImage().enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    getImageUrlFromResponse(response.body()!!)
                } else {
                    val errorBody = response.errorBody()
                }
                enableButton.value = true
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                enableButton.value = true
            }
        })
    }

    private fun getImageUrlFromResponse(response: JsonObject) {
        val jsonObj = response.toString()
        val updatedJsonObj = JSONObject(jsonObj)
        var url: String? = null
        try {
            url = updatedJsonObj.getString("message")
            if (url == "null")
                url = null
        } catch (e: Exception){}
        if (url != null) {
            imageUrl.value = url!!
            if (url.isEmpty().not()){
                var key = url
                if (key.contains("https://images.dog.ceo/breeds/")){
                    key = key.replace("https://images.dog.ceo/breeds/", "")
                }
                LocalCache.addToCache(key, url)
            }
        }
    }

}