package kz.weshop.unioncompanyservice.content.forget_password.model

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("activationCode")
    val activationCode: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("password")
    val password: String? = null
)