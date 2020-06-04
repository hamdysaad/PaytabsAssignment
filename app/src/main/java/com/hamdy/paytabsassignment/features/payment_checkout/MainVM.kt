package com.hamdy.paytabsassignment.features.payment_checkout

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.hamdy.paytabsassignment.R
import com.hamdy.paytabsassignment.base.BaseVM
import com.hamdy.paytabsassignment.features.payment_checkout.pre_auth.TransactionPref
import com.hamdy.paytabsassignment.features.payment_checkout.tokenization.TokenizationModel
import com.hamdy.paytabsassignment.features.payment_checkout.tokenization.TokenizationPref
import com.hamdy.paytabsassignment.features.payment_checkout.tokenization.TokenizationRepo
import com.hamdy.paytabsassignment.form_validation.*
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity
import com.paytabs.paytabs_sdk.utils.PaymentParams

class MainVM(
    val tokenizationPref : TokenizationPref ,
    val transactionPref: TransactionPref) : BaseVM() {

    val SECRET_KEY = "wLQbzomIvihkC9xN16Pc7aj75DmFHAQemGpW4MEA4WUG5su6zjiGtBOzfbeXM4b9KA5PmJqrGl8MOCSnVb4pNTY34d0Rhc02M5S9"
    val MERCHANT_EMAIL = "hamdysaad506@gmail.com"
    val PAY_BUTTON_COLOR = "#2474bc"

    val model = PaymentParamModel()
        .apply {

        MERCHANT_EMAIL = this@MainVM.MERCHANT_EMAIL
        SECRET_KEY = this@MainVM.SECRET_KEY
        LANGUAGE = PaymentParams.ENGLISH

        fillDummyData(this)
    }

    val formValiResult =  MutableLiveData<FormValiationResult?>()
    val openCheckout =  MutableLiveData<Intent?>()


    fun validateForm(context: Context?) {


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
                model.LANGUAGE,
                R.id.LANGUAGE,
                listOf(Required(strRes = R.string.LANGUAGE_required_msg))
            )
            .addField(
                model.TRANSACTION_TITLE,
                R.id.TRANSACTION_TITLE,
                listOf(Required(strRes = R.string.TRANSACTION_TITLE_required_msg))
            )
            .addField(
                model.AMOUNT, R.id.AMOUNT, listOf(
                    GreaterThanZero(strRes = R.string.AMOUNT_required_msg)
                )
            )
            .addField(
                model.CURRENCY_CODE,
                R.id.CURRENCY_CODE, listOf(
                    Required(strRes = R.string.CURRENCY_CODE_required_msg)
                )
            )
            .addField(
                model.CUSTOMER_PHONE_NUMBER,
                R.id.CUSTOMER_PHONE_NUMBER,
                listOf(Required(strRes = R.string.CUSTOMER_PHONE_NUMBER_required_msg))
            )
            .addField(
                model.CUSTOMER_EMAIL,
                R.id.CUSTOMER_EMAIL,
                listOf(Required(strRes = R.string.CUSTOMER_EMAIL_required_msg))
            )
            .addField(
                model.ORDER_ID,
                R.id.ORDER_ID,
                listOf(Required(strRes = R.string.ORDER_ID_required_msg))
            )
            .addField(
                model.PRODUCT_NAME,
                R.id.PRODUCT_NAME,
                listOf(Required(strRes = R.string.PRODUCT_NAME_required_msg))
            )
            .addField(
                model.ADDRESS_BILLING,
                R.id.ADDRESS_BILLING,
                listOf(Required(strRes = R.string.ADDRESS_BILLING_required_msg))
            )
            .addField(
                model.CITY_BILLING,
                R.id.CITY_BILLING,
                listOf(Required(strRes = R.string.CITY_BILLING_required_msg))
            )
            .addField(
                model.STATE_BILLING,
                R.id.STATE_BILLING,
                listOf(Required(strRes = R.string.STATE_BILLING_required_msg))
            )
            .addField(
                model.COUNTRY_BILLING,
                R.id.COUNTRY_BILLING,
                listOf(Required(strRes = R.string.COUNTRY_BILLING_required_msg))
            )
            .addField(
                model.POSTAL_CODE_BILLING,
                R.id.POSTAL_CODE_BILLING,
                listOf(Required(strRes = R.string.POSTAL_CODE_BILLING_required_msg))
            )
            .addField(
                model.ADDRESS_SHIPPING,
                R.id.ADDRESS_SHIPPING,
                listOf(Required(strRes = R.string.ADDRESS_SHIPPING_required_msg))
            )
            .addField(
                model.CITY_SHIPPING,
                R.id.CITY_SHIPPING,
                listOf(Required(strRes = R.string.CITY_SHIPPING_required_msg))
            )
            .addField(
                model.STATE_SHIPPING,
                R.id.STATE_SHIPPING,
                listOf(Required(strRes = R.string.STATE_SHIPPING_required_msg))
            )
            .addField(
                model.COUNTRY_SHIPPING,
                R.id.COUNTRY_SHIPPING,
                listOf(Required(strRes = R.string.COUNTRY_SHIPPING_required_msg))
            )
            .addField(
                model.POSTAL_CODE_SHIPPING,
                R.id.POSTAL_CODE_SHIPPING,
                listOf(Required(strRes = R.string.POSTAL_CODE_SHIPPING_required_msg))
            )
            .validate(object : FormValidationHandler {
                override fun onSuccess() {
                    prepareCheckoutActivityData(context)
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

    private fun prepareCheckoutActivityData(context: Context?) {

        context ?: return

        openCheckout.value = Intent(context, PayTabActivity::class.java).apply {

            putExtra(PaymentParams.MERCHANT_EMAIL, model.MERCHANT_EMAIL)
            putExtra(PaymentParams.SECRET_KEY, model.SECRET_KEY)
            putExtra(PaymentParams.LANGUAGE, model.LANGUAGE)


            putExtra(PaymentParams.TRANSACTION_TITLE, model.TRANSACTION_TITLE)
            putExtra(PaymentParams.AMOUNT, model.AMOUNT?.toDouble()  ?: 0.0 )

            putExtra(PaymentParams.CURRENCY_CODE,  model.CURRENCY_CODE)
            putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER,  model.CUSTOMER_PHONE_NUMBER)
            putExtra(PaymentParams.CUSTOMER_EMAIL,  model.CUSTOMER_EMAIL)
            putExtra(PaymentParams.ORDER_ID,  model.ORDER_ID)
            putExtra(PaymentParams.PRODUCT_NAME,  model.PRODUCT_NAME)

             //Billing Address
            putExtra(PaymentParams.ADDRESS_BILLING,  model.ADDRESS_BILLING)
            putExtra(PaymentParams.CITY_BILLING,  model.CITY_BILLING)
            putExtra(PaymentParams.STATE_BILLING,  model.STATE_BILLING)
            putExtra(PaymentParams.COUNTRY_BILLING,  model.COUNTRY_BILLING)
            putExtra(PaymentParams.POSTAL_CODE_BILLING, model.POSTAL_CODE_BILLING)

              //Shipping Address
            putExtra(PaymentParams.ADDRESS_SHIPPING,  model.ADDRESS_SHIPPING)
            putExtra(PaymentParams.CITY_SHIPPING,  model.CITY_SHIPPING)
            putExtra(PaymentParams.STATE_SHIPPING,  model.STATE_SHIPPING)
            putExtra(PaymentParams.COUNTRY_SHIPPING,  model.COUNTRY_SHIPPING)
            putExtra(PaymentParams.POSTAL_CODE_SHIPPING, model.POSTAL_CODE_SHIPPING)

             //Payment Page Style
            putExtra(PaymentParams.PAY_BUTTON_COLOR, PAY_BUTTON_COLOR)

             //Tokenization
            putExtra(PaymentParams.IS_TOKENIZATION,  model.IS_TOKENIZATION)

            //Preauth
            putExtra(PaymentParams.IS_PREAUTH,  1)

        }
    }

    private fun fillDummyData(paymentParamModel: PaymentParamModel) {
       paymentParamModel.AMOUNT                 =  "5.0"
       paymentParamModel.TRANSACTION_TITLE      = "Test Paytabs android library"

       paymentParamModel.CURRENCY_CODE          = "BHD"
       paymentParamModel.CUSTOMER_PHONE_NUMBER  =  "009733"
       paymentParamModel.CUSTOMER_EMAIL         =  "customer-email@example.com"
       paymentParamModel.ORDER_ID               = "123456"
       paymentParamModel.PRODUCT_NAME           = "Product 1, Product 2"

        //Billing Address
       paymentParamModel.ADDRESS_BILLING        = "Flat 1,Building 123, Road 2345"
       paymentParamModel.CITY_BILLING           = "Manama"
       paymentParamModel.STATE_BILLING          = "Manama"
       paymentParamModel.COUNTRY_BILLING        = "BHR"
       paymentParamModel.POSTAL_CODE_BILLING    = "00973"

        //Shipping Address
       paymentParamModel.ADDRESS_SHIPPING       = "Flat 1,Building 123, Road 2345"
       paymentParamModel.CITY_SHIPPING          = "Manama"
       paymentParamModel.STATE_SHIPPING         = "Manama"
       paymentParamModel.COUNTRY_SHIPPING       = "BHR"
       paymentParamModel.POSTAL_CODE_SHIPPING    ="00973"

        //Tokenization
       paymentParamModel.IS_TOKENIZATION = true
    }

    fun saveTokenDetails(token: String?, customerEmail: String?, customerPassword: String?) {

        tokenizationPref.saveTokenization(
            TokenizationModel().apply {
                TOKEN = token
                CUSTOMER_EMAIL = customerEmail
                CUSTOMER_PASSWORD = customerPassword
            }
        )
    }

    fun saveTransactionId(transactionId: String?) {
        transactionPref.saveTransactionId(transactionId)
    }
}