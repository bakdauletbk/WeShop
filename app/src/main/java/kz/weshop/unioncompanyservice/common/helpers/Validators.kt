package kz.weshop.unioncompanyservice.common.helpers

object Validators {

    private const val MIN_PASSWORD_LENGTH = 6
    private const val MIN_PHONE_LENGTH = 16
    private const val MIN_BIN_LENGTH = 11

    fun validatePassword(password: String): Boolean {
        return when {
            password.isBlank() -> false
            password.length < MIN_PASSWORD_LENGTH -> false
            else -> true
        }
    }

    fun validatePhone(phone: String): Boolean {
        return when {
            phone.isBlank() -> false
            phone.length <= MIN_PHONE_LENGTH -> false
            else -> true
        }
    }

    fun validateBin(bin: String): Boolean {
        return when {
            bin.isBlank() -> false
            bin.length <= MIN_BIN_LENGTH -> false
            else -> true
        }
    }

}