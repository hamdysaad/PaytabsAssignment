package com.hamdy.paytabsassignment.features.payment_checkout.tokenization

import com.hamdy.paytabsassignment.features.payment_checkout.PaymentParamModel

data class TokenizedTranactionRequest(
    val TOKEN: String?,
    val CUSTOMER_EMAIL: String?,
    val CUSTOMER_PASSWORD: String?,
    val firstName: String? ,
    val lastName: String ?,
    val paymentParamModel: PaymentParamModel?
)