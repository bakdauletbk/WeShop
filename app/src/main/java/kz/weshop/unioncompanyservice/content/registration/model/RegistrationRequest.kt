package kz.weshop.unioncompanyservice.content.registration.model

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("fullname")
    val fullname: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("email")
    val email: String? = null
)