package kz.weshop.unioncompanyservice.content.forget_password

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_forget_password.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R
import kz.weshop.unioncompanyservice.common.helpers.TextUtils
import kz.weshop.unioncompanyservice.common.helpers.Validators
import kz.weshop.unioncompanyservice.content.forget_password.model.ResetPasswordRequest
import kz.weshop.unioncompanyservice.content.registration.model.SmsModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class ForgetPasswordFragment : BaseFragment(R.layout.fragment_forget_password) {

    private lateinit var viewModel: ForgotPasswordViewModel

    private var isValidateSmsCode = false
    private var isValidatePassword = false
    private var isValidateRetryPassword = false
    private var isValidatePasswordFit = false

    private var isSendSms = false

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
            setLoading(false)
            errorDialog(getString(R.string.error_unknown_body))
        })
        viewModel.isSendSms.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    setLoading(false)
                    isSendSms = true
                    et_phone.isEnabled = false
                    ll_field_password.visibility = View.VISIBLE
                }
                false -> {
                    setLoading(false)
                    errorDialog(getString(R.string.error_failed_connection_to_server))
                }
            }
        })
        viewModel.isResetPassword.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    navigateTo(R.id.signInFragment)
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.password_changed_successfully),
                        Toast.LENGTH_LONG
                    ).show()
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

    private fun initListeners() {
        btn_send.onClick {
            if (isSendSms) prepareLogin() else sendSms()
        }
    }

    private fun sendSms() {
        val phone = et_phone.text.toString()
        when (Validators.validatePhone(phone)) {
            true -> {
                setLoading(true)
                val smsModel = SmsModel(phone = TextUtils.textToNumberFormat(phone))
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.sendSms(smsModel)
                }
            }
            false -> et_phone.error = getString(R.string.validate_phone)
        }
    }

    private fun prepareLogin() {
        val phone = et_phone.text.toString()
        val smsCode = et_sms_code.text.toString()
        val password = et_password.text.toString()
        val retryPassword = et_retry_password.text.toString()

        if (smsCode.isNotEmpty()) isValidateSmsCode = true else {
            et_sms_code.error = getString(R.string.validate_sms_code)
            isValidateSmsCode = false
        }
        if (Validators.validatePassword(password)) isValidatePassword = true else {
            et_password.error = getString(R.string.validate_password_size)
            isValidatePassword = false
        }
        if (Validators.validatePassword(retryPassword)) isValidateRetryPassword = true else {
            et_retry_password.error = getString(R.string.validate_password_size)
            isValidateRetryPassword = false
        }
        if (password == retryPassword) isValidatePasswordFit = true else {
            et_retry_password.error = getString(R.string.validate_password_fit)
            et_password.error = getString(R.string.validate_password_fit)
            isValidatePasswordFit = false
        }

        if (isValidatePassword && isValidatePasswordFit && isValidateRetryPassword && isValidateSmsCode) {
            val resetPasswordRequest =
                ResetPasswordRequest(
                    activationCode = smsCode,
                    phone = TextUtils.textToNumberFormat(phone),
                    password = password
                )
            sendResetPassword(resetPasswordRequest)
        }

    }

    private fun sendResetPassword(resetPasswordRequest: ResetPasswordRequest) {
        setLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.resetPassword(resetPasswordRequest)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
    }

}