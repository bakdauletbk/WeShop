package kz.weshop.unioncompanyservice.content.sign_in

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.weshop.unioncompanyservice.common.utils.RESPONSE_SUCCESS
import kz.weshop.unioncompanyservice.content.sign_in.model.SignInRequest
import kz.weshop.unioncompanyservice.content.sign_in.model.User
import java.lang.Exception

class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SignInRepository(application)

    val isError = MutableLiveData<String>()
    val isSuccess = MutableLiveData<Boolean>()

    suspend fun signIn(signInRequest: SignInRequest) {
        viewModelScope.launch {
            try {
                val response = repository.signIn(signInRequest)
                when (response.code()) {
                    RESPONSE_SUCCESS -> {
                        isSuccess.postValue(true)
                        response.body()?.user?.let { repository.saveUser(it) }
                    }
                    else -> isSuccess.postValue(false)
                }
            } catch (e: Exception) {
                isError.postValue("")
            }
        }
    }

}