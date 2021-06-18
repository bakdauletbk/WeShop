package kz.weshop.unioncompanyservice.common.helpers

import android.util.Base64

//Base64 encode
fun base64encode(decode: String): String {
    return Base64.encodeToString(decode.toByteArray(), Base64.NO_WRAP)
}