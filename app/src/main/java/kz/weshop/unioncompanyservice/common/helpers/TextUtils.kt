package kz.weshop.unioncompanyservice.common.helpers

object TextUtils {

    fun textToNumberFormat(text: String?): String {
        return "7${text!![3]}${text[4]}${text[5]}${text[8]}${text[9]}${text[10]}${text[12]}${text[13]}${text[15]}${text[16]}"
    }

}