package kz.weshop.unioncompanyservice.content.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R
import kz.weshop.unioncompanyservice.common.base_interfaces.FragmentImpl
import kz.weshop.unioncompanyservice.common.remote.ApiConstants.URL_WE_SHOP
import kz.weshop.unioncompanyservice.common.utils.FIVE
import kz.weshop.unioncompanyservice.common.utils.LOGIN

class HomeFragment : BaseFragment(R.layout.fragment_home), FragmentImpl {

    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupWebView()
        setupViewModel()
        getAccessToken()
        setupListeners()
        setupObservers()
    }

    private fun getAccessToken() {
        viewModel.getAccessToken()
    }

    private fun setupWebView() {
        val webSettings: WebSettings = web_view.settings

        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        getCustomHeaders()?.let { web_view?.loadUrl(URL_WE_SHOP, it) }

        web_view.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                try {
                    loading_view.visibility = View.GONE
                } catch (e: Exception) {
                }
                super.onPageFinished(view, url)
            }

            override fun shouldInterceptRequest(
                view: WebView?,
                url: String?
            ): WebResourceResponse? {
                Log.d("ErmahanUrl", url.toString())

                when (url?.takeLast(FIVE)) {
                    LOGIN -> CoroutineScope(Dispatchers.Main).launch { navigateTo(R.id.signInFragment) }
                }
                return super.shouldInterceptRequest(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { getCustomHeaders()?.let { it1 -> view?.loadUrl(it, it1) } }
                return true
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                getCustomHeaders()?.let { view?.loadUrl(request?.url.toString(), it) }
                return true
            }

        }

    }

    private fun getCustomHeaders(): Map<String, String>? {
        val headers: MutableMap<String, String> = HashMap()
        headers["Authorization"] =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiIiLCJhdWQiOiIiLCJpYXQiOjE2MjM4MjYxMjEsIm5iZiI6MTYyMzgyNjEyMiwiZXhwIjoxNjU1MzYyMTIxLCJkYXRhIjp7InVzZXJJZCI6MTAsInVzZXJBZ2VudCI6IldlU2hvcFwvMSBDRk5ldHdvcmtcLzEyMDYgRGFyd2luXC8xOS42LjAifX0.3qcqOia4nmNacWhDE9hA3-HaO8cAz4GVlBJLWdABLUqt9PO8Xv6tq2uF2hkk_14KuOdJSVlitK63M1Hr9OU1NQ"
        return headers
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }

}