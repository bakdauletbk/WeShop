package kz.weshop.unioncompanyservice.content.registration

import android.app.Application
import kz.kreditomat.business.common.base_mvvm.BaseRepository
import kz.weshop.unioncompanyservice.content.registration.model.*
import okhttp3.ResponseBody
import retrofit2.Response

class RegistrationRepository(application: Application) : BaseRepository(application) {

    fun clear() {
        userSession.clear()
    }

    suspend fun sendSms(smsModel: SmsModel): Response<ResponseBody> =
        networkService.sendSms(smsModel)

    suspend fun registration(registrationRequest: RegistrationRequest): Response<RegistrationResponse> =
        networkService.registration(registrationRequest)

    fun saveUserDate(userDto: UserDto) {
        userSession.setAccessToken(userDto.access_token.toString())
    }

    suspend fun activateAccount(activationAccountRequest: ActivationAccountRequest): Response<ResponseBody> =
        networkService.activateAccount(activationAccountRequest)
}