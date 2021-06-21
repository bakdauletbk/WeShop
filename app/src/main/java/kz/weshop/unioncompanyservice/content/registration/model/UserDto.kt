package kz.weshop.unioncompanyservice.content.registration.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("fullname")
    val fullname: String? = null,
    @SerializedName("access_token")
    val access_token: String? = null
)