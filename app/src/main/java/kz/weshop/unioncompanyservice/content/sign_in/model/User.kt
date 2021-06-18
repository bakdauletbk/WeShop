package kz.weshop.unioncompanyservice.content.sign_in.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    val id : Long? =null,
    @SerializedName("fullname")
    val fullname : String? =null,
    @SerializedName("access_token")
    val access_token : String? =null
) : Serializable