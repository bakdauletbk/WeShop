package kz.weshop.unioncompanyservice.content.forget_password

import android.os.Bundle
import android.view.View
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R

class ForgetPasswordFragment : BaseFragment(R.layout.fragment_forget_password) {

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

    }

    private fun initListeners() {

    }

    private fun initViewModel() {

    }

}