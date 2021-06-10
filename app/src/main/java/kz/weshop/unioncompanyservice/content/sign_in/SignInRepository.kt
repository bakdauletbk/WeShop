package kz.weshop.unioncompanyservice.content.sign_in

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kz.kreditomat.business.common.base_mvvm.BaseRepository

class SignInRepository(application: Application) : BaseRepository(application) {

    private val repository = SignInRepository(application)

    val isError = MutableLiveData<String>()
    val isSuccess = MutableLiveData<Boolean>()

}