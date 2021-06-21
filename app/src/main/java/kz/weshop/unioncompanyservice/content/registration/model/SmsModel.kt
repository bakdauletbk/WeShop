package kz.weshop.unioncompanyservice.content.registration.model

import com.google.gson.annotations.SerializedName

data class SmsModel(
    @SerializedName("phone")
    val phone: String? = null
)