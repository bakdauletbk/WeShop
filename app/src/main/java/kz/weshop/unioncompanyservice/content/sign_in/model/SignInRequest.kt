package kz.weshop.unioncompanyservice.content.sign_in.model

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("password")
    val password: String? = null
)