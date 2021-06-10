package kz.weshop.unioncompanyservice.content.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R
import kz.weshop.unioncompanyservice.common.base_interfaces.FragmentImpl
import kz.weshop.unioncompanyservice.common.utils.DELAY_THREE_SECOND

class SplashFragment : BaseFragment(R.layout.fragment_splash), FragmentImpl {

    private lateinit var viewModel: SplashViewModel

    private var isFirstLaunch = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupViewModel()
        setupListeners()
        setupObservers()
        initNetWorkChecker()
    }

    private fun initNetWorkChecker() {
        CoroutineScope(Dispatchers.IO).launch {
            checkNetworkConnection()
        }
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun setupListeners() {
    }

    private suspend fun checkNetworkConnection() {
        when (isFirstLaunch) {
            true -> firstLaunch()
        }
    }

    override fun setupObservers() {
        viewModel.isNetworkConnected.observe(viewLifecycleOwner, {
            when (it) {
                true -> navigateTo(R.id.homeFragment)
                false -> errorDialog(getString(R.string.error_unknown_body))
            }
        })
    }

    private suspend fun firstLaunch() {
        isFirstLaunch = false
        delay(DELAY_THREE_SECOND)
        getIsNetworkConnected()
    }

    private fun getIsNetworkConnected() {
        viewModel.getIsNetworkConnected(requireContext())
    }
}