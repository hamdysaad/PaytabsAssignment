package com.hamdy.paytabsassignment.features.payment_parameters.pre_auth

class PreAuthModel {

    var MERCHANT_EMAIL : String? = null
        set(value) {
            field = value?.trim()
        }
    var SECRET_KEY : String? = null
        set(value) {
            field = value?.trim()
        }
    var TRANSACTION_ID : String? = null
        set(value) {
            field = value?.trim()
        }
    var CAPTURED_AMOUNT : String? = null
        set(value) {
            field = value?.trim()
        }
    var VOID_TRANSACTION : String? = null
        set(value) {
            field = value?.trim()
        }
}