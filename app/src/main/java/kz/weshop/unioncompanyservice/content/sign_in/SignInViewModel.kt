package kz.weshop.unioncompanyservice.content.sign_in

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SignInRepository(application)

}