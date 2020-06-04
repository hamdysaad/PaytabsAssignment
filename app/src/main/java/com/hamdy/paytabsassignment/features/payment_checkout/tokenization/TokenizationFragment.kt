package com.hamdy.paytabsassignment.features.payment_checkout.tokenization


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import com.hamdy.paytabsassignment.R
import com.hamdy.paytabsassignment.base.BaseFragment
import com.hamdy.paytabsassignment.data.source.remote.Resource
import com.hamdy.paytabsassignment.data.source.remote.Status
import com.hamdy.paytabsassignment.databinding.FragmentTokenizationBinding
import com.hamdy.paytabsassignment.features.payment_checkout.MainVM
import com.hamdy.paytabsassignment.features.payment_result.PaymentResultActivity
import com.hamdy.paytabsassignment.form_validation.FormValidationError
import com.paytabs.paytabs_sdk.utils.PaymentParams
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class TokenizationFragment : BaseFragment<FragmentTokenizationBinding>() {

    val vm : TokenizationVM by viewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_tokenization

    override fun initUI(savedInstanceState: Bundle?) {

        vm.mainVm  = getViewModel<MainVM>()

        binding.model = vm.model
        binding.submitBtn.setOnClickListener { v: View? -> onSubmitClick() }

        vm.formValiResult.observe(this , Observer {
            if(it?.isSuccess == false)
                showValidationErrors(it.fields)
        })

        vm.TokenizedTransaction.observe(this , Observer {
            tokenizeTransactionResonse(it)
        })
    }


    private fun tokenizeTransactionResonse(it: Resource<TokenizeTransactionRes?>?) {

        hideDialogLoading()

        when(it?.status){

            Status.LOADING -> showDialogLoading()

            Status.ERROR -> showErrorMsgDialog(it.message ?: getString(R.string.anErrorOccured))

            Status.SUCCESS -> {
                PaymentResultActivity.openPaymentResultActivity(context , Intent().apply {
                    putExtra(PaymentParams.TRANSACTION_ID , it.data?.transaction_id)
                    putExtra(PaymentParams.RESULT_MESSAGE , it.data?.result)
                    putExtra(PaymentParams.RESPONSE_CODE , it.data?.response_code)

                })
            }
        }
    }

    private fun showValidationErrors(fields: ArrayList<FormValidationError?>?) {

        showErrorMsgDialog(getString(R.string.please_enter_valid_data))

        fields?.get(0)?.formControlResID?.let {
            view?.findViewById<EditText>(it)?.requestFocus()
        }

        fields?.forEach{ formValidationError ->
            formValidationError?.messages?.get(0)?.getMsgStringRes()?.let { it1 -> getString(it1) }
                ?.let {
                    view?.findViewById<EditText>(formValidationError.formControlResID)?.error = it
                }
        }
    }

    private fun onSubmitClick() {
        vm.validateForm()
    }

    companion object {
        fun newInstance(): TokenizationFragment {
            return TokenizationFragment()
        }
    }
}