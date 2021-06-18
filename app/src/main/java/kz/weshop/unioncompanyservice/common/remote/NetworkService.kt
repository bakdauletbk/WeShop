package kz.weshop.unioncompanyservice.common.remote

import kz.weshop.unioncompanyservice.content.sign_in.model.SignInRequest
import kz.weshop.unioncompanyservice.content.sign_in.model.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {

    @POST(EndPoints.POST_SIGN_IN)
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>

}