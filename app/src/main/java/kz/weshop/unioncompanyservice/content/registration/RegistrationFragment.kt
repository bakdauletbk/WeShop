package kz.weshop.unioncompanyservice.content.registration

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_registration.*
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R
import org.jetbrains.anko.sdk27.coroutines.onClick

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        initListeners()
    }

    private fun initListeners() {
        tv_sign_in.onClick {
            navigateTo(R.id.signInFragment)
        }
    }

}