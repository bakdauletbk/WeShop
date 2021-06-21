package kz.weshop.unioncompanyservice.content.registration.model

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("user")
    val user: UserDto
)