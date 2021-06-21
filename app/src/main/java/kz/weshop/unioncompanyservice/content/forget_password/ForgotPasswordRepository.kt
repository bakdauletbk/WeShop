package kz.weshop.unioncompanyservice.content.forget_password

import android.app.Application
import kz.kreditomat.business.common.base_mvvm.BaseRepository
import kz.weshop.unioncompanyservice.content.forget_password.model.ResetPasswordRequest
import kz.weshop.unioncompanyservice.content.registration.model.SmsModel
import okhttp3.ResponseBody
import retrofit2.Response

class ForgotPasswordRepository(application: Application) : BaseRepository(application) {

    suspend fun sendSms(smsModel: SmsModel): Response<ResponseBody> =
        networkService.sendSmsResetPassword(smsModel)

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Response<ResponseBody> =
        networkService.resetPassword(resetPasswordRequest)

}