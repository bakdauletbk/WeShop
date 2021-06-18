package kz.weshop.unioncompanyservice.content.sign_in.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("user")
    val user : User
)