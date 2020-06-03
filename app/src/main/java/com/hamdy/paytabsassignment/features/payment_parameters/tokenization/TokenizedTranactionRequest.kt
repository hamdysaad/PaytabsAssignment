package com.hamdy.paytabsassignment.features.payment_parameters.tokenization

import com.hamdy.paytabsassignment.features.payment_parameters.PaymentParamModel

class TokenizedTranactionRequest(
    val TOKEN: String?,
    val CUSTOMER_EMAIL: String?,
    val CUSTOMER_PASSWORD: String?,
    val firstName: String ? = "Hamdy Saad",
    val lastName: String ? = "Saad",
    val paymentParamModel: PaymentParamModel
)