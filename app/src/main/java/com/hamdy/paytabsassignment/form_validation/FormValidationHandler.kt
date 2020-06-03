package com.hamdy.paytabsassignment.form_validation


interface FormValidationHandler {

    fun onSuccess()
    fun onFailure(fields: ArrayList<FormValidationError?>)
}