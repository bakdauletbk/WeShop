package kz.weshop.unioncompanyservice.content.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.weshop.unioncompanyservice.common.utils.RESPONSE_SUCCESS
import kz.weshop.unioncompanyservice.content.registration.model.ActivationAccountRequest
import kz.weshop.unioncompanyservice.content.registration.model.RegistrationRequest

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RegistrationRepository(application)

    val isSuccess = MutableLiveData<Boolean>()
    val isError = MutableLiveData<String>()
    val isActivation = MutableLiveData<Boolean>()

    fun clear() {
        repository.clear()
    }

    suspend fun registration(registrationRequest: RegistrationRequest) {
        viewModelScope.launch {
            try {
                val response = repository.registration(registrationRequest)
                when (response.code()) {
                    RESPONSE_SUCCESS -> {
                        isSuccess.postValue(true)
                        response.body()?.user?.let { repository.saveUserDate(it) }
                    }
                    else -> isSuccess.postValue(false)
                }
            } catch (e: Exception) {
                isError.postValue("")
            }
        }
    }

    suspend fun activateAccount(activationAccountRequest: ActivationAccountRequest) {
        viewModelScope.launch {
            try {
                val response = repository.activateAccount(activationAccountRequest)
                when (response.code()) {
                    RESPONSE_SUCCESS -> isActivation.postValue(true)
                    else -> isActivation.postValue(false)
                }
            } catch (e: Exception) {
                isError.postValue("")
            }
        }
    }

}