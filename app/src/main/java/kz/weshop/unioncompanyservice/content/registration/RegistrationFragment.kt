package kz.weshop.unioncompanyservice.content.registration

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.kreditomat.business.common.base_mvvm.BaseFragment
import kz.weshop.unioncompanyservice.R
import kz.weshop.unioncompanyservice.common.helpers.TextUtils
import kz.weshop.unioncompanyservice.common.helpers.Validators
import kz.weshop.unioncompanyservice.common.helpers.base64encode
import kz.weshop.unioncompanyservice.common.utils.ONE
import kz.weshop.unioncompanyservice.common.utils.TWO
import kz.weshop.unioncompanyservice.common.utils.ZERO
import kz.weshop.unioncompanyservice.content.registration.model.ActivationAccountRequest
import kz.weshop.unioncompanyservice.content.registration.model.RegistrationRequest
import kz.weshop.unioncompanyservice.content.registration.model.SmsModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private lateinit var viewModel: RegistrationViewModel

    private var isFullNameValidate = false
    private var isPhoneValidate = false
    private var isEmailValidate = false
    private var isPasswordValidate = false
    private var isSmsCodeValidate = false

    private var statusField = ZERO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigateTo(R.id.signInFragment)
        }
    }

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
                    statusField = ONE
                    showRegistrationField(ONE)
                }
                false -> {
                    setLoading(false)
                    errorDialog(getString(R.string.error_failed_connection_to_server))
                }
            }
        })
        viewModel.isSuccess.observe(viewLifecycleOwner, {
            when (it) {
                true -> navigateTo(R.id.homeFragment)
                false -> {
                    setLoading(false)
                    errorDialog(getString(R.string.error_registration))
                }
            }
        })
        viewModel.isActivation.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    setLoading(false)
                    statusField = TWO
                    showRegistrationField(TWO)
                }
                false -> {
                    setLoading(false)
                    errorDialog(getString(R.string.error_failed_connection_to_server))
                }
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
    }

    private fun initListeners() {
        btn_registration.onClick {
            when (statusField) {
                ZERO -> prepareSendSms()
                ONE -> prepareActivationAccount()
                TWO -> prepareRegister()
            }
        }
        tv_sign_in.onClick {
            navigateTo(R.id.signInFragment)
        }
    }

    private fun prepareSendSms() {
        val phone = et_phone.text.toString()
        if (Validators.validatePhone(phone)) {
            val smsModel = SmsModel(TextUtils.textToNumberFormat(phone))
            setLoading(true)
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.sendSms(smsModel)
            }
        } else et_phone.error = getString(R.string.validate_phone)
    }

    private fun prepareActivationAccount() {
        val phone = et_phone.text.toString()
        val smsCode = et_sms_code.text.toString()

        if (smsCode.isNotEmpty()) isSmsCodeValidate = true else {
            isSmsCodeValidate = false
            et_sms_code.error = getString(R.string.validate_sms_code)
        }
        if (Validators.validatePhone(phone)) isPhoneValidate = true else {
            isPhoneValidate = false
            et_phone.error = getString(R.string.validate_phone)
        }

        if (isPhoneValidate && isSmsCodeValidate) {
            val activationAccountRequest = ActivationAccountRequest(
                phone = TextUtils.textToNumberFormat(phone),
                activationCode = smsCode
            )
            sendActivationAccount(activationAccountRequest)
        }
    }

    private fun showRegistrationField(status: Int) {
        when (status) {
            ONE -> {
                et_phone.isEnabled = false
                tv_sms_code.visibility = View.VISIBLE
                et_sms_code.visibility = View.VISIBLE
            }
            TWO -> {
                et_sms_code.isEnabled = false
                ll_field.visibility = View.VISIBLE
            }
        }
    }

    private fun sendActivationAccount(activationAccountRequest: ActivationAccountRequest) {
        setLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.activateAccount(activationAccountRequest)
        }
    }

    private fun prepareRegister() {
        val fullName = et_full_name.text.toString()
        val phone = et_phone.text.toString()
        val email = et_email.text.toString()
        val password = et_password.text.toString()

        if (fullName.isNotEmpty()) isFullNameValidate = true else {
            isFullNameValidate = false
            et_full_name.error = getString(R.string.validate_full_name)
        }
        if (email.isNotEmpty()) isEmailValidate = true else {
            isEmailValidate = false
            et_email.error = getString(R.string.validate_email)
        }
        if (Validators.validatePassword(password)) isPasswordValidate = true else {
            isPasswordValidate = false
            et_password.error = getString(R.string.validate_password)
        }
        if (Validators.validatePhone(phone)) isPhoneValidate = true else {
            isPhoneValidate = false
            et_phone.error = getString(R.string.validate_phone)
        }

        if (isEmailValidate && isFullNameValidate && isPasswordValidate && isPhoneValidate) {
            val registrationRequest = RegistrationRequest(
                email = email,
                password = password,
                fullname = fullName,
                phone = TextUtils.textToNumberFormat(phone)
            )
            setRegistration(
                registrationRequest
            )
        }
    }

    private fun setRegistration(registrationRequest: RegistrationRequest) {
        setLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.registration(registrationRequest)
        }
    }

    private fun setLoading(loading: Boolean) {
        loading_view.visibility = if (loading) View.VISIBLE else View.GONE
    }

}