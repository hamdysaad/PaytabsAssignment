package com.hamdy.paytabsassignment.features.payment_checkout.pre_auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hamdy.paytabsassignment.R
import com.hamdy.paytabsassignment.base.BaseVM
import com.hamdy.paytabsassignment.features.payment_checkout.FormValiationResult
import com.hamdy.paytabsassignment.form_validation.*

class PreAuthVM( val repo: PreAuthRepo , val transactionPref: TransactionPref) : BaseVM() {

    val SECRET_KEY = "wLQbzomIvihkC9xN16Pc7aj75DmFHAQemGpW4MEA4WUG5su6zjiGtBOzfbeXM4b9KA5PmJqrGl8MOCSnVb4pNTY34d0Rhc02M5S9"
    val MERCHANT_EMAIL = "hamdysaad506@gmail.com"

    val model = PreAuthModel().apply {

        MERCHANT_EMAIL = this@PreAuthVM.MERCHANT_EMAIL
        SECRET_KEY = this@PreAuthVM.SECRET_KEY

        fillDummyData(this)
    }

    private fun fillDummyData(preAuthModel: PreAuthModel) {
        preAuthModel.CAPTURED_AMOUNT = "3.0"
        preAuthModel.TRANSACTION_ID = transactionPref.getTransactionId()
    }

    val formValiResult =  MutableLiveData<FormValiationResult?>()

    val releaseCapturePreauthLD = MutableLiveData<PreAuthModel>()

    val releaseCapturePreauth = Transformations.switchMap(releaseCapturePreauthLD){
        repo.releaseCapturePreauth(it)
    }

    fun validateForm() {

        FormValidation()
            .addField(
                model.MERCHANT_EMAIL,
                R.id.MERCHANT_EMAIL,
                listOf(Required(strRes = R.string.MERCHANT_EMAIL_required_msg))
            )
            .addField(
                model.SECRET_KEY,
                R.id.SECRET_KEY,
                listOf(Required(strRes = R.string.SECRET_KEY_required_msg))
            )
            .addField(
                model.CAPTURED_AMOUNT, R.id.CAPTURED_AMOUNT, listOf(
                    GreaterThanZero(strRes = R.string.AMOUNT_required_msg)
                )
            )
            .addField(
                model.TRANSACTION_ID,
                R.id.TRANSACTION_ID, listOf(
                    Required(strRes = R.string.TRANSACTION_ID_required_msg)
                )
            )

            .validate(object : FormValidationHandler {
                override fun onSuccess() {
                    releaseCapturePreauthLD.value = model
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

}