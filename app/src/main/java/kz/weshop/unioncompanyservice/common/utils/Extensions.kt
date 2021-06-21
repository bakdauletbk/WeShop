package kz.weshop.unioncompanyservice.common.utils

import android.util.Base64
import android.util.Patterns

//Base64 encode
fun base64encode(decode: String): String {
    return Base64.encodeToString(decode.toByteArray(), Base64.NO_WRAP)
}

fun String.validateEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()
