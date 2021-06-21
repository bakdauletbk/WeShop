package kz.weshop.unioncompanyservice.content.splash

import android.app.Application
import android.content.Context
import kz.kreditomat.business.common.base_mvvm.BaseRepository
import kz.weshop.unioncompanyservice.common.helpers.NetworkHelpers

class SplashRepository(application: Application) : BaseRepository(application) {

    fun getIsNetworkConnected(context: Context): Boolean {
        return NetworkHelpers.isNetworkConnected(context)
    }

}