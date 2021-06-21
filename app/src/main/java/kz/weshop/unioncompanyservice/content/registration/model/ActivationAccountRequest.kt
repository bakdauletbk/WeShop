package kz.weshop.unioncompanyservice.content.registration.model

import com.google.gson.annotations.SerializedName

data class ActivationAccountRequest(
    @SerializedName("activationCode")
    val activationCode: String? = null,
    @SerializedName("phone\n")
    val phone: String? = null
)