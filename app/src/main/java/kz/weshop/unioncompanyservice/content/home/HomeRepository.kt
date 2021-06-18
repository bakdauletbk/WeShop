package kz.weshop.unioncompanyservice.content.home

import android.app.Application
import kz.kreditomat.business.common.base_mvvm.BaseRepository

class HomeRepository(application: Application) : BaseRepository(application) {

    fun getAccessToken() : String = userSession.getAccessToken().toString()

}