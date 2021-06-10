package kz.weshop.unioncompanyservice.common.helpers

object TextUtils {

    fun textToNumberFormat(text: String?): String {
        return "${text!![1]}${text[2]}${text[3]}${text[6]}${text[7]}${text[8]}${text[10]}${text[11]}${text[13]}${text[14]}"
    }

}