package kz.weshop.unioncompanyservice.content.forget_password

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ForgotPasswordRepository(application)



}