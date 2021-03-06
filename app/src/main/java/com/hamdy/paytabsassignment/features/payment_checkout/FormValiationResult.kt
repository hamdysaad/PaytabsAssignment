package com.hamdy.paytabsassignment.features.payment_checkout

import com.hamdy.paytabsassignment.form_validation.FormValidationError

class FormValiationResult(
    val fields: ArrayList<FormValidationError?>? = null,
    val isSuccess: Boolean,
    val model: PaymentParamModel?= null
)