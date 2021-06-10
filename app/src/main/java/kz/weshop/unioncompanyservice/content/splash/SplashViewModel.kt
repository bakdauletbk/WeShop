package kz.weshop.unioncompanyservice.content.splash

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SplashRepository(application)

    var isNetworkConnected = MutableLiveData<Boolean>()
    var isAuthorized = MutableLiveData<Boolean>()

    fun getIsNetworkConnected(context: Context) {
        viewModelScope.launch {
            isNetworkConnected.postValue(repository.getIsNetworkConnected(context))
        }
    }

    fun getIsAuthorized() {
        viewModelScope.launch {
            isAuthorized.postValue(repository.getIsAuthorized())
        }
    }

}