package kz.weshop.unioncompanyservice.content.sign_in

import android.app.Application
import android.content.Context
import kz.weshop.unioncompanyservice.common.preferences.UserSession
import kz.weshop.unioncompanyservice.common.remote.ApiConstants
import kz.weshop.unioncompanyservice.common.remote.Networking
import kz.weshop.unioncompanyservice.content.sign_in.model.SignInRequest
import kz.weshop.unioncompanyservice.content.sign_in.model.SignInResponse
import kz.weshop.unioncompanyservice.content.sign_in.model.User
import retrofit2.Response


class SignInRepository(application: Application) {

    private val networkService =
        Networking.create(ApiConstants.BASE_URL)
    private var sharedPreferences =
        application.getSharedPreferences("userSession", Context.MODE_PRIVATE)
    private var sessionManager: UserSession =
        UserSession(sharedPreferences)


    suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse> =
        networkService.signIn(signInRequest)

    fun saveUser(user: User) {
        sessionManager.setAccessToken(user.access_token.toString())
    }

}