package kz.kreditomat.business.common.base_mvvm

import android.app.Application
import android.content.Context
import kz.weshop.unioncompanyservice.common.preferences.UserSession
import kz.weshop.unioncompanyservice.common.remote.ApiConstants
import kz.weshop.unioncompanyservice.common.remote.Networking
import kz.weshop.unioncompanyservice.common.utils.CONTENT_TYPE_JSON

open class BaseRepository(
    application: Application, baseUrl: String = ApiConstants.BASE_URL
) {

    //    val applicationVersion = BuildCo
    val contentType = CONTENT_TYPE_JSON

    val networkService =
        Networking.create(baseUrl)
    private var sharedPreferences =
        application.getSharedPreferences("userSession", Context.MODE_PRIVATE)
    var userSession: UserSession =
        UserSession(sharedPreferences)

}