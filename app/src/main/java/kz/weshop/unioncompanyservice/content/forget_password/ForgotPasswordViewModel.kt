package kz.weshop.unioncompanyservice.content.forget_password

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.weshop.unioncompanyservice.common.utils.RESPONSE_SUCCESS
import kz.weshop.unioncompanyservice.content.forget_password.model.ResetPasswordRequest
import kz.weshop.unioncompanyservice.content.registration.model.SmsModel

class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ForgotPasswordRepository(application)

    val isSendSms = MutableLiveData<Boolean>()
    val isResetPassword = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()

    suspend fun sendSms(smsModel: SmsModel) {
        viewModelScope.launch {
            try {
                val response = repository.sendSms(smsModel)
                when (response.code()) {
                    RESPONSE_SUCCESS -> isSendSms.postValue(true)
                    else -> isSendSms.postValue(false)
                }
            } catch (e: Exception) {
                isError.postValue("")
            }
        }
    }

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest) {
        viewModelScope.launch {
            try {
                val response = repository.resetPassword(resetPasswordRequest)
                when (response.code()) {
                    RESPONSE_SUCCESS -> isResetPassword.postValue(true)
                    else -> isResetPassword.postValue(true)
                }
            } catch (e: Exception) {
                isError.postValue("")
            }
        }
    }

}