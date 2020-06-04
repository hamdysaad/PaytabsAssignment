package com.hamdy.paytabsassignment.features.payment_checkout.tokenization

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hamdy.paytabsassignment.R
import com.hamdy.paytabsassignment.base.BaseVM
import com.hamdy.paytabsassignment.features.payment_checkout.FormValiationResult
import com.hamdy.paytabsassignment.features.payment_checkout.MainVM
import com.hamdy.paytabsassignment.form_validation.*
import com.paytabs.paytabs_sdk.utils.PaymentParams.TOKEN

class TokenizationVM(val tokenizationPref : TokenizationPref ,val tokenizationRepo: TokenizationRepo) : BaseVM() {

    var mainVm: MainVM? = null
    val SECRET_KEY = "wLQbzomIvihkC9xN16Pc7aj75DmFHAQemGpW4MEA4WUG5su6zjiGtBOzfbeXM4b9KA5PmJqrGl8MOCSnVb4pNTY34d0Rhc02M5S9"
    val MERCHANT_EMAIL = "hamdysaad506@gmail.com"

    val model = tokenizationPref.getTokenization().apply {
        FIRST_NAME = "Hamdy"
        LAST_NAME = "Saad"
    }

    val formValiResult =  MutableLiveData<FormValiationResult?>()

    val tokenizationDataLD = MutableLiveData<TokenizedTranactionRequest>()

    val TokenizedTransaction = Transformations.switchMap(tokenizationDataLD){
        tokenizationRepo.tokenizeTransaction(it)
    }

    fun validateForm() {

        FormValidation()
            .addField(
                model.TOKEN,
                R.id.TOKEN,
                listOf(Required(strRes = R.string.TOKEN_required_msg))
            )
            .addField(
                model.CUSTOMER_EMAIL,
                R.id.CUSTOMER_EMAIL,
                listOf(Required(strRes = R.string.CUSTOMER_EMAIL_required_msg))
            )
            .addField(
                model.CUSTOMER_PASSWORD, R.id.CUSTOMER_PASSWORD, listOf(
                    Required(strRes = R.string.CUSTOMER_PASSWORD_required_msg)
                )
            )
            .addField(
                model.FIRST_NAME,
                R.id.FIRST_NAME, listOf(
                    Required(strRes = R.string.FIRST_NAME_required_msg)
                )
            )

            .addField(
                model.LAST_NAME,
                R.id.LAST_NAME, listOf(
                    Required(strRes = R.string.LAST_NAME_required_msg)
                )
            )

            .validate(object : FormValidationHandler {
                override fun onSuccess() {
                    integrateWithTokenization()
                }

                override fun onFailure(fields: ArrayList<FormValidationError?>) {
                    formValiResult.value =
                        FormValiationResult(
                            fields = fields,
                            isSuccess = false
                        )
                }
            })
    }

    fun integrateWithTokenization() {
        tokenizationPref.getTokenization()?.let {
            if(!it.TOKEN.isNullOrEmpty()){
                tokenizationDataLD.value = TokenizedTranactionRequest(
                    it.TOKEN ,
                    it.CUSTOMER_EMAIL,
                    it.CUSTOMER_PASSWORD,
                    "Hamdy",
                    "Saad",
                    mainVm?.model
                )
            }


        }
    }

}