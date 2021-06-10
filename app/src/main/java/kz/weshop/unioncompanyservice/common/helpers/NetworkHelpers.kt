package kz.weshop.unioncompanyservice.common.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

object NetworkHelpers {

    fun isNetworkConnected(context: Context): Boolean{
        var result = false
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            result =
                isCapableNetwork(
                    this,
                    this.activeNetwork
                )
        }

        return result
    }

    private fun isCapableNetwork(cm: ConnectivityManager, network: Network?): Boolean{
        cm.getNetworkCapabilities(network)?.also {
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            }
        }
        return false
    }


}