package sagar.svgassignment.services

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Body : Serializable {
    @SerializedName("message")
    @Expose
    var message: String? = null
}