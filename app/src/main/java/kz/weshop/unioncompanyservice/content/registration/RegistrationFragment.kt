package kz.weshop.unioncompanyservice.content.registration

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_registration.*
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R
import org.jetbrains.anko.sdk27.coroutines.onClick

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private lateinit var viewModel: RegistrationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        initViewModel()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.isError.observe(viewLifecycleOwner, {
            errorDialog(getString(R.string.error_unknown_body))
        })
        viewModel.isSuccess.observe(viewLifecycleOwner, {
            when (it) {
                true -> navigateTo(R.id.homeFragment)
                false -> errorDialog(getString(R.string.error_registration))
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
    }

    private fun initListeners() {
        tv_sign_in.onClick {
            navigateTo(R.id.signInFragment)
        }
    }

}