package kz.weshop.unioncompanyservice.content.registration

import android.app.Application
import kz.kreditomat.business.common.base_mvvm.BaseRepository
import kz.weshop.unioncompanyservice.content.registration.model.RegistrationRequest
import kz.weshop.unioncompanyservice.content.registration.model.RegistrationResponse
import kz.weshop.unioncompanyservice.content.registration.model.UserDto
import retrofit2.Response

class RegistrationRepository(application: Application) : BaseRepository(application) {

    suspend fun registration(registrationRequest: RegistrationRequest): Response<RegistrationResponse> =
        networkService.registration(registrationRequest)

    fun saveUserDate(userDto: UserDto) {
        userSession.setAccessToken(userDto.access_token.toString())
    }
}