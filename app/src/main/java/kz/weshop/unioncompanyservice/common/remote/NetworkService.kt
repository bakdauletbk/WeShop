package kz.weshop.unioncompanyservice.common.remote

import kz.weshop.unioncompanyservice.content.registration.model.ActivationAccountRequest
import kz.weshop.unioncompanyservice.content.registration.model.RegistrationRequest
import kz.weshop.unioncompanyservice.content.registration.model.RegistrationResponse
import kz.weshop.unioncompanyservice.content.registration.model.SmsModel
import kz.weshop.unioncompanyservice.content.sign_in.model.SignInRequest
import kz.weshop.unioncompanyservice.content.sign_in.model.SignInResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {

    @POST(EndPoints.POST_SIGN_IN)
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>

    @POST(EndPoints.POST_REGISTER)
    suspend fun registration(
        @Body registrationRequest: RegistrationRequest
    ) : Response<RegistrationResponse>

    @POST(EndPoints.POST_RESET_PASSWORD_SMS)
    suspend fun sendSms(
        @Body smsModel: SmsModel
    ): Response<ResponseBody>

    @POST(EndPoints.POST_ACTIVATION)
    suspend fun activateAccount(
        @Body activationAccountRequest: ActivationAccountRequest
    ) : Response<ResponseBody>

}