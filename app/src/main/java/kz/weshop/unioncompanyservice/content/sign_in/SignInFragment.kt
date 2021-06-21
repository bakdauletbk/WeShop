package kz.weshop.unioncompanyservice.content.sign_in

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R
import kz.weshop.unioncompanyservice.common.base_interfaces.FragmentImpl
import kz.weshop.unioncompanyservice.common.helpers.TextUtils
import kz.weshop.unioncompanyservice.common.helpers.Validators
import kz.weshop.unioncompanyservice.common.helpers.base64encode
import kz.weshop.unioncompanyservice.content.sign_in.model.SignInRequest
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignInFragment : BaseFragment(R.layout.fragment_sign_in), FragmentImpl {

    private lateinit var viewModel: SignInViewModel

    private var isValidateNumber = false
    private var isValidatePassword = false

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
            prepareLogin()
        }
        tv_forget_password.onClick {
            navigateTo(R.id.forgetPasswordFragment)
        }
        tv_register.onClick {
            navigateTo(R.id.action_signInFragment_to_registrationFragment)
        }
    }

    private fun prepareLogin() {
        val userName = et_login.text.toString()
        val password = et_password.text.toString()

        when (Validators.validatePhone(userName)) {
            true -> isValidateNumber = true
            false -> {
                isValidateNumber = false
                et_login.error = getString(R.string.enter_your_phone_number)
            }
        }
        when (password.isNotEmpty()) {
            true -> isValidatePassword = true
            false -> {
                isValidatePassword = false
                et_password.error = getString(R.string.enter_password)
            }
        }
        when (isValidateNumber && isValidatePassword) {
            true -> {
                setLoading(true)
                val signInRequest = SignInRequest(
                    base64encode(TextUtils.textToNumberFormat(userName)),
                    base64encode(password)
                )
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.signIn(signInRequest)
                }
            }
        }
    }

    override fun setupObservers() {
        viewModel.isError.observe(viewLifecycleOwner, {
            setLoading(false)
            errorDialog(getString(R.string.error_unknown_body))
        })
        viewModel.isSuccess.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    setLoading(false)
                    navigateTo(R.id.homeFragment)
                }
                false -> {
                    setLoading(false)
                    errorDialog(getString(R.string.error_failed_connection_to_server))
                }
            }
        })
    }

    private fun setLoading(loading: Boolean) {
        loading_view.visibility = if (loading) View.VISIBLE else View.GONE
    }

}