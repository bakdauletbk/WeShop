package kz.weshop.unioncompanyservice.content.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HomeRepository(application)

    val accessToken = MutableLiveData<String?>()

    fun getAccessToken() {
        try {
            accessToken.postValue(repository.getAccessToken())
        } catch (e: Exception) {
            accessToken.postValue(null)
        }
    }

}