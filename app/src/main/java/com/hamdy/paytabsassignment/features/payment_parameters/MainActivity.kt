package com.hamdy.paytabsassignment.features.payment_parameters

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.Observer
import com.hamdy.paytabsassignment.R
import com.hamdy.paytabsassignment.base.BaseActivity
import com.hamdy.paytabsassignment.data.source.remote.Resource
import com.hamdy.paytabsassignment.data.source.remote.Status
import com.hamdy.paytabsassignment.databinding.ActivityMainBinding
import com.hamdy.paytabsassignment.features.payment_parameters.pre_auth.PreAuthFragment
import com.hamdy.paytabsassignment.features.payment_parameters.tokenization.TokenizeTransactionRes
import com.hamdy.paytabsassignment.features.payment_result.PaymentResultActivity
import com.hamdy.paytabsassignment.form_validation.FormValidationError
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity
import com.paytabs.paytabs_sdk.utils.PaymentParams
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Error
import java.util.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding>() {


    val vm : MainVM by viewModel()

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {

       binding.model = vm.model

        binding.checkoutBtn.setOnClickListener {
            onCheckoutClick()
        }

        binding.tokenizationIntegrateBtn.setOnClickListener {
            onIntegrateWithTokenizationClick()
        }

        binding.preAuthBtn.setOnClickListener {
            onPreAuthClick()
        }

        vm.formValiResult.observe(this , Observer {
            if(it?.isSuccess == false)
                showValidationErrors(it.fields)
        })

        vm.openCheckout.observe(this , Observer {
            openCheckoutActivity(it)
        })

        vm.TokenizedTransaction.observe(this , Observer {
           TokenizeTransactionResonse(it)
        })


        //init Is Tokenization Checkbox
        binding.ISTOKENIZATION.let {
            it.isChecked = vm.model.IS_TOKENIZATION
            it.setOnCheckedChangeListener { buttonView, isChecked ->
                vm.model.IS_TOKENIZATION
            }
        }
    }

    private fun onPreAuthClick() {
        addFragment(
            PreAuthFragment.newInstance(),
            R.id.container,
            true
        )
    }

    private fun TokenizeTransactionResonse(it: Resource<TokenizeTransactionRes?>?) {

        hideDialogLoading()

        when(it?.status){

            Status.LOADING -> showDialogLoading()

            Status.ERROR -> showErrorMsgDialog(it.message ?: getString(R.string.anErrorOccured))

            Status.SUCCESS -> {
                PaymentResultActivity.openPaymentResultActivity(this , Intent().apply {
                    putExtra(PaymentParams.TRANSACTION_ID , it.data?.transaction_id)
                    putExtra(PaymentParams.RESULT_MESSAGE , it.data?.result)
                    putExtra(PaymentParams.RESPONSE_CODE , it.data?.response_code)

                })
            }
        }
    }

    private fun onIntegrateWithTokenizationClick() {

        if(!vm.isTokenFound()){
            showErrorMsgDialog(getString(R.string.tokenNotFound))
            return
        }

        vm.integrateWithTokenization()

    }

    private fun showValidationErrors(fields: ArrayList<FormValidationError?>?) {

        showErrorMsgDialog(getString(R.string.please_enter_valid_data))

        fields?.get(0)?.formControlResID?.let {
            findViewById<EditText>(it)?.requestFocus()
        }

        fields?.forEach{ formValidationError ->
            formValidationError?.messages?.get(0)?.getMsgStringRes()?.let { it1 -> getString(it1) }
                ?.let {
                    findViewById<EditText>(formValidationError.formControlResID)?.error = it
                }
        }
    }

    private fun openCheckoutActivity(intent : Intent?) {
        intent ?: return
        startActivityForResult(intent, PaymentParams.PAYMENT_REQUEST_CODE)
    }

    private fun onCheckoutClick() {
        hideKeyboard()
        vm.validateForm(this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {

            val RESPONSE_CODE = data?.getStringExtra(PaymentParams.RESPONSE_CODE)
            val resultMsg = data?.getStringExtra(PaymentParams.RESULT_MESSAGE)
            val TRANSACTION_ID = data?.getStringExtra(PaymentParams.TRANSACTION_ID)

            Log.e("Tag", RESPONSE_CODE ?: "")
            Log.e("Tag", TRANSACTION_ID ?: "")
            Log.e("Tag", resultMsg ?: "")


            val TOKEN = data?.getStringExtra(PaymentParams.TOKEN)
            val CUSTOMER_EMAIL = data?.getStringExtra(PaymentParams.CUSTOMER_EMAIL)
            val CUSTOMER_PASSWORD = data?.getStringExtra(PaymentParams.CUSTOMER_PASSWORD)

            Log.e("Tag", TOKEN ?: "")
            Log.e("Tag", CUSTOMER_EMAIL ?: "")
            Log.e("Tag", CUSTOMER_PASSWORD ?: "")

            vm.saveTokenDetails(TOKEN , CUSTOMER_EMAIL , CUSTOMER_PASSWORD)

            PaymentResultActivity.openPaymentResultActivity(this , data)
        }
    }
}