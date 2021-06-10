package kz.weshop.unioncompanyservice.content.sign_in

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R
import kz.weshop.unioncompanyservice.common.base_interfaces.FragmentImpl
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignInFragment : BaseFragment(R.layout.fragment_sign_in), FragmentImpl {

    private lateinit var viewModel : SignInViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    override fun lets() {
        setupViewModel()
        setupListeners()
        setupObservers()
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
    }

    override fun setupListeners() {
        btn_to_come_in.onClick {
//            navigateTo(R.id.homeFragment)
        }
    }

    override fun setupObservers() {
    }


}